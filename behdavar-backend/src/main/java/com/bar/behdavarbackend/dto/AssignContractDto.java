package com.bar.behdavarbackend.dto;

import com.bar.behdavarcommon.enumeration.ContractStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class AssignContractDto {

    @NotNull(message = "validation.error.not.null")
    private Long assigneeId;
    @NotNull(message = "validation.error.not.null")
    private Long contractId;
    @NotNull(message = "validation.error.not.null")
    private ContractStatus status;
}
