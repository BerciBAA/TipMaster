package com.backend.StudentTipMaster.controller;


import com.backend.StudentTipMaster.handler.TokenNotFoundException;
import com.backend.StudentTipMaster.request.LoginRequest;
import com.backend.StudentTipMaster.request.RefreshTokenRequest;
import com.backend.StudentTipMaster.request.RegisterRequest;
import com.backend.StudentTipMaster.response.LoginResponse;
import com.backend.StudentTipMaster.response.RegisterResponse;
import com.backend.StudentTipMaster.service.JwtService;
import com.backend.StudentTipMaster.service.RefreshTokenService;
import com.backend.StudentTipMaster.service.UserService;
import com.backend.StudentTipMaster.service.UserServiceDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok().body(userService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(registerRequest));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) throws TokenNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(refreshTokenService.verifyRefreshToken(refreshTokenRequest));
    }
}
