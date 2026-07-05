package com.hireflow.backend.service;

import com.hireflow.backend.dto.LoginRequest;
import com.hireflow.backend.dto.LoginResponse;
import com.hireflow.backend.dto.RegisterRequest;

public interface AuthService {
    LoginResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request);
}
