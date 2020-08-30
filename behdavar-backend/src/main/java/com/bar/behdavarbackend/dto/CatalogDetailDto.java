package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class CatalogDetailDto extends BaseAuditorDto<String, Long> {

    private String englishTitle;
    private String title;
    private String code;
    private Boolean active;
    private CatalogDto catalog;

}
