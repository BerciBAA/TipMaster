package com.backend.StudentTipMaster.mapper;

import com.backend.StudentTipMaster.entity.Room;
import com.backend.StudentTipMaster.entity.User;
import com.backend.StudentTipMaster.repository.UserRepository;
import com.backend.StudentTipMaster.request.CreateRoomMessage;
import com.backend.StudentTipMaster.response.RegisterResponse;
import com.backend.StudentTipMaster.response.RoomResponse;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CreateRoomMessageToRoom implements Converter<CreateRoomMessage, Room> {

    private final UserRepository userRepository;

    @Override
    public Room convert(MappingContext<CreateRoomMessage, Room> mappingContext) {
        CreateRoomMessage source = mappingContext.getSource();
        Room destination = new Room();
        destination.setRoomName(source.getRoomName());
        if (source.getUserId() != null && !source.getUserId().toString().isEmpty()){
            User user = userRepository.findById(source.getUserId()).orElseThrow(() -> new UsernameNotFoundException("A felhasználó nem található."));
            destination.setOwner(user);
            destination.setMembers(List.of(user));
        }

        return destination;
    }
}
