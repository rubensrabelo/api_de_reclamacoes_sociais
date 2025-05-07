package com.project.complaints.data.dto.complaint;

import com.project.complaints.data.dto.tag.TagResponseDTO;
import com.project.complaints.data.dto.auth.RegisterResponseDTO;
import com.project.complaints.model.embedded.Address;
import com.project.complaints.model.enums.StatusEnum;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ComplaintResponseDTO extends RepresentationModel<ComplaintResponseDTO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String description;
    private Address address;
    private StatusEnum status;
    private String imageUrl;
    private Instant createdDate;
    private Instant updatedDate;
    private boolean isAnonymous;

    private RegisterResponseDTO user;
    private Set<TagResponseDTO> tags = new HashSet<>();

    private ComplaintResponseDTO() {
    }

    public ComplaintResponseDTO(String title, String description, Address address, String imageUrl, boolean isAnonymous, RegisterResponseDTO user) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.imageUrl = imageUrl;
        this.isAnonymous = isAnonymous;
        this.user = user;
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

    public RegisterResponseDTO getUser() {
        return user;
    }

    public void setUser(RegisterResponseDTO user) {
        this.user = user;
    }

    public Set<TagResponseDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagResponseDTO> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ComplaintResponseDTO complaint = (ComplaintResponseDTO) o;
        return isAnonymous == complaint.isAnonymous && Objects.equals(id, complaint.id) && Objects.equals(title, complaint.title) && Objects.equals(description, complaint.description) && Objects.equals(address, complaint.address) && status == complaint.status && Objects.equals(imageUrl, complaint.imageUrl) && Objects.equals(createdDate, complaint.createdDate) && Objects.equals(updatedDate, complaint.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, address, status, imageUrl, createdDate, updatedDate, isAnonymous);
    }
}
