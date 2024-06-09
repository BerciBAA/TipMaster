package com.backend.StudentTipMaster.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "credential_table")
public class Credential extends Audit {
    @Id
    @UuidGenerator
    private UUID id;
    @Column(unique = true)
    private String email;
    private String password;
    private int trackPoint;
}
