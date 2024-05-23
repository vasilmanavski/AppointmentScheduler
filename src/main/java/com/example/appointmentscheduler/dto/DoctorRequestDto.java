package com.example.appointmentscheduler.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRequestDto {
  private Long doctorId;
  private LocalDateTime chosenDay;
}
