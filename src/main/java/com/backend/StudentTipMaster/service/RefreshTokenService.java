package com.backend.StudentTipMaster.service;

import com.backend.StudentTipMaster.entity.RefreshToken;
import com.backend.StudentTipMaster.entity.User;
import com.backend.StudentTipMaster.handler.TokenNotFoundException;
import com.backend.StudentTipMaster.repository.RefreshTokenRepository;
import com.backend.StudentTipMaster.repository.UserRepository;
import com.backend.StudentTipMaster.response.LoginResponse;
import com.backend.StudentTipMaster.request.RefreshTokenRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Value("${refreshToken.life}")
    private Long refreshTokenLifeTime;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository, JwtService jwtService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public RefreshToken createRefreshToken(String username){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("A felhasználó nem található."));
        invalidateRefreshToken(user);
        RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(refreshTokenLifeTime))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public LoginResponse verifyRefreshToken(RefreshTokenRequest refreshTokenRequest) throws TokenNotFoundException {
        return findByToken(refreshTokenRequest.getToken())
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String accessToken = jwtService.GenerateToken(user.getUsername());
                    return LoginResponse.builder()
                            .accessToken(accessToken)
                            .refreshToken(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() ->new TokenNotFoundException("A Refresh token nincs az adatbázisban!"));
    }

    private void invalidateRefreshToken(User user){
        Optional<RefreshToken> token = refreshTokenRepository.findByUserId(user.getId());
        token.ifPresent(refreshTokenRepository::delete);
    }
    private Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    private RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token lejárt. Kérlek jelentkezz be újra! ");
        }
        return token;
    }

}
