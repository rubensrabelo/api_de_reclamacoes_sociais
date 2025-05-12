package com.project.complaints.controller;

import com.project.complaints.controller.docs.AuthControllerDocs;
import com.project.complaints.data.dto.auth.LoginRequestDTO;
import com.project.complaints.data.dto.auth.LoginResponseDTO;
import com.project.complaints.data.dto.auth.RegisterRequestDTO;
import com.project.complaints.data.dto.auth.RegisterResponseDTO;
import com.project.complaints.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthControllerDocs {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        LoginResponseDTO responseDTO = authService.login(dto);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping("/register")
    @Override
    public ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO dto) {
        RegisterResponseDTO responseDTO = authService.register(dto);
        return ResponseEntity.ok().body(responseDTO);
    }
}
