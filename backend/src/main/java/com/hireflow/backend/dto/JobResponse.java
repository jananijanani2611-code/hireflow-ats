package com.hireflow.backend.dto;

import com.hireflow.backend.entity.Job;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String department;
    private String location;
    private String employmentType;
    private String experienceRequired;
    private String skillsRequired;
    private Double salaryMin;
    private Double salaryMax;
    private String status;
    private String postedBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static JobResponse fromEntity(Job job) {
        JobResponse r = new JobResponse();
        r.setId(job.getId());
        r.setTitle(job.getTitle());
        r.setDescription(job.getDescription());
        r.setDepartment(job.getDepartment());
        r.setLocation(job.getLocation());
        r.setEmploymentType(job.getEmploymentType());
        r.setExperienceRequired(job.getExperienceRequired());
        r.setSkillsRequired(job.getSkillsRequired());
        r.setSalaryMin(job.getSalaryMin());
        r.setSalaryMax(job.getSalaryMax());
        r.setStatus(job.getStatus().name());
        r.setPostedBy(job.getPostedBy());
        r.setCreatedAt(job.getCreatedAt());
        r.setUpdatedAt(job.getUpdatedAt());
        return r;
    }
}
