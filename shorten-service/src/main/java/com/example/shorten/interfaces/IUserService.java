package com.example.shorten.interfaces;

import com.example.shorten.dto.request.LoginRequestDTO;
import com.example.shorten.dto.request.SignupRequestDTO;
import com.example.shorten.dto.response.GenericResponse;
import com.example.shorten.dto.response.UserResponseDTO;

public interface IUserService {

    GenericResponse signUp(SignupRequestDTO signupRequestDTO);

    GenericResponse<UserResponseDTO> login(LoginRequestDTO loginRequestDTO);
}
