package com.studenttribe.eurekaservice.userservice.service;

import com.studenttribe.eurekaservice.userservice.dto.request.SignupRequestDto;
import com.studenttribe.eurekaservice.userservice.dto.request.UserRequestDto;
import com.studenttribe.eurekaservice.userservice.dto.response.UserResponseDto;
import com.studenttribe.eurekaservice.userservice.dto.response.UserStatusRequestDto;
import com.studenttribe.eurekaservice.userservice.entity.User;
import com.studenttribe.eurekaservice.userservice.exception.UserExistsException;
import com.studenttribe.eurekaservice.userservice.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    UserResponseDto save(SignupRequestDto userRequestDto) throws UserExistsException;

    User findByEmail(String email) throws UserNotFoundException;

    UserResponseDto update(UserRequestDto userRequestDto) throws UserNotFoundException;

    List<UserResponseDto> findAll();

    UserResponseDto findById(int id) throws UserNotFoundException;

    void delete(int id) throws UserNotFoundException;

    boolean updateStatus(int id, UserStatusRequestDto userStatusRequestDto) throws UserNotFoundException;

    boolean existsByEmail(String email);
}
