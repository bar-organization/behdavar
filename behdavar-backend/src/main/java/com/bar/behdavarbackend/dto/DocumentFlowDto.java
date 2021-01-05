package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class DocumentFlowDto extends BaseAuditorDto<String, Long> {

    private UserDto previousReceiver;
    private UserDto newReceiver;
    private UserDto sender;
}