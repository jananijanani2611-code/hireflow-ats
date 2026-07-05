package com.hireflow.backend.service.impl;

import com.hireflow.backend.dto.ApplicationRequest;
import com.hireflow.backend.dto.ApplicationResponse;
import com.hireflow.backend.entity.Application;
import com.hireflow.backend.entity.Candidate;
import com.hireflow.backend.entity.Job;
import com.hireflow.backend.exception.ResourceNotFoundException;
import com.hireflow.backend.repository.ApplicationRepository;
import com.hireflow.backend.repository.CandidateRepository;
import com.hireflow.backend.repository.JobRepository;
import com.hireflow.backend.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private JobRepository jobRepository;

    @Override
    public ApplicationResponse applyToJob(ApplicationRequest request) {
        if (applicationRepository.existsByCandidateIdAndJobId(request.getCandidateId(), request.getJobId())) {
            throw new IllegalStateException("Candidate has already applied to this job");
        }

        Candidate candidate = candidateRepository.findById(request.getCandidateId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Candidate not found with id: " + request.getCandidateId()));

        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Job not found with id: " + request.getJobId()));

        Application application = new Application();
        application.setCandidate(candidate);
        application.setJob(job);
        application.setCoverNote(request.getCoverNote());
        application.setStatus(Application.ApplicationStatus.APPLIED);
        application.setAppliedAt(LocalDateTime.now());
        application.setUpdatedAt(LocalDateTime.now());

        Application saved = applicationRepository.save(application);
        return ApplicationResponse.fromEntity(saved);
    }

    @Override
    public ApplicationResponse getApplicationById(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));
        return ApplicationResponse.fromEntity(application);
    }

    @Override
    public List<ApplicationResponse> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(ApplicationResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponse> getApplicationsByCandidate(Long candidateId) {
        return applicationRepository.findByCandidateId(candidateId)
                .stream()
                .map(ApplicationResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponse> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(ApplicationResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationResponse updateStatus(Long id, String status) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));

        Application.ApplicationStatus newStatus = Application.ApplicationStatus.valueOf(status.toUpperCase());
        application.setStatus(newStatus);
        application.setUpdatedAt(LocalDateTime.now());

        Application updated = applicationRepository.save(application);
        return ApplicationResponse.fromEntity(updated);
    }

    @Override
    public void withdrawApplication(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));
        applicationRepository.delete(application);
    }
}
