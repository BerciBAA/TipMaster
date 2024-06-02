package com.backend.StudentTipMaster.mapper;

import com.backend.StudentTipMaster.entity.Role;
import com.backend.StudentTipMaster.entity.Room;
import com.backend.StudentTipMaster.response.RoomResponse;
import com.backend.StudentTipMaster.response.UserDto;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoomToResponseRoom  implements Converter<Room, RoomResponse> {

    @Override
    public RoomResponse convert(MappingContext<Room, RoomResponse> mappingContext) {
        Room source = mappingContext.getSource();
        RoomResponse destination = new RoomResponse();
        destination.setRoomId(source.getRoomId());
        destination.setOwner(source.getOwner());
        destination.setTemporaryUsers(source.getTemporaryUsers());
        if(source.getUsers() != null){
            List<UserDto> users = source.getUsers().stream()
                    .map(user -> UserDto.builder()
                            .email(user.getEmail())
                            .username(user.getUsername())
                            .roles(user.getRoles().stream()
                                    .map(Role::getRole)
                                    .collect(Collectors.toSet()))
                            .build())
                    .collect(Collectors.toList());
            destination.setUsers(users);
        }
        destination.setRoomName(source.getRoomName());

        return destination;
    }
}
