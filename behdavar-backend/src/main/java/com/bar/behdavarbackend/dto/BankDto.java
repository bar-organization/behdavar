package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import lombok.Data;

@Data
public class BankDto extends BaseAuditorDto<Long, Long> {
    private String code;
    private String name;
    private String phone;
    private String fax;
    private AddressDto address;
    private String heads;
    private CatalogDetailDto bankType;
    private Boolean active;

}