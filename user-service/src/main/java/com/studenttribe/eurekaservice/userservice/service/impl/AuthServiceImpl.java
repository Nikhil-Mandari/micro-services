package com.studenttribe.eurekaservice.userservice.service.impl;

import com.studenttribe.eurekaservice.userservice.dto.request.LoginRequestDto;
import com.studenttribe.eurekaservice.userservice.dto.request.SignupRequestDto;
import com.studenttribe.eurekaservice.userservice.dto.response.AuthResponseDto;
import com.studenttribe.eurekaservice.userservice.dto.response.UserResponseDto;
import com.studenttribe.eurekaservice.userservice.entity.User;
import com.studenttribe.eurekaservice.userservice.exception.InvalidCredentialsException;
import com.studenttribe.eurekaservice.userservice.exception.UserExistsException;
import com.studenttribe.eurekaservice.userservice.exception.UserNotFoundException;
import com.studenttribe.eurekaservice.userservice.service.AuthService;
import com.studenttribe.eurekaservice.userservice.service.UserService;
import com.studenttribe.eurekaservice.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AuthResponseDto login(
            LoginRequestDto loginRequestDto
    ) throws UserNotFoundException, InvalidCredentialsException {

        User user = userService.findByEmail(loginRequestDto.getEmail());

        boolean isValidPassword = passwordEncoder.matches(
                loginRequestDto.getPassword(),
                user.getPassword()
        );

        if (!isValidPassword) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String accessToken = jwtUtil.generateAccessToken(user);
        String refreshToken = jwtUtil.generateRefreshToken(user);

        return AuthResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(modelMapper.map(user, UserResponseDto.class))
                .build();
    }

    @Override
    public UserResponseDto signup(
            SignupRequestDto signupRequestDto
    ) throws UserExistsException {

        if (userService.existsByEmail(signupRequestDto.getEmail())) {
            throw new UserExistsException(
                    "User already exists with email : " + signupRequestDto.getEmail()
            );
        }

        return userService.save(signupRequestDto);
    }
}
