package com.project.complaints.data.dto.complaint;

import com.project.complaints.model.embedded.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class ComplaintCreateDTO {

    @NotBlank
    @Size(min=1, max = 100)
    private String title;

    @Size(max = 255)
    private String description;
    private Address address;
    private String imageUrl;
    private boolean isAnonymous;

    private Set<String> tagsName = new HashSet<>();

    private ComplaintCreateDTO() {
    }

    public ComplaintCreateDTO(String title, String description, Address address, String imageUrl, boolean isAnonymous) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.imageUrl = imageUrl;
        this.isAnonymous = isAnonymous;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public Set<String> getTagsName() {
        return tagsName;
    }

    public void setTagsName(Set<String> tagsName) {
        this.tagsName = tagsName;
    }
}
