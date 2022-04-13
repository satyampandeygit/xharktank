package com.crio.xharktank.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidBodyException extends RuntimeException{

    public InvalidBodyException(String message){
        super(message);
    }

}
