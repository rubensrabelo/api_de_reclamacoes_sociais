package com.project.complaints.repository;

import com.project.complaints.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {

    Tag findByNameIgnoreCase(String name);
}
