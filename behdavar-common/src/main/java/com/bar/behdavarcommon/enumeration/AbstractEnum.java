package com.bar.behdavarcommon.enumeration;

import java.io.Serializable;

public interface AbstractEnum<T> extends Serializable {
    T getValue();
}
