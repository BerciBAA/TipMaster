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
@Table(name = "role_table")
public class Role {

    @Id
    @UuidGenerator
    private UUID id;
    @Column(unique=true, nullable = false)
    private String role;

}
