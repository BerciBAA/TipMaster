package com.backend.StudentTipMaster.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> adminTest(){
        return ResponseEntity.ok().body("adminTest");
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/user")
    public ResponseEntity<String> userTest(){
        return ResponseEntity.ok().body("userTest");
    }

    @GetMapping("/all")
    public ResponseEntity<String> allTest(){
        return ResponseEntity.ok().body("allTest");
    }
}
