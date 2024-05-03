package com.backend.StudentTipMaster.repository;

import com.backend.StudentTipMaster.entity.Role;
import com.backend.StudentTipMaster.entity.Room;
import com.backend.StudentTipMaster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, UUID> {
    Optional<Room> findByRoomName(String roomName);
}
