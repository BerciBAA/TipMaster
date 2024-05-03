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
    private UUID id;
    private String roomName;
    private String owner;
    private List<User> users;
    private List<String> temporaryUsers;
}
