package com.studenttribe.eurekaservice.userservice.service;

import com.studenttribe.eurekaservice.userservice.dto.request.LoginRequestDto;
import com.studenttribe.eurekaservice.userservice.dto.request.SignupRequestDto;
import com.studenttribe.eurekaservice.userservice.dto.response.AuthResponseDto;
import com.studenttribe.eurekaservice.userservice.dto.response.UserResponseDto;
import com.studenttribe.eurekaservice.userservice.exception.InvalidCredentialsException;
import com.studenttribe.eurekaservice.userservice.exception.UserExistsException;
import com.studenttribe.eurekaservice.userservice.exception.UserNotFoundException;

public interface AuthService {

    AuthResponseDto login(LoginRequestDto loginRequestDto)
            throws UserNotFoundException, InvalidCredentialsException;

    UserResponseDto signup(SignupRequestDto signupRequestDto) throws UserExistsException;
}
