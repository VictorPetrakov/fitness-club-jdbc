package com.victorp.services;

import com.victorp.model.Admin;
import com.victorp.model.Client;
import com.victorp.model.Trainer;

public interface RegistrationService {
    void createClient(Client client) throws Exception;

    void createTrainer(Trainer trainer) throws Exception;

    void createAdmin(Admin admin) throws Exception;

}
