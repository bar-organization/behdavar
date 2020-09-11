package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class UserAmountDto extends BaseAuditorDto<String, Long> {

    private BigDecimal totalAmount;
    private BigDecimal receiveAmount;
    private UserDto user;
}