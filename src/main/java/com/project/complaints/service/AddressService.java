package com.project.complaints.service;

import com.project.complaints.controller.ComplaintController;
import com.project.complaints.data.dto.complaint.ComplaintCreateDTO;
import com.project.complaints.data.dto.complaint.ComplaintResponseDTO;
import com.project.complaints.data.dto.complaint.ComplaintUpdateDTO;
import com.project.complaints.model.Complaint;
import com.project.complaints.model.embedded.Address;
import com.project.complaints.repository.ComplaintRepository;
import com.project.complaints.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AddressService {

    private final ComplaintRepository complaintRepository;
    private final ModelMapper modelMapper;

    public AddressService(
            ComplaintRepository complaintRepository,
            ModelMapper modelMapper
            ) {
        this.complaintRepository = complaintRepository;
        this.modelMapper = modelMapper;
    }

    public ComplaintResponseDTO update(String id, Address value) {
        Complaint object = complaintRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Complaint not found."));
        object.setAddress(value);
        object = complaintRepository.save(object);
        ComplaintResponseDTO dto = modelMapper.map(object, ComplaintResponseDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    private void addHateoasLinks(ComplaintResponseDTO dto) {
        dto.add(linkTo(methodOn(ComplaintController.class).findAll(0, 10, "desc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(ComplaintController.class).findById(dto.getId())).withSelfRel().withType("GET"));

        dto.add(linkTo(methodOn(ComplaintController.class).create(null)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(ComplaintController.class).update(dto.getId(), null)).withRel("update").withType("PUT"));

        dto.add(linkTo(methodOn(ComplaintController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }
}
