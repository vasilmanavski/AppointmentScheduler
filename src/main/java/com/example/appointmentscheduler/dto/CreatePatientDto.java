package com.example.appointmentscheduler.dto;

import com.example.appointmentscheduler.model.Appointment;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePatientDto {
  private String name;
  private Set<Appointment> appointments;
}
