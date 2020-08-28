package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import lombok.Data;

@Data
public class AddressDto extends BaseAuditorDto<Long, Long> {

    private String mainStreet;
    private String subStreet;
    private String mainAlley;
    private String subAlley;
    private String postalCode;
    private String plate;
    private String description;
    private GeoDivisionDto geoDivision;
    private PersonDto person;
}