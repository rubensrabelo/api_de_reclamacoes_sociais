package com.project.complaints.controller;

import com.project.complaints.controller.docs.ComplaintControllerDocs;
import com.project.complaints.data.dto.complaint.ComplaintCreateDTO;
import com.project.complaints.data.dto.complaint.ComplaintResponseDTO;
import com.project.complaints.data.dto.complaint.ComplaintUpdateDTO;
import com.project.complaints.service.ComplaintService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("complaints/v1/")
public class ComplaintController implements ComplaintControllerDocs {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<PagedModel<EntityModel<ComplaintResponseDTO>>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {
        Direction sortDirection = direction.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "title"));
        return ResponseEntity.ok().body(complaintService.findAll(pageable));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity<ComplaintResponseDTO> findById(@PathVariable String id) {
        return ResponseEntity.ok().body(complaintService.findById(id));
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public ResponseEntity<ComplaintResponseDTO> create(@RequestBody ComplaintCreateDTO dtoCreate) {
        ComplaintResponseDTO response = complaintService.create(dtoCreate);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(
            value = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public ResponseEntity<ComplaintResponseDTO> update(
            @PathVariable String id,
            @RequestBody ComplaintUpdateDTO dtoUpdate
    ) {
        return ResponseEntity.ok().body(complaintService.update(id, dtoUpdate));
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<Void> delete(@PathVariable String id) {
        complaintService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
