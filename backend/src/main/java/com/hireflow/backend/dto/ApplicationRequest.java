package com.hireflow.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationRequest {
    private Long candidateId;
    private Long jobId;
    private String coverNote;
}
