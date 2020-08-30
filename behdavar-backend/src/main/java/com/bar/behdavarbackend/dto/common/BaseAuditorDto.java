package com.bar.behdavarbackend.dto.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public abstract class BaseAuditorDto<U, I extends Serializable> extends BaseDto<I> {

    private U createdBy;
    private LocalDateTime createdDate;
    private U lastModifiedBy;
    private LocalDateTime lastModifiedDate;
    private Long version;
}
