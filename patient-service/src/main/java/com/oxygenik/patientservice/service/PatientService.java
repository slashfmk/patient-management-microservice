package com.oxygenik.patientservice.service;

import com.oxygenik.patientservice.dto.PatientResponseDTO;
import com.oxygenik.patientservice.mapper.PatientMapper;
import com.oxygenik.patientservice.model.Patient;
import com.oxygenik.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients
                .stream()
                .map(PatientMapper::toDTO)
                .toList();
    }

}
