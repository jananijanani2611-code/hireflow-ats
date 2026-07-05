package com.hireflow.backend.repository;

import com.hireflow.backend.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByCandidateId(Long candidateId);
    List<Application> findByJobId(Long jobId);
    List<Application> findByStatus(Application.ApplicationStatus status);
    boolean existsByCandidateIdAndJobId(Long candidateId, Long jobId);
}
