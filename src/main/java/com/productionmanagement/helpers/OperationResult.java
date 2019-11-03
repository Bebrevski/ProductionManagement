package com.productionmanagement.helpers;

import java.io.Serializable;
import java.util.List;

public class OperationResult<T> implements Serializable {
    public ResultType Type;
    public String Message;
    public List<String> AdditionalMessages;
    public T ResultData;

    protected OperationResult() {
    }

    public static <T> OperationResult<T> Success() {
        return Success(null);
    }

    public static <T> OperationResult<T> Success(String message) {
        return Success(message, null);
    }

    public static <T> OperationResult<T> Success(String message, List<String> additionalMessages) {
        return Success(null, message, additionalMessages);
    }

    public static <T> OperationResult<T> Success(T resultObject, String message, List<String> additionalMessages) {
        OperationResult<T> result = new OperationResult<T>();
        result.Type = ResultType.Success;
        result.Message = message;
        result.AdditionalMessages = additionalMessages;
        result.ResultData = resultObject;

        return result;
    }

    public static <T> OperationResult<T> Error() {
        return Error(null);
    }

    public static <T> OperationResult<T> Error(String errorMessage) {
        return Error(errorMessage, null);
    }

    public static <T> OperationResult<T> Error(String errorMessage, List<String> additionalMessages) {
        OperationResult<T> result = new OperationResult<>();
        result.Type = ResultType.Error;
        result.Message = errorMessage;
        result.AdditionalMessages = additionalMessages;
        result.ResultData = null;

        return result;
    }

    public static <T> OperationResult<T> Exception(Exception ex) {
        OperationResult<T> result = new OperationResult<>();
        result.Type = ResultType.Exception;
        result.Message = ex.getClass() == BusinessException.class ? ex.getMessage() : null;
        result.ResultData = null;

        return result;
    }
}

enum ResultType {
    Success(1),
    Error(0),
    Exception(-1);

    private int value;

    ResultType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}