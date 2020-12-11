package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.bar.behdavardatabase.entity.MessageAttachmentEntity;
import com.bar.behdavardatabase.entity.MessageReceiverEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class MessageDto extends BaseAuditorDto<String, Long> {

    private String messageNumber;
    @NotBlank(message = "validation.error.not.null")
    private String subject;
    @NotBlank(message = "validation.error.not.null")
    private String description;
    private Boolean isSent;
    private Boolean deleted;
    private UserDto sender;
    private Set<MessageReceiverDto> receivers = new HashSet<>();
    private Set<MessageReceiverDto> ccs = new HashSet<>();
    private Set<MessageAttachmentDto> attachments = new HashSet<>();
}
