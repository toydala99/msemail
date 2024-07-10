package com.hcsystems.ms_email.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.hcsystems.ms_email.enums.StatusEmail;
import com.hcsystems.ms_email.models.EmailModel;
import com.hcsystems.ms_email.repositories.EmailRepository;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender emailSender;

    @SuppressWarnings("finally")
    public EmailModel sendEmail(EmailModel emailModel) {
        emailModel.setSendDateEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());

            emailSender.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        } catch (Exception e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } finally{
            return emailRepository.save(emailModel);
        }
    }
}
