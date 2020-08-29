package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserAmountDto extends BaseAuditorDto<Long, Long> {

    private BigDecimal totalAmount;
    private BigDecimal receiveAmount;
    private UserDto User;
}