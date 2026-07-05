package com.hireflow.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    private String department;

    private String location;

    private String employmentType; // FULL_TIME, PART_TIME, INTERNSHIP, CONTRACT

    private String experienceRequired;

    @Column(length = 1000)
    private String skillsRequired;

    private Double salaryMin;

    private Double salaryMax;

    @Enumerated(EnumType.STRING)
    private JobStatus status = JobStatus.OPEN;

    private String postedBy;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    public enum JobStatus {
        OPEN, CLOSED, ON_HOLD
    }
}
