package com.oxygenik.patientservice.service;

import com.oxygenik.patientservice.dto.PatientRequestDTO;
import com.oxygenik.patientservice.dto.PatientResponseDTO;
import com.oxygenik.patientservice.exception.EmailAlreadyExistsException;
import com.oxygenik.patientservice.exception.PatientNotFoundException;
import com.oxygenik.patientservice.mapper.PatientMapper;
import com.oxygenik.patientservice.model.Patient;
import com.oxygenik.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService implements IPatientService {

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

    @Override
    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {

        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("A patient of this email " + patientRequestDTO.getEmail() + " already exists!");
        }

        var savedPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(savedPatient);
    }

    @Override
    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {

        var foundPatient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient with id " + id + " not found!"));

        if (patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("A patient of this email " + patientRequestDTO.getEmail() + " already exists!");
        }

        foundPatient.setEmail(patientRequestDTO.getEmail());
        foundPatient.setName(patientRequestDTO.getName());
        foundPatient.setAddress(patientRequestDTO.getAddress());
        foundPatient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        var updatedPatient = patientRepository.save(foundPatient);

        return PatientMapper.toDTO(updatedPatient);

    }

    @Override
    public PatientResponseDTO getPatient(UUID id) {
        return PatientMapper.toDTO(patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient with id " + id + " not found!")));
    }

    @Override
    public PatientResponseDTO deletePatient(UUID id) {
        var foundPatient = patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException("Patient with id " + id + " not found!"));
        patientRepository.delete(foundPatient);
        return PatientMapper.toDTO(foundPatient);
    }

}
