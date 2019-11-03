package com.productionmanagement.helpers;

public enum ResultType {
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
