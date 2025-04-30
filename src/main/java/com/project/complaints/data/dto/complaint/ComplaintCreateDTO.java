package com.project.complaints.data.dto.complaint;

import com.project.complaints.model.embedded.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ComplaintCreateDTO {

    @NotBlank
    @Size(min=1, max = 100)
    private String title;

    @NotBlank
    @Size(min=1, max = 255)
    private String description;
    private Address address;
    private String imageUrl;
    private boolean isAnonymous;

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
}
