package com.backend.StudentTipMaster.service;

import com.backend.StudentTipMaster.entity.Room;
import com.backend.StudentTipMaster.handler.RoomNameAlreadyExitsException;
import com.backend.StudentTipMaster.repository.RoomRepository;
import com.backend.StudentTipMaster.request.CreateRoomMessage;
import com.backend.StudentTipMaster.response.RoomResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public List<RoomResponse> getRooms(){
        return roomRepository.findAll()
                .stream()
                .map(room -> modelMapper.map(room, RoomResponse.class))
                .toList();

    }

    public RoomResponse createRoom(CreateRoomMessage roomRequest){
        roomRepository.findByRoomName(roomRequest.getRoomName()).ifPresent(room -> {
            throw new RoomNameAlreadyExitsException("A szobanév már foglalt");
        });
        Room room = roomRepository.save(modelMapper.map(roomRequest, Room.class));
        return modelMapper.map(room, RoomResponse.class);
    }
}
