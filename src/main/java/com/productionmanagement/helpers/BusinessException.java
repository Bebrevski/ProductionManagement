package com.productionmanagement.helpers;

public class BusinessException extends Exception{
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException (String message, Exception innerException) {
        super(message, innerException);
    }
}
