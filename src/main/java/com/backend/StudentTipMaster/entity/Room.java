package com.backend.StudentTipMaster.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room_table")
public class Room {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(unique=true, nullable = false)
    private String roomName;
    private String owner;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users;
    private List<String> temporaryUsers;
}
