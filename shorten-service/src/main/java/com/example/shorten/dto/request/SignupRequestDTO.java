package com.example.shorten.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {

    @Email @NotEmpty String email;
    @NotEmpty String name;
    @NotEmpty
    String password;
    @NotEmpty
    String confirmPassword;
}
