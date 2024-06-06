package com.backend.StudentTipMaster.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.metamodel.mapping.internal.EmbeddableMappingTypeImpl;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_table")
public class User  extends Audit {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(unique=true, nullable = false)
    private String username;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    @OneToOne
    private Credential credential;

    @OneToMany(mappedBy = "owner")
    private List<Room> ownedRooms;

    @ManyToMany(mappedBy = "members")
    private List<Room> rooms;
}
