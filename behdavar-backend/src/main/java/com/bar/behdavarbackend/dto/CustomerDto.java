package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerDto extends BaseAuditorDto<Long, Long> {

    @NotNull(message = "validation.error.not.null")
    private PersonDto person;
    @NotNull(message = "validation.error.not.null")
    private ContractDto contract;
}