package com.hireflow.backend.service;

import com.hireflow.backend.dto.CandidateRequest;
import com.hireflow.backend.dto.CandidateResponse;

import java.util.List;

public interface CandidateService {
    CandidateResponse createCandidate(CandidateRequest request);
    CandidateResponse getCandidateById(Long id);
    List<CandidateResponse> getAllCandidates();
    CandidateResponse updateCandidate(Long id, CandidateRequest request);
    void deleteCandidate(Long id);
}
