package com.backend.StudentTipMaster.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRoomMessage {
    @NotBlank(message = "A szoba név nem lehet üres!")
    @NotNull(message = "A szoba név nem lehet null")
    private String roomName;
    private UUID userId;
    private String temporaryUsername;
}
