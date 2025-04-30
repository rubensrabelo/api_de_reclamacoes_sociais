package com.project.complaints.model.embedded;

public record Address (
        String street,
        String number,
        String neighborhood,
        String city,
        String state,
        String postalCode,
        String complement
) {}
