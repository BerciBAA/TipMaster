package com.backend.StudentTipMaster.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "role_table")
public class Role extends Audit  {

    @Id
    @UuidGenerator
    private UUID id;
    @Column(unique=true, nullable = false)
    private String role;

}
