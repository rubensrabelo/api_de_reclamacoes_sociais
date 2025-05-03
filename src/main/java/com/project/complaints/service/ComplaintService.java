package com.project.complaints.service;

import com.project.complaints.controller.ComplaintController;
import com.project.complaints.data.dto.complaint.ComplaintCreateDTO;
import com.project.complaints.data.dto.complaint.ComplaintResponseDTO;
import com.project.complaints.data.dto.complaint.ComplaintUpdateDTO;
import com.project.complaints.model.Complaint;
import com.project.complaints.repository.ComplaintRepository;
import com.project.complaints.service.exceptions.*;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ModelMapper modelMapper;
    private final PagedResourcesAssembler<ComplaintResponseDTO> assembler;

    public ComplaintService(
            ComplaintRepository complaintRepository,
            ModelMapper modelMapper,
            PagedResourcesAssembler<ComplaintResponseDTO> assembler
            ) {
        this.complaintRepository = complaintRepository;
        this.modelMapper = modelMapper;
        this.assembler = assembler;
    }

    public PagedModel<EntityModel<ComplaintResponseDTO>> findAll(Pageable pageable) {
        var dtoResponse = complaintRepository.findAll(pageable)
                .map(complaint -> {
                    var dto = modelMapper.map(complaint, ComplaintResponseDTO.class);
                    addHateoasLinks(dto);
                    return dto;
                });
        Link findAllLinks = WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(ComplaintController.class)
                        .findAll(
                                pageable.getPageNumber(),
                                pageable.getPageSize(),
                                String.valueOf(pageable.getSort())
                        )
        ).withSelfRel();

        return assembler.toModel(dtoResponse, findAllLinks);
    }

    public ComplaintResponseDTO findById(String id) {
        Complaint object = complaintRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Complaint with ID " + id + " not found."));
        ComplaintResponseDTO dto = modelMapper.map(object, ComplaintResponseDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public ComplaintResponseDTO create(ComplaintCreateDTO createDTO) {
        if(createDTO == null)
            throw new RequiredObjectIsNullException("Its is not allowed to persist a null object.");

        if(createDTO.getTitle() == null || createDTO.getTitle().isEmpty())
            throw new EmptyTitleException("The name cannot be null or blank.");

        if(createDTO.getTitle().isBlank() || createDTO.getTitle().length() > 100)
            throw new InvalidTitleSizeException("The title field must be between 1 and 100 characters.");

        if(createDTO.getDescription().length() > 255)
            throw new InvalidDescriptionSizeException("The description field cannot exceed 255 characters.");

        Complaint object = modelMapper.map(createDTO, Complaint.class);
        object = complaintRepository.save(object);
        ComplaintResponseDTO dto = modelMapper.map(object, ComplaintResponseDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public ComplaintResponseDTO update(String id, ComplaintUpdateDTO updateDTO) {
        if(updateDTO == null)
            throw new RequiredObjectIsNullException("Its is not allowed to persist a null object.");

        if(updateDTO.getTitle() == null || updateDTO.getTitle().isEmpty())
            throw new EmptyTitleException("The name cannot be null or blank.");

        if(updateDTO.getTitle().isBlank() || updateDTO.getTitle().length() > 100)
            throw new InvalidTitleSizeException("The title field must be between 1 and 100 characters.");

        if(updateDTO.getDescription().length() > 255)
            throw new InvalidDescriptionSizeException("The description field cannot exceed 255 characters.");

        Complaint object = complaintRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Complaint with ID " + id + " not found."));;
        updateData(object, updateDTO);
        object = complaintRepository.save(object);
        ComplaintResponseDTO dto = modelMapper.map(object, ComplaintResponseDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(String id) {
        try {
            complaintRepository.deleteById(id);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Complaint with ID " + id + " not found.");
        }
    }

    private void addHateoasLinks(ComplaintResponseDTO dto) {
        dto.add(linkTo(methodOn(ComplaintController.class).findAll(0, 10, "desc")).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(ComplaintController.class).findById(dto.getId())).withSelfRel().withType("GET"));

        ComplaintCreateDTO dtoCreated = new ComplaintCreateDTO(dto.getTitle(), dto.getDescription(), dto.getAddress(), dto.getImageUrl(), dto.isAnonymous());
        dto.add(linkTo(methodOn(ComplaintController.class).create(dtoCreated)).withRel("create").withType("POST"));

        ComplaintUpdateDTO dtoUpdated = new ComplaintUpdateDTO(dto.getTitle(), dto.getDescription(), dto.getImageUrl());
        dto.add(linkTo(methodOn(ComplaintController.class).update(dto.getId(), dtoUpdated)).withRel("update").withType("PUT"));

        dto.add(linkTo(methodOn(ComplaintController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

    private void updateData(Complaint object, ComplaintUpdateDTO updateDTO) {
        object.setTitle(updateDTO.getTitle());
        object.setDescription(updateDTO.getDescription());
        object.setImageUrl(updateDTO.getImageUrl());
    }
}
