package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import lombok.Data;


@Data
public class PersonDto extends BaseAuditorDto<Long, Long> {

    private String firstName;
    private String lastName;
    private String email;
    private String description;
}
