package com.example.appointmentscheduler;

import com.example.appointmentscheduler.model.Appointment;
import com.example.appointmentscheduler.model.Doctor;
import com.example.appointmentscheduler.model.Patient;
import com.example.appointmentscheduler.model.enums.Status;
import com.example.appointmentscheduler.repository.AppointmentRepository;
import com.example.appointmentscheduler.repository.DoctorRepository;
import com.example.appointmentscheduler.repository.PatientRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializationConfig {
  private final PatientRepository patientRepository;
  private final DoctorRepository doctorRepository;
  private final AppointmentRepository appointmentRepository;

  @Autowired
  public DatabaseInitializationConfig(PatientRepository patientRepository, DoctorRepository doctorRepository, AppointmentRepository appointmentRepository) {
    this.patientRepository = patientRepository;
    this.doctorRepository = doctorRepository;
    this.appointmentRepository = appointmentRepository;
  }

  @PostConstruct
  public void initializeDatabase() {
    Patient patient1 = new Patient();
    patient1.setName("John Doe");
    patient1.setEmail("john-doe@gmail.com");

    Patient patient2 = new Patient();
    patient2.setName("Jane Smith");
    patient2.setEmail("jane-smith@gmail.com");

    patientRepository.save(patient1);
    patientRepository.save(patient2);

    Doctor doctor1 = new Doctor();
    doctor1.setName("Dr. Smith");

    Doctor doctor2 = new Doctor();
    doctor2.setName("Dr. Johnson");

    doctorRepository.save(doctor1);
    doctorRepository.save(doctor2);

    Appointment appointment1 = new Appointment();
    appointment1.setStatus(Status.SCHEDULED);
    LocalDateTime startTime1 = LocalDateTime.of(2025, 5, 16, 11, 30);
    appointment1.setStartTime(startTime1);

    LocalDateTime endTime1 = LocalDateTime.of(2025, 5, 16, 12, 0);
    appointment1.setEndTime(endTime1);

    Appointment appointment2 = new Appointment();

    appointment2.setStatus(Status.COMPLETED);

    LocalDateTime startTime2 = LocalDateTime.of(2024, 5, 16, 11, 30);
    appointment2.setStartTime(startTime2);

    LocalDateTime endTime2 = LocalDateTime.of(2024, 5, 16, 12, 0);
    appointment2.setEndTime(endTime2);

    appointmentRepository.save(appointment1);
    appointmentRepository.save(appointment2);
  }
}
