package com.studenttribe.eurekaservice.userservice.dto.response;

import com.studenttribe.eurekaservice.userservice.enums.Role;
import com.studenttribe.eurekaservice.userservice.enums.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {

    private int id;
    private String name;
    private String email;
    private Role role;
    private Status status;
}
