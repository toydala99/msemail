package com.hcsystems.ms_email.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.hcsystems.ms_email.dtos.EmailDTO;
import com.hcsystems.ms_email.models.EmailModel;
import com.hcsystems.ms_email.services.EmailService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/mail")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDTO emailDTO) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel);
        emailService.sendEmail(emailModel);

        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }

}
