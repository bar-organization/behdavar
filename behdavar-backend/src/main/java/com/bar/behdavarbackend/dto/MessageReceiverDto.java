package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MessageReceiverDto extends BaseAuditorDto<String, Long> {

    private Boolean isCC;
    private Boolean active;

    @NotNull(message = "validation.error.not.null")
    private UserDto receiver;

    @NotNull(message = "validation.error.not.null")
    private MessageDto message;
}
