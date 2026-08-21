package com.oxygenik.patientservice.service;

import com.oxygenik.patientservice.dto.PatientRequestDTO;
import com.oxygenik.patientservice.dto.PatientResponseDTO;
import com.oxygenik.patientservice.exception.EmailAlreadyExistsException;
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

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient of this email " + patientRequestDTO.getEmail() + " already exists!");
        }

        var savedPatient  = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(savedPatient);
    }

}
