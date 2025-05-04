package com.project.complaints.model;

import com.project.complaints.model.embedded.Address;
import com.project.complaints.model.enums.StatusEnum;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Document(collection = "complaints")
public class Complaint implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String title;
    private String description;
    private Address address;
    private StatusEnum status;
    private String imageUrl;

    @CreatedDate
    private Instant createdDate;

    @LastModifiedDate
    private Instant updatedDate;
    private boolean isAnonymous;

    @DBRef
    private Set<Tag> tags = new HashSet<>();

    private Complaint() {
        this.status = StatusEnum.PENDING;
    }

    public Complaint(String title, String description, Address address, String imageUrl, boolean isAnonymous) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.imageUrl = imageUrl;
        this.isAnonymous = isAnonymous;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Complaint complaint = (Complaint) o;
        return isAnonymous == complaint.isAnonymous && Objects.equals(id, complaint.id) && Objects.equals(title, complaint.title) && Objects.equals(description, complaint.description) && Objects.equals(address, complaint.address) && status == complaint.status && Objects.equals(imageUrl, complaint.imageUrl) && Objects.equals(createdDate, complaint.createdDate) && Objects.equals(updatedDate, complaint.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, address, status, imageUrl, createdDate, updatedDate, isAnonymous);
    }
}
