package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartableDto extends BaseAuditorDto<Long, Long> {

    @NotNull(message = "validation.error.not.null")
    private UserDto sender;

    @NotNull(message = "validation.error.not.null")
    private UserDto receiver;

    @NotNull(message = "validation.error.not.null")
    private ContractDto contract;
}