package com.hireflow.backend.service.impl;

import com.hireflow.backend.dto.CandidateRequest;
import com.hireflow.backend.dto.CandidateResponse;
import com.hireflow.backend.entity.Candidate;
import com.hireflow.backend.exception.ResourceNotFoundException;
import com.hireflow.backend.repository.CandidateRepository;
import com.hireflow.backend.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public CandidateResponse createCandidate(CandidateRequest request) {
        Candidate candidate = new Candidate();
        candidate.setFullName(request.getFullName());
        candidate.setEmail(request.getEmail());
        candidate.setPhone(request.getPhone());
        candidate.setSkills(request.getSkills());
        candidate.setExperience(request.getExperience());

        try {
            candidate.setStatus(request.getStatus() != null
                    ? Candidate.CandidateStatus.valueOf(request.getStatus().toUpperCase())
                    : Candidate.CandidateStatus.ACTIVE);
        } catch (IllegalArgumentException ex) {
            candidate.setStatus(Candidate.CandidateStatus.ACTIVE);
        }

        candidate.setCreatedAt(LocalDateTime.now());
        candidate.setUpdatedAt(LocalDateTime.now());

        Candidate saved = candidateRepository.save(candidate);
        return CandidateResponse.fromEntity(saved);
    }

    @Override
    public CandidateResponse getCandidateById(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + id));
        return CandidateResponse.fromEntity(candidate);
    }

    @Override
    public List<CandidateResponse> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(CandidateResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateResponse updateCandidate(Long id, CandidateRequest request) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + id));

        candidate.setFullName(request.getFullName());
        candidate.setEmail(request.getEmail());
        candidate.setPhone(request.getPhone());
        candidate.setSkills(request.getSkills());
        candidate.setExperience(request.getExperience());

        if (request.getStatus() != null) {
            try {
                candidate.setStatus(Candidate.CandidateStatus.valueOf(request.getStatus().toUpperCase()));
            } catch (IllegalArgumentException ignored) {
                // keep existing status if invalid value supplied
            }
        }

        candidate.setUpdatedAt(LocalDateTime.now());

        Candidate updated = candidateRepository.save(candidate);
        return CandidateResponse.fromEntity(updated);
    }

    @Override
    public void deleteCandidate(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with id: " + id));
        candidateRepository.delete(candidate);
    }
}
