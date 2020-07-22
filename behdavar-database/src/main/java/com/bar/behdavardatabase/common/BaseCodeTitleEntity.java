package com.bar.behdavardatabase.common;

import com.bar.behdavardatabase.constant.common.BaseCodeTitleConstant;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseCodeTitleEntity<C, I extends Serializable> extends BaseEntity<I> {
    private C code;
    private String title;

    protected BaseCodeTitleEntity() {
    }

    @Column(name = BaseCodeTitleConstant.CODE)
    public C getCode() {
        return code;
    }

    @Column(name = BaseCodeTitleConstant.TITLE)
    public String getTitle() {
        return title;
    }

    public void setCode(C code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
