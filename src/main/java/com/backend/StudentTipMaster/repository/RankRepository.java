package com.backend.StudentTipMaster.repository;

import com.backend.StudentTipMaster.entity.Credential;
import com.backend.StudentTipMaster.entity.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RankRepository extends JpaRepository<Rank, UUID> {
}
