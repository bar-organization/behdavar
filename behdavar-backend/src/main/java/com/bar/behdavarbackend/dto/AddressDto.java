package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class AddressDto extends BaseAuditorDto<String, Long> {

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