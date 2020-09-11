package com.bar.behdavarbackend.exception;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final String message;
    private final Object[] args;

    public BusinessException(String message, Object... args) {
        this.message = message;
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }
}
