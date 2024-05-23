package com.example.appointmentscheduler.web;

import com.example.appointmentscheduler.dto.CreatePatientDto;
import com.example.appointmentscheduler.model.Patient;
import com.example.appointmentscheduler.service.PatientService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/patients"})
public class PatientController {
  private final PatientService patientService;

  @Autowired
  public PatientController(PatientService patientService) {
    this.patientService = patientService;
  }

  @GetMapping
  public List<Patient> getAllPatients() {
    return patientService.getAllPatients();
  }

  @GetMapping({"/{id}"})
  public Optional<Patient> getPatientById(@PathVariable Long id) {
    return patientService.getPatientById(id);
  }

  @PostMapping
  public Patient createPatient(@RequestBody CreatePatientDto patientDto) {
    return patientService.savePatient(patientDto);
  }

  @DeleteMapping({"/{id}"})
  public void deletePatient(@PathVariable Long id) {
    patientService.deletePatient(id);
  }
}
