package com.example.dynamicform.controller;
import com.example.dynamicform.dto.*;
import com.example.dynamicform.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController @RequestMapping("/api/auth")
public class AuthController {
 private final AuthService service;public AuthController(AuthService s){service=s;}
 @PostMapping("/login") public Map<String,Object> login(@Valid @RequestBody AuthRequest req){return service.login(req);}
 @PostMapping("/register") public Map<String,Object> register(@Valid @RequestBody RegisterRequest req){return service.register(req);}
}
