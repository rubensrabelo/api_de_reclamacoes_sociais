package com.project.complaints.service;

import com.project.complaints.data.dto.complaint.ComplaintCreateDTO;
import com.project.complaints.data.dto.complaint.ComplaintResponseDTO;
import com.project.complaints.data.dto.complaint.ComplaintUpdateDTO;
import com.project.complaints.model.Complaint;
import com.project.complaints.repository.ComplaintRepository;
import com.project.complaints.service.exceptions.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ModelMapper modelMapper;

    public ComplaintService(ComplaintRepository complaintRepository, ModelMapper modelMapper) {
        this.complaintRepository = complaintRepository;
        this.modelMapper = modelMapper;
    }

    public List<ComplaintResponseDTO> findAll() {
      return complaintRepository.findAll().stream()
              .map(c -> modelMapper.map(c, ComplaintResponseDTO.class))
              .collect(Collectors.toList());
    }

    public ComplaintResponseDTO findById(String id) {
        var object = complaintRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Complaint with ID " + id + " not found."));
        return modelMapper.map(object, ComplaintResponseDTO.class);
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
        return modelMapper.map(object, ComplaintResponseDTO.class);
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
        return modelMapper.map(object, ComplaintResponseDTO.class);
    }

    public void delete(String id) {
        try {
            complaintRepository.deleteById(id);
        } catch (ObjectNotFoundException e) {
            throw new ObjectNotFoundException("Complaint with ID " + id + " not found.");
        }
    }

    private void updateData(Complaint object, ComplaintUpdateDTO updateDTO) {
        object.setTitle(updateDTO.getTitle());
        object.setDescription(updateDTO.getDescription());
        object.setImageUrl(updateDTO.getImageUrl());
    }
}
