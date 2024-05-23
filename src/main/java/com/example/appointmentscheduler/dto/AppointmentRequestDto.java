package com.example.appointmentscheduler.dto;

import com.example.appointmentscheduler.model.enums.Status;

import lombok.Getter;

@Getter
public class AppointmentRequestDto {

  private Long patientId;
  private Status status;
}
