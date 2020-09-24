package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class PersonDto extends BaseAuditorDto<String, Long> {

    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String description;
    private String nationalCode;
    private String fatherName;
    private List<ContactDto> contacts = new ArrayList<>();
}
