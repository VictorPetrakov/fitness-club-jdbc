package com.victorp.services;

import com.victorp.model.Client;

import java.sql.Date;

public interface RegistrationService  {
    void createClient(Client client) throws Exception;
    void createTrainer();
    void createAdmin();
}
