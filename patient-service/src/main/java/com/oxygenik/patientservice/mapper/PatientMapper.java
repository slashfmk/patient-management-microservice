package com.oxygenik.patientservice.mapper;

import com.oxygenik.patientservice.dto.PatientResponseDTO;
import com.oxygenik.patientservice.model.Patient;

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


//    public static Patient toPatientResponseDTO(PatientResponseDTO patient) {
//
//        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
//
//        patientResponseDTO.setName(patient.getName());
//        patientResponseDTO.setAddress(patient.getAddress());
//        patientResponseDTO.setEmail(patient.getEmail());
//        patientResponseDTO.setId(patient.getId().toString());
//        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());
//
//        return patientResponseDTO;
//    }
}
