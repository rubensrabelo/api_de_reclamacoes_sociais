package com.project.complaints.controller.docs;

import com.project.complaints.data.dto.auth.LoginRequestDTO;
import com.project.complaints.data.dto.auth.LoginResponseDTO;
import com.project.complaints.data.dto.auth.RegisterRequestDTO;
import com.project.complaints.data.dto.auth.RegisterResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthControllerDocs {

    @Operation(summary = "User login",
            description = "Authenticates a user and returns a JWT token upon successful login.",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO dto);

    @Operation(summary = "User registration",
            description = "Registers a new user and returns basic information about the created account.",
            tags = {"Authentication"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = @Content(schema = @Schema(implementation = RegisterResponseDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Conflict", responseCode = "409", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    ResponseEntity<RegisterResponseDTO> register(@RequestBody RegisterRequestDTO dto);
}
