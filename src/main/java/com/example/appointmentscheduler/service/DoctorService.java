package com.example.appointmentscheduler.service;

import com.example.appointmentscheduler.dto.CreateDoctorDto;
import com.example.appointmentscheduler.model.Doctor;
import java.util.List;
import java.util.Optional;

public interface DoctorService {
  List<Doctor> getAllDoctors();

  Optional<Doctor> getDoctorById(Long id);

  Doctor saveDoctor(CreateDoctorDto doctorDto);

  void deleteDoctor(Long id);
}
