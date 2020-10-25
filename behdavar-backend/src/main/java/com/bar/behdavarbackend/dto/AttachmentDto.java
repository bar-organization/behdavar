package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class AttachmentDto extends BaseAuditorDto<String, Long> {

    @NotNull(message = "validation.error.not.null")
    private String content;

    @NotNull(message = "validation.error.not.null")
    private String fileName;

    @NotNull(message = "validation.error.not.null")
    private ContractDto contract;

}