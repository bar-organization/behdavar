package com.bar.behdavarbackend.dto.common;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseCodeTitleDto<C, I extends Serializable> extends BaseDto<I> {
    private C code;
    private String title;
}
