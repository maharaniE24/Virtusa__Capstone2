package com.example.dynamicform.service;
import com.example.dynamicform.model.*;
import com.example.dynamicform.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
@Service
public class FormService {
 private final FormRepository forms;
 public FormService(FormRepository forms){this.forms=forms;}
 public List<DynamicForm> all(){return forms.findAll();}
 public List<DynamicForm> published(){return forms.findByPublishedTrue();}
 public DynamicForm get(Long id){return forms.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Form not found"));}
 public DynamicForm save(DynamicForm input,Long userId){
  DynamicForm target=input.getId()==null?new DynamicForm():get(input.getId());
  target.setTitle(input.getTitle());target.setDescription(input.getDescription());target.setPublished(input.isPublished());
  if(target.getCreatedBy()==null)target.setCreatedBy(userId);
  List<FormField> copy=new ArrayList<>(input.getFields());target.setFields(copy);
  return forms.save(target);
 }
 public void delete(Long id){forms.delete(get(id));}
}
