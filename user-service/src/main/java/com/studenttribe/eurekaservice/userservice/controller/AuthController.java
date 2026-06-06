package com.studenttribe.eurekaservice.userservice.controller;

import com.studenttribe.eurekaservice.userservice.dto.request.LoginRequestDto;
import com.studenttribe.eurekaservice.userservice.dto.request.SignupRequestDto;
import com.studenttribe.eurekaservice.userservice.dto.response.ApiResponseDto;
import com.studenttribe.eurekaservice.userservice.dto.response.AuthResponseDto;
import com.studenttribe.eurekaservice.userservice.dto.response.UserResponseDto;
import com.studenttribe.eurekaservice.userservice.exception.InvalidCredentialsException;
import com.studenttribe.eurekaservice.userservice.exception.UserExistsException;
import com.studenttribe.eurekaservice.userservice.exception.UserNotFoundException;
import com.studenttribe.eurekaservice.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<AuthResponseDto>> login(
            @RequestBody LoginRequestDto loginRequestDto
    ) throws UserNotFoundException, InvalidCredentialsException {

        return ResponseEntity.ok(
                ApiResponseDto.<AuthResponseDto>builder()
                        .success(true)
                        .status(HttpStatus.OK.value())
                        .message("Login Successful")
                        .data(authService.login(loginRequestDto))
                        .build()
        );
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> signup(
            @RequestBody SignupRequestDto signupRequestDto
    ) throws UserExistsException {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponseDto.<UserResponseDto>builder()
                                .success(true)
                                .status(HttpStatus.CREATED.value())
                                .message("User Created Successfully")
                                .data(authService.signup(signupRequestDto))
                                .build()
                );
    }
}
