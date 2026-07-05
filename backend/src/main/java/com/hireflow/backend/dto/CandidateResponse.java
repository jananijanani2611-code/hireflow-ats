package com.hireflow.backend.dto;

import com.hireflow.backend.entity.Candidate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CandidateResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String skills;
    private Integer experience;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CandidateResponse fromEntity(Candidate c) {
        CandidateResponse r = new CandidateResponse();
        r.setId(c.getId());
        r.setFullName(c.getFullName());
        r.setEmail(c.getEmail());
        r.setPhone(c.getPhone());
        r.setSkills(c.getSkills());
        r.setExperience(c.getExperience());
        r.setStatus(c.getStatus().name());
        r.setCreatedAt(c.getCreatedAt());
        r.setUpdatedAt(c.getUpdatedAt());
        return r;
    }
}
