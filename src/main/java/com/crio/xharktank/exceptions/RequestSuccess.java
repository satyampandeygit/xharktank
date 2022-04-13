package com.crio.xharktank.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class RequestSuccess extends RuntimeException{

    public RequestSuccess(String message){
        super(message);
    }

}
