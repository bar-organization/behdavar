package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import lombok.Data;

@Data
public class ProductDto extends BaseAuditorDto<Long, Long> {

    private String productShasiNumber;
    private String productPlate;
    private String productName;
}