package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavarcommon.enumeration.ContractStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class StatusLogDto extends BaseAuditorDto<String, Long> {

    @NotNull(message = "validation.error.not.null")
    private ContractStatus status;
    @NotNull(message = "validation.error.not.null")
    private UserDto user;
    @NotNull(message = "validation.error.not.null")
    private ContractDto contract;
}