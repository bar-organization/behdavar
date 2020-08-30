package com.bar.behdavarapplication.exception;


import com.bar.behdavarbackend.exception.BusinessException;
import com.bar.behdavarbackend.util.RestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice

public class RestExceptionHandler {

    private static final String UNEXPECTED_ERROR = "Exception.unexpected";

    private final MessageSource messageSource;

    @Autowired

    public RestExceptionHandler(MessageSource messageSource) {

        this.messageSource = messageSource;

    }

    @ExceptionHandler(BusinessException.class)

    public ResponseEntity<RestMessage> handleIllegalArgument(BusinessException ex, Locale locale) {

        String errorMessage = messageSource.getMessage(ex.getMessage(), ex.getArgs(), locale);

        return new ResponseEntity<>(new RestMessage(errorMessage), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)

    public ResponseEntity<RestMessage> handleArgumentNotValidException(MethodArgumentNotValidException ex, Locale locale) {

        BindingResult result = ex.getBindingResult();

        List<String> errorMessages = result.getAllErrors()

                .stream()

                .map(objectError ->
                        messageSource.getMessage(objectError.getDefaultMessage(), objectError.getArguments(), locale)
                )
                .collect(Collectors.toList());

        return new ResponseEntity<>(new RestMessage(errorMessages), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)

    public ResponseEntity<RestMessage> handleExceptions(Exception ex, Locale locale) {

        String errorMessage = messageSource.getMessage(UNEXPECTED_ERROR, null, locale);
        return new ResponseEntity<>(new RestMessage(String.format("%s: [%s]", errorMessage, ex.getMessage())), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}