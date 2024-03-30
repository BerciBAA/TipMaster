package com.backend.StudentTipMaster.mapper;

import com.backend.StudentTipMaster.entity.Role;
import com.backend.StudentTipMaster.entity.User;
import com.backend.StudentTipMaster.request.RegisterRequest;
import com.backend.StudentTipMaster.response.RegisterResponse;
import lombok.AllArgsConstructor;
import org.hibernate.mapping.Property;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserToRegisterResponse implements Converter<User, RegisterResponse> {

    @Override
    public RegisterResponse convert(MappingContext<User, RegisterResponse> mappingContext) {

        User source = mappingContext.getSource();
        RegisterResponse destination = new RegisterResponse();
        destination.setUsername(source.getUsername());
        destination.setEmail(source.getEmail());
        destination.setRoles(source.getRoles().stream().map(Role::getRole).collect(Collectors.toSet()));

        return destination;
    }
}
