package com.victorp.services;

import com.victorp.model.Admin;
import com.victorp.model.Client;
import com.victorp.model.Trainer;

public interface AuthorizationService {

    Client authorizeClient(String login, String password) throws Exception;
    Trainer authorizeTrainer(String login, String password) throws Exception;
    Admin authorizeAdmin(String login, String password) throws Exception;
}
