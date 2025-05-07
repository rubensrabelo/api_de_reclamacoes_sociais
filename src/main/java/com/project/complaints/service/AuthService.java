package com.project.complaints.service;

import com.project.complaints.data.dto.auth.LoginRequestDTO;
import com.project.complaints.data.dto.auth.LoginResponseDTO;
import com.project.complaints.data.dto.auth.RegisterRequestDTO;
import com.project.complaints.data.dto.auth.RegisterResponseDTO;
import com.project.complaints.infra.security.TokenService;
import com.project.complaints.model.User;
import com.project.complaints.repository.UserRepository;
import com.project.complaints.service.exceptions.InvalidPasswordException;
import com.project.complaints.service.exceptions.ObjectNotFoundException;
import com.project.complaints.service.exceptions.UserAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final ModelMapper modelMapper;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenService tokenService,
            ModelMapper modelMapper
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.modelMapper = modelMapper;
    }

    public LoginResponseDTO login(LoginRequestDTO dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new ObjectNotFoundException("User not found."));
        if (passwordEncoder.matches(dto.password(), user.getPassword())) {
            String token = tokenService.generateToken(user);
            return new LoginResponseDTO(user.getName(), token);
        }
        throw new InvalidPasswordException("Invalid password.");
    }

    public RegisterResponseDTO register(RegisterRequestDTO dto) {
        Optional<User> user = userRepository.findByEmail(dto.email());

        if(user.isPresent())
            throw new UserAlreadyExistsException("User already exists.");

        User newUser = new User();
        newUser.setName(dto.name());
        newUser.setEmail(dto.email());
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        newUser = userRepository.save(newUser);

        return modelMapper.map(newUser, RegisterResponseDTO.class);
    }
}
