package com.bar.behdavarbackend.dto.common;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseDto<I extends Serializable> {
    private I id;
}
