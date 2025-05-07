package com.project.complaints.data.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(
        @NotBlank @Size(min = 3, max = 100) String name,
        @NotBlank String email,
        @NotBlank String password
) {}
