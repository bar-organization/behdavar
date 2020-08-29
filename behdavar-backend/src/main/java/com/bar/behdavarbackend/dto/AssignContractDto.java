package com.bar.behdavarbackend.dto;

import com.bar.behdavarcommon.enumeration.ContractStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AssignContractDto {

    @NotNull(message = "validation.error.not.null")
    private Long assigneeId;
    @NotNull(message = "validation.error.not.null")
    private Long contractId;
    @NotNull(message = "validation.error.not.null")
    private ContractStatus status;
}
