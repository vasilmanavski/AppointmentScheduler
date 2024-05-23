package com.example.appointmentscheduler.service;

import com.example.appointmentscheduler.dto.CreatePatientDto;
import com.example.appointmentscheduler.model.Patient;
import java.util.List;
import java.util.Optional;

public interface PatientService {
  List<Patient> getAllPatients();

  Optional<Patient> getPatientById(Long id);

  Patient savePatient(CreatePatientDto patientDto);

  void deletePatient(Long id);
}
