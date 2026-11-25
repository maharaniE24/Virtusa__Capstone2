package com.example.dynamicform.security;
import com.example.dynamicform.model.AppUser;
import com.example.dynamicform.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
@Component
public class CurrentUser {
 private final UserRepository users;
 public CurrentUser(UserRepository users){this.users=users;}
 public AppUser get(HttpServletRequest request){
  String header=request.getHeader("Authorization");
  if(header==null||!header.startsWith("Bearer "))throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Please sign in");
  String token=header.substring(7);try{Long id=Long.valueOf(token.split("-")[0]);return users.findById(id).orElseThrow();}catch(Exception e){throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid session");}
 }
 public AppUser admin(HttpServletRequest r){AppUser u=get(r);if(!"ADMIN".equals(u.getRole()))throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Admin access required");return u;}
 public Map<String,Object> map(AppUser u){return Map.of("id",u.getId(),"name",u.getName(),"email",u.getEmail(),"role",u.getRole());}
}
