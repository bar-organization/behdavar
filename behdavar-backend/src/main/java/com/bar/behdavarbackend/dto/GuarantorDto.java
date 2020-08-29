package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavarcommon.enumeration.RelationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class GuarantorDto extends BaseAuditorDto<String, Long> {

    @NotNull(message = "validation.error.not.null")
    private RelationType relationType;
    @NotNull(message = "validation.error.not.null")
    private ContractDto contract;
    @NotNull(message = "validation.error.not.null")
    private PersonDto person;
}