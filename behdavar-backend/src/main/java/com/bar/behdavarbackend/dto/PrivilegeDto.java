package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@EqualsAndHashCode
public class PrivilegeDto extends BaseAuditorDto<String, Long> {

    @NotBlank(message = "validation.error.not.null")
    private String name;

    private String title;
}