package com.example.appointmentscheduler.service;

import com.example.appointmentscheduler.dto.CreateAppointmentDto;
import com.example.appointmentscheduler.model.Appointment;
import com.example.appointmentscheduler.model.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {
  List<Appointment> getAppointmentsForPatient(Long patientId);

  List<Appointment> getAppointmentsForPatientByStatus(Long patientId, Status status);

  List<String> getAvailableDoctorAppointmentsForGivenDay(Long doctorId, LocalDateTime chosenDay);

  List<Appointment> markAppointmentsAsCompleted();

  List<Appointment> getAppointmentsByStatus(Status status);

  List<Appointment> getAllAppointments();

  Optional<Appointment> getAppointmentById(Long id);

  Appointment saveAppointment(CreateAppointmentDto appointmentDTO);

  void deleteAppointment(Long id);
}
