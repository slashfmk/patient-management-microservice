package com.oxygenik.patientservice.service;

import com.oxygenik.patientservice.dto.PatientRequestDTO;
import com.oxygenik.patientservice.dto.PatientResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IPatientService {

    List<PatientResponseDTO> getPatients();
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO);
    PatientResponseDTO getPatient(UUID id);
    PatientResponseDTO deletePatient(UUID id);
}
