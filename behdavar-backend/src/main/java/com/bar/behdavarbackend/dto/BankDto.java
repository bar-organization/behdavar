package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class BankDto extends BaseAuditorDto<String, Long> {
    private String code;
    private String name;
    private String phone;
    private String fax;
    private AddressDto address;
    private String heads;
    private CatalogDetailDto bankType;
    private Boolean active;

}