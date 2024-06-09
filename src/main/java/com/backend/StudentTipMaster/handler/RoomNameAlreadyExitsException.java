package com.backend.StudentTipMaster.handler;

public class RoomNameAlreadyExitsException extends RuntimeException {
    public RoomNameAlreadyExitsException(String errorMessage){
        super(errorMessage);
    }
}
