package com.example.appointmentscheduler.service.impl;

import com.example.appointmentscheduler.dto.CreateDoctorDto;
import com.example.appointmentscheduler.mapper.EntityMapper;
import com.example.appointmentscheduler.model.Doctor;
import com.example.appointmentscheduler.repository.DoctorRepository;
import com.example.appointmentscheduler.service.DoctorService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
  private final DoctorRepository doctorRepository;
  private final EntityMapper entityMapper;

  @Autowired
  public DoctorServiceImpl(DoctorRepository doctorRepository, EntityMapper entityMapper) {
    this.doctorRepository = doctorRepository;
    this.entityMapper = entityMapper;
  }

  public List<Doctor> getAllDoctors() {
    return doctorRepository.findAll();
  }

  public Optional<Doctor> getDoctorById(Long id) {
    return doctorRepository.findById(id);
  }

  public Doctor saveDoctor(CreateDoctorDto doctorDto) {

    Doctor doctor = entityMapper.toEntity(doctorDto);
    return doctorRepository.save(doctor);
  }

  public void deleteDoctor(Long id) {
    doctorRepository.deleteById(id);
  }
}
