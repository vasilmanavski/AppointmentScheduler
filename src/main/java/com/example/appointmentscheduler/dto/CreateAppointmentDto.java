package com.example.appointmentscheduler.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.example.appointmentscheduler.model.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAppointmentDto {

  private Long doctorId;
  private Long patientId;
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDateTime startTime;
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDateTime endTime;
  private Status status;
}
