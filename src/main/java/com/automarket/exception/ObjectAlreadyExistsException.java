package com.automarket.exception;

/**
 * @author orest uzhytchak
 * Custom exceptions for errors occured if object is alredy exists
 * */
public class ObjectAlreadyExistsException extends RuntimeException{
    Long errorCode;

    public ObjectAlreadyExistsException(String message, Long errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public Long getErrorCode() {
        return errorCode;
    }
}
