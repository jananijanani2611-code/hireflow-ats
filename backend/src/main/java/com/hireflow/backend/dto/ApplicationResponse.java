package com.hireflow.backend.dto;

import com.hireflow.backend.entity.Application;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApplicationResponse {
    private Long id;
    private Long candidateId;
    private String candidateName;
    private Long jobId;
    private String jobTitle;
    private String status;
    private String coverNote;
    private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;

    public static ApplicationResponse fromEntity(Application a) {
        ApplicationResponse r = new ApplicationResponse();
        r.setId(a.getId());
        r.setCandidateId(a.getCandidate().getId());
        r.setCandidateName(a.getCandidate().getFullName());
        r.setJobId(a.getJob().getId());
        r.setJobTitle(a.getJob().getTitle());
        r.setStatus(a.getStatus().name());
        r.setCoverNote(a.getCoverNote());
        r.setAppliedAt(a.getAppliedAt());
        r.setUpdatedAt(a.getUpdatedAt());
        return r;
    }
}
