package com.example.appointmentscheduler.repository;

import com.example.appointmentscheduler.model.Appointment;
import com.example.appointmentscheduler.model.Doctor;
import com.example.appointmentscheduler.model.Patient;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
  List<Appointment> findAllByDoctorAndStartTimeGreaterThanAndEndTimeLessThan(Doctor doctor, LocalDateTime start, LocalDateTime finish);

  List<Appointment> findAllByPatient(Patient patient);

  List<Appointment> findAllByDoctor(Doctor doctorId);
}
