package com.project.complaints.data.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO (
        @NotBlank String email,
        @NotBlank String password
) {}
