package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import lombok.Data;

@Data
public class CatalogDto extends BaseAuditorDto<Long, Long> {

    private String code;
    private String englishTitle;
    private String title;
    private Boolean active;
}