package com.example.dynamicform.dto;
import jakarta.validation.constraints.*;
public class RegisterRequest {
 @NotBlank private String name; @NotBlank @Email private String email; @NotBlank @Size(min=6) private String password;
 public String getName(){return name;} public String getEmail(){return email;} public String getPassword(){return password;}
 public void setName(String v){name=v;} public void setEmail(String v){email=v;} public void setPassword(String v){password=v;}
}
