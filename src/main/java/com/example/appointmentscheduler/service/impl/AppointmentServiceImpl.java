package com.example.appointmentscheduler.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appointmentscheduler.dto.CreateAppointmentDto;
import com.example.appointmentscheduler.mapper.EntityMapper;
import com.example.appointmentscheduler.model.Appointment;
import com.example.appointmentscheduler.model.Doctor;
import com.example.appointmentscheduler.model.Patient;
import com.example.appointmentscheduler.model.enums.Status;
import com.example.appointmentscheduler.repository.AppointmentRepository;
import com.example.appointmentscheduler.repository.DoctorRepository;
import com.example.appointmentscheduler.repository.PatientRepository;
import com.example.appointmentscheduler.service.AppointmentService;
import com.example.appointmentscheduler.service.EmailService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

  private final AppointmentRepository appointmentRepository;
  private final DoctorRepository doctorRepository;
  private final PatientRepository patientRepository;
  private final EntityMapper entityMapper;
  private final EmailService emailService;

  @Autowired
  public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
    DoctorRepository doctorRepository,
    PatientRepository patientRepository,
    EntityMapper entityMapper,
    EmailService emailService) {
    this.appointmentRepository = appointmentRepository;
    this.doctorRepository = doctorRepository;
    this.patientRepository = patientRepository;
    this.entityMapper = entityMapper;
    this.emailService = emailService;
  }

  public List<Appointment> getAppointmentsForPatient(Long patientId) {
    Optional<Patient> patientOptional = patientRepository.findById(patientId);

    return patientOptional.map(appointmentRepository::findAllByPatient).orElse(List.of());
  }

  public List<Appointment> getAppointmentsForPatientByStatus(Long patientId, Status status) {
    Optional<Patient> patientOptional = patientRepository.findById(patientId);

    List<Appointment> appointments = patientOptional.map(appointmentRepository::findAllByPatient)
      .orElse(Collections.emptyList());

    return appointments.stream().filter(appointment -> appointment.getStatus() == status).collect(Collectors.toList());
  }

  public List<String> getAvailableDoctorAppointmentsForGivenDay(Long doctorId, LocalDateTime chosenDay) {
    LocalDateTime startOfDay = chosenDay.withHour(9).withMinute(0);
    LocalDateTime endOfDay = chosenDay.withHour(17).withMinute(0);

    Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);

    List<Appointment> appointments = optionalDoctor
      .map(doctor -> appointmentRepository.findAllByDoctorAndStartTimeGreaterThanAndEndTimeLessThan(doctor, startOfDay, endOfDay))
      .orElse(Collections.emptyList());

    List<LocalDateTime> bookedTimes = appointments.stream()
      .filter(appointment -> appointment.getStatus() == Status.SCHEDULED)
      .map(Appointment::getStartTime)
      .collect(Collectors.toList());

    List<LocalDateTime> timeSlots = Stream.iterate(startOfDay, time -> time.plusMinutes(30))
      .limit(Duration.between(startOfDay, endOfDay).toMinutes() / 30)
      .collect(Collectors.toList());

    return timeSlots.stream()
      .filter(time -> !bookedTimes.contains(time))
      .map(LocalDateTime::toString)
      .collect(Collectors.toList());
  }

  public List<Appointment> markAppointmentsAsCompleted() {
    List<Appointment> appointments = appointmentRepository.findAll();

    List<Appointment> pastAppointments = appointments.stream()
      .filter(appointment -> appointment.getEndTime().isBefore(LocalDateTime.now()))
      .filter(appointment -> appointment.getStatus() != Status.COMPLETED)
      .collect(Collectors.toList());

    pastAppointments.forEach(appointment -> appointment.setStatus(Status.COMPLETED));

    return appointmentRepository.saveAll(pastAppointments);
  }

  public List<Appointment> getAppointmentsByStatus(Status status) {
    return appointmentRepository.findAll().stream()
      .filter(appointment -> appointment.getStatus() == status)
      .collect(Collectors.toList());
  }

  public List<Appointment> getAllAppointments() {
    return appointmentRepository.findAll();
  }

  public Optional<Appointment> getAppointmentById(Long id) {
    return appointmentRepository.findById(id);
  }

  public Appointment saveAppointment(CreateAppointmentDto appointmentDTO) {
    Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
      .orElseThrow(() -> new IllegalArgumentException("Cannot save appointment for non-existing patient"));

    Doctor doctor = doctorRepository.findById(appointmentDTO.getDoctorId())
      .orElseThrow(() -> new IllegalArgumentException("Cannot save appointment for non-existing doctor"));

    Appointment appointment = entityMapper.toEntity(appointmentDTO, patient, doctor);
    return appointmentRepository.save(appointment);
  }

  public void deleteAppointment(Long id) {
    Appointment appointment = appointmentRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

    String patientEmail = appointment.getPatient().getEmail();
    appointmentRepository.delete(appointment);

    String text = String.format("Your appointment for %s has been canceled", appointment.getStartTime());
    String subject = "Email from appointment scheduler";

    emailService.sendEmail(patientEmail, subject, text);
  }
}
