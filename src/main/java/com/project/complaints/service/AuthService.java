package com.project.complaints.service;

import com.project.complaints.data.dto.auth.LoginRequestDTO;
import com.project.complaints.data.dto.auth.LoginResponseDTO;
import com.project.complaints.data.dto.auth.RegisterRequestDTO;
import com.project.complaints.data.dto.auth.RegisterResponseDTO;
import com.project.complaints.infra.security.TokenService;
import com.project.complaints.model.User;
import com.project.complaints.repository.UserRepository;
import com.project.complaints.service.exceptions.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if(dto.email() == null || dto.email().isBlank())
            throw new EmptyEmailException("Email is required.");

        if(dto.password() == null || dto.password().isBlank())
            throw new EmptyPasswordException("Password is required.");

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

        if(dto.name() == null || dto.name().isEmpty())
            throw new EmptyNameException("Email is required.");

        if(dto.name().isBlank() || dto.name().length() < 3 || dto.name().length() > 100)
            throw new InvalidNameSizeException("The name field must be between 3 and 100 characters.");

        if(dto.email() == null || dto.email().isBlank())
            throw new EmptyEmailException("Email is required.");

        if(!isValidEmail(dto.email()))
            throw new InvalidEmailException("The email provided is invalid.");

        if(dto.password() == null || dto.password().isBlank())
            throw new EmptyPasswordException("Password is required.");

        User newUser = new User();
        newUser.setName(dto.name());
        newUser.setEmail(dto.email());
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        newUser = userRepository.save(newUser);

        return modelMapper.map(newUser, RegisterResponseDTO.class);
    }

    private boolean isValidEmail(String email) {
        final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
