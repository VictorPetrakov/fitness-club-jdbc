package com.victorp.services;

import com.victorp.model.Admin;
import com.victorp.model.Client;
import com.victorp.model.Trainer;

import java.util.List;

public interface ViewService  {
    List<Client> viewClients() throws Exception;

    List<Admin> viewAdmins() throws Exception;

    List<Trainer> viewTrainers() throws Exception;

}
