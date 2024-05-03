package com.backend.StudentTipMaster.mapper;

import com.backend.StudentTipMaster.entity.Room;
import com.backend.StudentTipMaster.response.RoomResponse;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class RoomToResponseRoom extends PropertyMap<Room, RoomResponse> {
    @Override
    protected void configure() {
        map(source.getId(), destination.getId());
        map(source.getRoomName(), destination.getRoomName());
        map(source.getUsers(), destination.getUsers());
        map(source.getOwner(), destination.getOwner());
    }
}
