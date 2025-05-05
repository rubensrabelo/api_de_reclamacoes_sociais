package com.project.complaints.service;

import com.project.complaints.model.Tag;
import com.project.complaints.repository.TagRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private final TagRepository repository;
    private final ModelMapper modelMapper;

    public TagService(TagRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Tag createOrGetTag(String name) {
        Tag object = repository.findByNameIgnoreCase(name);
        if (object == null) object = repository.save(new Tag(name));
        return object;
    }
}
