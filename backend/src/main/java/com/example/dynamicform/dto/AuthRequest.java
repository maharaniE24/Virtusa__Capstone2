package com.example.dynamicform.dto;
import jakarta.validation.constraints.*;
public class AuthRequest {
 @NotBlank @Email private String email; @NotBlank private String password;
 public String getEmail(){return email;} public String getPassword(){return password;}
 public void setEmail(String v){email=v;} public void setPassword(String v){password=v;}
}
