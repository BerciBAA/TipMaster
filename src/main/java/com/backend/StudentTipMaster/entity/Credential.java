package com.backend.StudentTipMaster.entity;


import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class CredentialTable extends Audit {
    private UUID id;
    private String email;
    private String password;

    private int trackPoint;
    
}
