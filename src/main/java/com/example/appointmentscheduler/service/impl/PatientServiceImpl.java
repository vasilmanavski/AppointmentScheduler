package com.example.appointmentscheduler.service.impl;

import com.example.appointmentscheduler.dto.CreatePatientDto;
import com.example.appointmentscheduler.mapper.EntityMapper;
import com.example.appointmentscheduler.model.Patient;
import com.example.appointmentscheduler.repository.PatientRepository;
import com.example.appointmentscheduler.service.PatientService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
  private final PatientRepository patientRepository;
  private final EntityMapper entityMapper;

  @Autowired
  public PatientServiceImpl(PatientRepository patientRepository, EntityMapper entityMapper) {
    this.patientRepository = patientRepository;
    this.entityMapper = entityMapper;
  }

  public List<Patient> getAllPatients() {
    return patientRepository.findAll();
  }

  public Optional<Patient> getPatientById(Long id) {
    return patientRepository.findById(id);
  }

  public Patient savePatient(CreatePatientDto patientDto) {
    Patient patient = entityMapper.toEntity(patientDto);
    return patientRepository.save(patient);
  }

  public void deletePatient(Long id) {
    patientRepository.deleteById(id);
  }
}
