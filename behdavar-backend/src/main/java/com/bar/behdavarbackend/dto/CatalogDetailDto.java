package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import lombok.Data;

@Data
public class CatalogDetailDto extends BaseAuditorDto<Long, Long> {

    private String englishTitle;
    private String title;
    private String code;
    private Boolean active;
    private CatalogDto catalog;

}
