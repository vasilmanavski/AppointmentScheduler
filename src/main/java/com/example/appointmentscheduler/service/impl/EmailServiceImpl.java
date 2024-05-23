package com.example.appointmentscheduler.service.impl;

import com.example.appointmentscheduler.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender mailSender;

  @Autowired
  public EmailServiceImpl(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Async
  public void sendEmail(String to, String subject, String text) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("appointments-scheduler-app@gmail.com");
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);

    try {
      mailSender.send(message);
    } catch (Exception e) {
      System.err.println("Failed to send email: " + e.getMessage());
    }

  }
}
