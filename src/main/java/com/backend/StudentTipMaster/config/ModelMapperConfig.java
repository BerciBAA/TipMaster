package com.backend.StudentTipMaster.config;

import com.backend.StudentTipMaster.mapper.RegisterRequestToUser;
import com.backend.StudentTipMaster.mapper.UserToRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    private final RegisterRequestToUser registerRequestToUser;
    private final UserToRegisterResponse userToRegisterResponse;
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(registerRequestToUser);
        modelMapper.addConverter(userToRegisterResponse);
        return modelMapper;
    }
}
