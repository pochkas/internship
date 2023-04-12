package com.digdes.school.exception;

public class ConditionException extends RuntimeException{
    public ConditionException(String errorMessage){
            super(errorMessage);
        }
}
