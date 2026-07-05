package com.hireflow.backend.service.impl;

import com.hireflow.backend.dto.JobRequest;
import com.hireflow.backend.dto.JobResponse;
import com.hireflow.backend.entity.Job;
import com.hireflow.backend.exception.ResourceNotFoundException;
import com.hireflow.backend.repository.JobRepository;
import com.hireflow.backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;

    @Override
    public JobResponse createJob(JobRequest request) {
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setDepartment(request.getDepartment());
        job.setLocation(request.getLocation());
        job.setEmploymentType(request.getEmploymentType());
        job.setExperienceRequired(request.getExperienceRequired());
        job.setSkillsRequired(request.getSkillsRequired());
        job.setSalaryMin(request.getSalaryMin());
        job.setSalaryMax(request.getSalaryMax());
        job.setPostedBy(request.getPostedBy());
        job.setStatus(Job.JobStatus.OPEN);
        job.setCreatedAt(LocalDateTime.now());
        job.setUpdatedAt(LocalDateTime.now());

        Job saved = jobRepository.save(job);
        return JobResponse.fromEntity(saved);
    }

    @Override
    public JobResponse getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));
        return JobResponse.fromEntity(job);
    }

    @Override
    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(JobResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> getJobsByStatus(String status) {
        Job.JobStatus jobStatus = Job.JobStatus.valueOf(status.toUpperCase());
        return jobRepository.findByStatus(jobStatus)
                .stream()
                .map(JobResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse updateJob(Long id, JobRequest request) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setDepartment(request.getDepartment());
        job.setLocation(request.getLocation());
        job.setEmploymentType(request.getEmploymentType());
        job.setExperienceRequired(request.getExperienceRequired());
        job.setSkillsRequired(request.getSkillsRequired());
        job.setSalaryMin(request.getSalaryMin());
        job.setSalaryMax(request.getSalaryMax());
        job.setUpdatedAt(LocalDateTime.now());

        Job updated = jobRepository.save(job);
        return JobResponse.fromEntity(updated);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));
        jobRepository.delete(job);
    }
}
