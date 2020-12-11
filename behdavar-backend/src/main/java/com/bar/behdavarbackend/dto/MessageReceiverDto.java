package com.bar.behdavarbackend.dto;

import com.bar.behdavarbackend.dto.common.BaseAuditorDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Getter
@Setter
@NoArgsConstructor
public class MessageReceiverDto extends BaseAuditorDto<String, Long> {

    private Boolean isCC;
    private Boolean deleted;
    private Boolean isRead;

    @NotNull(message = "validation.error.not.null")
    private UserDto receiver;

    @NotNull(message = "validation.error.not.null")
    private MessageDto message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MessageReceiverDto that = (MessageReceiverDto) o;
        if ( this.getId() != null && this.getId().equals(that.getId())) return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), receiver, message);
    }
}
