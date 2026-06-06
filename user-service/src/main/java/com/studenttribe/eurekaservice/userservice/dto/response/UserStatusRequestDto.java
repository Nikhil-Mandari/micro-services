package com.studenttribe.eurekaservice.userservice.dto.response;

import com.studenttribe.eurekaservice.userservice.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserStatusRequestDto {

    @NotNull(message = "Id is required")
    private Integer id;

    @NotNull(message = "Status is required")
    private Status status;

    @NotNull(message = "Reason is required")
    private String reason;
}
