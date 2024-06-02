package com.backend.StudentTipMaster.controller;

import com.backend.StudentTipMaster.handler.RoomNameAlreadyExitsException;
import com.backend.StudentTipMaster.request.CreateRoomMessage;
import com.backend.StudentTipMaster.response.ExceptionResponse;
import com.backend.StudentTipMaster.response.RoomResponse;
import com.backend.StudentTipMaster.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RoomWebSocketController {

    private final RoomService roomService;

    @MessageMapping("/create-room")
    @SendTo("/rooms/create-room")
    public RoomResponse createRoom(@Validated @Payload CreateRoomMessage roomInfo){
        return roomService.createRoom(roomInfo);
    }

    @SubscribeMapping("/rooms/create-room")
    public List<RoomResponse> subscribeRoom() {
        return roomService.getRooms();
    }


    @MessageExceptionHandler
    @SendToUser("/rooms/errors")
    public ExceptionResponse handleRoomNameAlreadyExitsException(RoomNameAlreadyExitsException exception) {
        System.out.println(exception.getMessage());
        return ExceptionResponse.builder()
                .exceptionMessage(exception.getMessage())
                .build();
    }

    @MessageExceptionHandler
    @SendToUser("/rooms/errors")
    public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        System.out.println(exception.getMessage());
        return ExceptionResponse.builder()
                .exceptionMessage(exception.getMessage())
                .build();
    }
}
