package com.example.shorten.controllers;

import com.example.shorten.dto.request.LoginRequestDTO;
import com.example.shorten.dto.request.SignupRequestDTO;
import com.example.shorten.dto.response.GenericResponse;
import com.example.shorten.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/signup")
    @ResponseBody()
    public ResponseEntity<GenericResponse> signUp(@Valid @RequestBody SignupRequestDTO signupRequestDTO){
        return ResponseEntity.ok(userService.signUp(signupRequestDTO));
    }

    @PostMapping("/login")
    @ResponseBody()
    public ResponseEntity<GenericResponse> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(userService.login(loginRequestDTO));
    }
}
