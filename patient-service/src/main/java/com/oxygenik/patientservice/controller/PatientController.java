package com.oxygenik.patientservice.controller;


import com.oxygenik.patientservice.dto.PatientRequestDTO;
import com.oxygenik.patientservice.dto.PatientResponseDTO;
import com.oxygenik.patientservice.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        var savedPatient = this.patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok().body(savedPatient);
    }
}
