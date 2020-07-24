package com.bar.behdavarbackend.exception;


import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private String message;
    private Object[] args;

    public BusinessException(String message , Object... args){
        this.message = message;
        this.args = args;
    }

    public Object[] getArgs() {
        return args;
    }
}
