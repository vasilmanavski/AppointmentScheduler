package com.example.appointmentscheduler.dto;

import java.util.Set;

import com.example.appointmentscheduler.model.Appointment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDoctorDto {

  private String name;
  private Set<Appointment> appointments;
}
