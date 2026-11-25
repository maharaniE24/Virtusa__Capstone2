package com.example.dynamicform.controller;
import com.example.dynamicform.model.*;
import com.example.dynamicform.service.*;
import com.example.dynamicform.security.CurrentUser;
import com.example.dynamicform.dto.SubmissionRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController @RequestMapping("/api")
public class FormController {
 private final FormService forms;private final SubmissionService submissions;private final CurrentUser current;
 public FormController(FormService f,SubmissionService s,CurrentUser c){forms=f;submissions=s;current=c;}
 @GetMapping("/forms") public List<DynamicForm> all(HttpServletRequest r){current.admin(r);return forms.all();}
 @GetMapping("/forms/published") public List<DynamicForm> published(HttpServletRequest r){current.get(r);return forms.published();}
 @GetMapping("/forms/{id}") public DynamicForm get(@PathVariable Long id,HttpServletRequest r){current.get(r);return forms.get(id);}
 @PostMapping("/forms") public DynamicForm create(@RequestBody DynamicForm form,HttpServletRequest r){AppUser u=current.admin(r);return forms.save(form,u.getId());}
 @PutMapping("/forms/{id}") public DynamicForm update(@PathVariable Long id,@RequestBody DynamicForm form,HttpServletRequest r){AppUser u=current.admin(r);form.setId(id);return forms.save(form,u.getId());}
 @DeleteMapping("/forms/{id}") public Map<String,String> delete(@PathVariable Long id,HttpServletRequest r){current.admin(r);forms.delete(id);return Map.of("message","Form deleted");}
 @PostMapping("/forms/{id}/submissions") public FormSubmission submit(@PathVariable Long id,@Valid @RequestBody SubmissionRequest req,HttpServletRequest r){AppUser u=current.get(r);return submissions.submit(id,req,current.map(u));}
 @GetMapping("/submissions") public List<Map<String,Object>> responses(HttpServletRequest r){current.admin(r);return submissions.all();}
}
