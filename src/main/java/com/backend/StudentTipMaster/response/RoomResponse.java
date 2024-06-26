package com.backend.StudentTipMaster.response;


import com.backend.StudentTipMaster.entity.User;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomResponse {
    private UUID roomId;
    private String roomName;
    private UserDto owner;
    private List<UserDto> members;
}
