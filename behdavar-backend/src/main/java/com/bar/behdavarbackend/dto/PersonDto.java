package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class PersonDto extends BaseAuditorDto<String, Long> {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String description;
}
