package com.backend.StudentTipMaster.handler;

public class TokenNotFoundException extends Exception{
    public TokenNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
