package com.example.appointmentscheduler.web;

import com.example.appointmentscheduler.dto.AppointmentRequestDto;
import com.example.appointmentscheduler.dto.CreateAppointmentDto;
import com.example.appointmentscheduler.dto.DoctorRequestDto;
import com.example.appointmentscheduler.model.Appointment;
import com.example.appointmentscheduler.model.enums.Status;
import com.example.appointmentscheduler.service.AppointmentService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/appointments"})
public class AppointmentController {
  private final AppointmentService appointmentService;

  @Autowired
  public AppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @PostMapping({"/available"})
  public List<String> getAvailableDoctorAppointmentsForGivenDay(@RequestBody DoctorRequestDto doctorRequestDto) {
    return appointmentService.getAvailableDoctorAppointmentsForGivenDay(
      doctorRequestDto.getDoctorId(),
      doctorRequestDto.getChosenDay());
  }

  @PostMapping({"/patient"})
  public List<Appointment> getAppointmentsForPatient(@RequestParam("patientId") Long patientId) {
    return appointmentService.getAppointmentsForPatient(patientId);
  }

  @PostMapping({"/patient/status"})
  public List<Appointment> getAppointmentsForPatientByStatus(@RequestBody AppointmentRequestDto appointmentRequestDto) {
    return appointmentService.getAppointmentsForPatientByStatus(
      appointmentRequestDto.getPatientId(),
      appointmentRequestDto.getStatus());
  }

  @GetMapping({"/fetch/{status}"})
  public List<Appointment> getAppointmentsByStatus(@PathVariable String status) {
    Status appointmentStatus = Status.valueOf(status.toUpperCase());
    return appointmentService.getAppointmentsByStatus(appointmentStatus);
  }

  @GetMapping({"/complete-past-appointments"})
  public List<Appointment> completePastAppointments() {
    return appointmentService.markAppointmentsAsCompleted();
  }

  @GetMapping
  public List<Appointment> getAllAppointments() {
    return appointmentService.getAllAppointments();
  }

  @GetMapping({"/{id}"})
  public Optional<Appointment> getAppointmentById(@PathVariable Long id) {
    return appointmentService.getAppointmentById(id);
  }

  @PostMapping({"/add"})
  public Appointment createAppointment(@RequestBody CreateAppointmentDto createAppointmentDTO) {
    return appointmentService.saveAppointment(createAppointmentDTO);
  }

  @DeleteMapping({"/{id}"})
  public void deleteAppointment(@PathVariable Long id) {
    appointmentService.deleteAppointment(id);
  }
}
