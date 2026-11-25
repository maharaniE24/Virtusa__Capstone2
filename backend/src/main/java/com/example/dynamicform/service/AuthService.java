package com.example.dynamicform.service;
import com.example.dynamicform.dto.*;
import com.example.dynamicform.model.AppUser;
import com.example.dynamicform.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
@Service
public class AuthService {
 private final UserRepository users;
 public AuthService(UserRepository users){this.users=users;}
 public Map<String,Object> login(AuthRequest req){
  AppUser u=users.findByEmail(req.getEmail()).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password"));
  if(!u.getPassword().equals(req.getPassword()))throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid email or password");
  return response(u);
 }
 public Map<String,Object> register(RegisterRequest req){
  if(users.findByEmail(req.getEmail()).isPresent())throw new ResponseStatusException(HttpStatus.CONFLICT,"Email already registered");
  AppUser u=users.save(new AppUser(req.getName(),req.getEmail(),req.getPassword(),"USER"));return response(u);
 }
 private Map<String,Object> response(AppUser u){
  Map<String,Object> user=Map.of("id",u.getId(),"name",u.getName(),"email",u.getEmail(),"role",u.getRole());
  return Map.of("token",u.getId()+"-"+UUID.randomUUID(),"user",user);
 }
}
