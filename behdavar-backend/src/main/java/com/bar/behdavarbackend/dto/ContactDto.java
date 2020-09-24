package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavarcommon.enumeration.PhoneType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ContactDto extends BaseAuditorDto<String, Long> {

    @NotBlank(message = "validation.error.not.blank")
    private String number;

    private String description;

    private String preCode;

    private Boolean confirmed;

    @NotBlank(message = "validation.error.not.blank")
    private PhoneType phoneType;

    @NotBlank(message = "validation.error.not.blank")
    private PersonDto person;
}