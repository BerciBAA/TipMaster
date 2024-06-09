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
        UserDto owner = new UserDto();
        owner.setId(source.getOwner().getId());
        owner.setUsername(source.getOwner().getUsername());
        owner.setRoles(source.getOwner().getRoles().stream().map(Role::getRole).collect(Collectors.toSet()));
        destination.setOwner(owner);
        if(source.getMembers()!= null){
            List<UserDto> members = source.getMembers().stream()
                    .map(user -> UserDto.builder()
                            .username(user.getUsername())
                            .roles(user.getRoles().stream()
                                    .map(Role::getRole)
                                    .collect(Collectors.toSet()))
                            .build())
                    .toList();
            destination.setMembers(members);
        }
        destination.setRoomName(source.getRoomName());

        return destination;
    }
}
