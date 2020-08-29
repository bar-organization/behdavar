package com.bar.behdavarbackend.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public abstract class BaseDto<I extends Serializable> {
    private I id;
}
