package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class CatalogDto extends BaseAuditorDto<String, Long> {

    @NotBlank(message = "validation.error.not.blank")
    private String code;
    @NotBlank(message = "validation.error.not.blank")
    private String englishTitle;
    @NotBlank(message = "validation.error.not.blank")
    private String title;
    @NotNull(message = "validation.error.not.null")
    private Boolean active;
}