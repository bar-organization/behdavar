package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavarcommon.enumeration.ContractStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatusLogDto extends BaseAuditorDto<Long, Long> {

    @NotNull(message = "validation.error.not.null")
    private ContractStatus status;
    @NotNull(message = "validation.error.not.null")
    private UserDto user;
    @NotNull(message = "validation.error.not.null")
    private ContractDto contract;
}