package com.hireflow.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidateRequest {
    private String fullName;
    private String email;
    private String phone;
    private String skills;
    private Integer experience;
    private String status;
}
