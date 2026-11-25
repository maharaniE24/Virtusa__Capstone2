package com.example.dynamicform.service;
import com.example.dynamicform.model.*;
import com.example.dynamicform.dto.SubmissionRequest;
import com.example.dynamicform.repository.SubmissionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
@Service
public class SubmissionService {
 private final SubmissionRepository submissions;private final FormService forms;private final ObjectMapper mapper;
 public SubmissionService(SubmissionRepository s,FormService f,ObjectMapper m){submissions=s;forms=f;mapper=m;}
 public FormSubmission submit(Long formId,SubmissionRequest req,Map<String,Object> user){
  DynamicForm form=forms.get(formId);if(!form.isPublished())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This form is not published");
  for(FormField field:form.getFields()){
   Object value=req.getAnswers().get(String.valueOf(field.getId()));
   if(field.isRequired()&&(value==null||value.toString().isBlank()||Boolean.FALSE.equals(value)))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,field.getLabel()+" is required");
  }
  try{return submissions.save(new FormSubmission(formId,form.getTitle(),Long.valueOf(user.get("id").toString()),user.get("name").toString(),mapper.writeValueAsString(req.getAnswers())));}
  catch(JsonProcessingException e){throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid answer data");}
 }
 public List<Map<String,Object>> all(){
  List<Map<String,Object>> result=new ArrayList<>();
  for(FormSubmission s:submissions.findAllByOrderBySubmittedAtDesc()){
   try{Map<String,Object> m=new LinkedHashMap<>();m.put("id",s.getId());m.put("formTitle",s.getFormTitle());m.put("userName",s.getUserName());m.put("submittedAt",s.getSubmittedAt());m.put("answers",mapper.readValue(s.getAnswers(),Map.class));result.add(m);}
   catch(Exception ignored){}
  }return result;
 }
}
