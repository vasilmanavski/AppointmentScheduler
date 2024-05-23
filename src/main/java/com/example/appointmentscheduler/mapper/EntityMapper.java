package com.example.appointmentscheduler.mapper;

import org.springframework.stereotype.Component;

import com.example.appointmentscheduler.dto.CreateAppointmentDto;
import com.example.appointmentscheduler.dto.CreateDoctorDto;
import com.example.appointmentscheduler.dto.CreatePatientDto;
import com.example.appointmentscheduler.model.Appointment;
import com.example.appointmentscheduler.model.Doctor;
import com.example.appointmentscheduler.model.Patient;

@Component
public class EntityMapper {

  public EntityMapper() {
  }

  public Appointment toEntity(CreateAppointmentDto appointment, Patient patient, Doctor doctor) {

    Appointment entity = new Appointment();

    entity.setPatient(patient);
    entity.setDoctor(doctor);
    entity.setStartTime(appointment.getStartTime());
    entity.setEndTime(appointment.getEndTime());
    entity.setStatus(appointment.getStatus());

    return entity;
  }

  public Doctor toEntity(CreateDoctorDto doctorDto) {

    Doctor entity = new Doctor();

    entity.setAppointments(doctorDto.getAppointments());
    entity.setName(doctorDto.getName());

    return entity;
  }

  public Patient toEntity(CreatePatientDto patientDto) {

    Patient entity = new Patient();

    entity.setAppointments(patientDto.getAppointments());
    entity.setName(patientDto.getName());

    return entity;
  }
}
