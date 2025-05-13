package com.project.complaints.controller;

import com.project.complaints.controller.docs.AddressControllerDocs;
import com.project.complaints.data.dto.complaint.ComplaintResponseDTO;
import com.project.complaints.model.embedded.Address;
import com.project.complaints.service.AddressService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address/v1")
public class AddressController implements AddressControllerDocs {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PutMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public ResponseEntity<ComplaintResponseDTO> update(
            @PathVariable String id,
            @RequestBody Address value
    ) {
        ComplaintResponseDTO dto = addressService.update(id, value);
        return ResponseEntity.ok().body(dto);
    }
}
