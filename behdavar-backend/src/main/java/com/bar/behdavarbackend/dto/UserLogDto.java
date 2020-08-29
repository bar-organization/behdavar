package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class UserLogDto extends BaseDto<Long> {

    @NotNull(message = "validation.error.not.null")
    private UserDto user;
    @NotNull(message = "validation.error.not.null")
    private LocalDateTime lastLogin;
}
