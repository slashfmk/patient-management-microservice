package com.oxygenik.patientservice.dto;


import lombok.Data;

@Data
public class PatientResponseDTO {
    private String id;
    private String name;
    private String address;
    private String email;
    private String dateOfBirth;
}
