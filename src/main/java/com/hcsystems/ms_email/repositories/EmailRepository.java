package com.hcsystems.ms_email.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcsystems.ms_email.models.EmailModel;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel, UUID>{

}
