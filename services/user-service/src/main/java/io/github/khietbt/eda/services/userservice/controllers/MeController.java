package io.github.khietbt.eda.services.userservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/me")
@RestController
@Slf4j
public class MeController {
    @GetMapping
    public ResponseEntity<?> me(Authentication authentication) {
        log.info("Authentication received: {}", authentication.getName());
        return ResponseEntity.ok(authentication.getDetails());
    }
}
