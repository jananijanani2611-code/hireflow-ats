package com.hireflow.backend.service;

import com.hireflow.backend.dto.JobRequest;
import com.hireflow.backend.dto.JobResponse;

import java.util.List;

public interface JobService {
    JobResponse createJob(JobRequest request);
    JobResponse getJobById(Long id);
    List<JobResponse> getAllJobs();
    List<JobResponse> getJobsByStatus(String status);
    JobResponse updateJob(Long id, JobRequest request);
    void deleteJob(Long id);
}
