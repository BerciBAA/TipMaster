package com.backend.StudentTipMaster.request;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateRoomMessage {
    private String roomName;
    private UUID userId;
    private String temporaryUsername;
}
