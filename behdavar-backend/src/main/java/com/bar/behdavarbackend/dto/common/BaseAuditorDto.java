package com.bar.behdavarbackend.dto.common;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public abstract class BaseAuditorDto<U, I extends Serializable> extends BaseDto<I> {

    private U createdBy;
    private LocalDateTime createdDate;
    private U lastModifiedBy;
    private LocalDateTime lastModifiedDate;
    private Long version;
}
