package com.hireflow.backend.service.impl;

import com.hireflow.backend.dto.DashboardResponse;
import com.hireflow.backend.entity.Application;
import com.hireflow.backend.entity.Job;
import com.hireflow.backend.repository.ApplicationRepository;
import com.hireflow.backend.repository.CandidateRepository;
import com.hireflow.backend.repository.JobRepository;
import com.hireflow.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public DashboardResponse getDashboardStats() {
        long totalCandidates = candidateRepository.count();
        long totalJobs = jobRepository.count();
        long openJobs = jobRepository.findByStatus(Job.JobStatus.OPEN).size();
        long totalApplications = applicationRepository.count();
        long shortlisted = applicationRepository.findByStatus(Application.ApplicationStatus.SHORTLISTED).size();
        long hired = applicationRepository.findByStatus(Application.ApplicationStatus.HIRED).size();
        long rejected = applicationRepository.findByStatus(Application.ApplicationStatus.REJECTED).size();

        return new DashboardResponse(
                totalCandidates,
                totalJobs,
                openJobs,
                totalApplications,
                shortlisted,
                hired,
                rejected
        );
    }
}
