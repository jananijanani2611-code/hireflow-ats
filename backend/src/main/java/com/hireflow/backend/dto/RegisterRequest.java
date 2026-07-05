package com.hireflow.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String role; // ADMIN, RECRUITER, CANDIDATE (optional, defaults to CANDIDATE)
}
