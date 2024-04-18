package com.backend.StudentTipMaster.service;

import com.backend.StudentTipMaster.entity.RefreshToken;
import com.backend.StudentTipMaster.entity.User;
import com.backend.StudentTipMaster.repository.UserRepository;
import com.backend.StudentTipMaster.request.LoginRequest;
import com.backend.StudentTipMaster.request.RegisterRequest;
import com.backend.StudentTipMaster.response.LoginResponse;
import com.backend.StudentTipMaster.response.RegisterResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final ModelMapper modelMapper;

    public LoginResponse login(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginRequest.getUsername());
        if(authentication.isAuthenticated()){
            return LoginResponse.builder()
                    .accessToken(jwtService.GenerateToken(loginRequest.getUsername()))
                    .refreshToken(refreshToken.getToken())
                    .build();
        }
        throw new UsernameNotFoundException("Nincs regisztrálva ilyen felhasználó.");
    }

    public RegisterResponse register(RegisterRequest registerRequest){
        User user = modelMapper.map(registerRequest, User.class);
        User savedUser = userRepository.save(user);
        log.info(savedUser.getRoles().toString());
        return modelMapper.map(savedUser, RegisterResponse.class);
    }
}
