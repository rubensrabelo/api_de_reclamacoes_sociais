package com.project.complaints.data.dto.complaint;

import com.project.complaints.model.embedded.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ComplaintUpdateDTO {

    @Size(min=1, max = 100)
    private String title;

    @Size(max = 255)
    private String description;
    private String imageUrl;

    private ComplaintUpdateDTO() {
    }

    public ComplaintUpdateDTO(String title, String description, String imageUrl) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
