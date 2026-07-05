package com.hireflow.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobRequest {
    private String title;
    private String description;
    private String department;
    private String location;
    private String employmentType;
    private String experienceRequired;
    private String skillsRequired;
    private Double salaryMin;
    private Double salaryMax;
    private String postedBy;
}
