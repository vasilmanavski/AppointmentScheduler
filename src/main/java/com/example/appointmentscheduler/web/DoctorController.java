package com.example.appointmentscheduler.web;

import com.example.appointmentscheduler.dto.CreateDoctorDto;
import com.example.appointmentscheduler.model.Doctor;
import com.example.appointmentscheduler.service.DoctorService;
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
@RequestMapping({"/doctors"})
public class DoctorController {
  private final DoctorService doctorService;

  @Autowired
  public DoctorController(DoctorService doctorService) {
    this.doctorService = doctorService;
  }

  @GetMapping
  public List<Doctor> getAllDoctors() {
    return doctorService.getAllDoctors();
  }

  @GetMapping({"/{id}"})
  public Optional<Doctor> getDoctorById(@PathVariable Long id) {
    return doctorService.getDoctorById(id);
  }

  @PostMapping
  public Doctor createDoctor(@RequestBody CreateDoctorDto doctor) {
    return doctorService.saveDoctor(doctor);
  }

  @DeleteMapping({"/{id}"})
  public void deleteDoctor(@PathVariable Long id) {
    doctorService.deleteDoctor(id);
  }
}
