package com.backend.StudentTipMaster.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "rank_table")
public class Rank extends Audit {
    @Id
    @UuidGenerator
    private UUID id;
    private String rankName;
    private Integer rankLimit;
}
