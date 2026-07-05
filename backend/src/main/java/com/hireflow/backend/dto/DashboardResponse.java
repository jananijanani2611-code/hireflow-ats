package com.hireflow.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DashboardResponse {
    private long totalCandidates;
    private long totalJobs;
    private long openJobs;
    private long totalApplications;
    private long shortlisted;
    private long hired;
    private long rejected;
}
