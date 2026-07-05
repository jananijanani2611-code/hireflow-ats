package com.hireflow.backend.service;

import com.hireflow.backend.dto.ApplicationRequest;
import com.hireflow.backend.dto.ApplicationResponse;

import java.util.List;

public interface ApplicationService {
    ApplicationResponse applyToJob(ApplicationRequest request);
    ApplicationResponse getApplicationById(Long id);
    List<ApplicationResponse> getAllApplications();
    List<ApplicationResponse> getApplicationsByCandidate(Long candidateId);
    List<ApplicationResponse> getApplicationsByJob(Long jobId);
    ApplicationResponse updateStatus(Long id, String status);
    void withdrawApplication(Long id);
}
