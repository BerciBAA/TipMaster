package com.backend.StudentTipMaster.controller;

import com.backend.StudentTipMaster.request.CreateRoomMessage;
import com.backend.StudentTipMaster.response.RoomResponse;
import com.backend.StudentTipMaster.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class RoomWebSocketController {

    private final RoomService roomService;

    @MessageMapping("/create-room")
    @SendTo("/rooms/create-room")
    public RoomResponse createRoom(CreateRoomMessage roomInfo){
        return roomService.createRoom(roomInfo);
    }

    @SubscribeMapping("/rooms/create-room")
    public List<RoomResponse> subscribeRoom() {
        return roomService.getRooms();
    }
}
