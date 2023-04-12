package com.digdes.school.exception;

public class CommandException extends RuntimeException {
    public CommandException(String errorMessage){
        super(errorMessage);
    }
}
