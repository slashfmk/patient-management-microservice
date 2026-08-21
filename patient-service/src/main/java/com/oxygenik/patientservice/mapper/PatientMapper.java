package com.oxygenik.patientservice.mapper;

import com.oxygenik.patientservice.dto.PatientRequestDTO;
import com.oxygenik.patientservice.dto.PatientResponseDTO;
import com.oxygenik.patientservice.model.Patient;

import java.time.LocalDate;

public class PatientMapper {

    public static PatientResponseDTO toDTO(Patient patient) {

        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();

        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientResponseDTO;
    }

    public static Patient toModel(PatientRequestDTO patientRequest) {

        var patient = new Patient();
        patient.setName(patientRequest.getName());
        patient.setAddress(patientRequest.getAddress());
        patient.setEmail(patientRequest.getEmail());
        patient.setRegisteredDate(LocalDate.parse(patientRequest.getRegisteredDate()));
        patient.setDateOfBirth(LocalDate.parse(patientRequest.getDateOfBirth()));

        return patient;
    }
}
