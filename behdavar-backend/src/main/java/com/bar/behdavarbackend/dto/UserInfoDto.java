package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class UserInfoDto extends BaseAuditorDto<String, Long> {
    private LocalDateTime lastLogin;
    private UserAmountDto userAmount;
    private Long activeCount;
}