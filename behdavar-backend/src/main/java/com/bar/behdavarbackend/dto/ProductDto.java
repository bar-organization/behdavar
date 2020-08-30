package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class ProductDto extends BaseAuditorDto<String, Long> {

    private String productShasiNumber;
    private String productPlate;
    private String productName;
}