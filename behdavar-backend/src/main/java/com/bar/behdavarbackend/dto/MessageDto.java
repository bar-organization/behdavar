package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MessageDto extends BaseAuditorDto<String, Long> {

    private String messageNumber;
    private String subject;
    private String description;
    private Boolean isSent;
    private Boolean active;
    private UserDto sender;
}
