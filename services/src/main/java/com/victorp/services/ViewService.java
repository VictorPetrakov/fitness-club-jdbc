package com.victorp.services;

import com.victorp.model.Admin;
import com.victorp.model.Client;
import com.victorp.model.Trainer;

import java.util.List;

public interface ViewService  {
    List<Client> viewClients(List<Client> clientList) throws Exception;

    List<Admin> viewAdmins(List<Admin> adminList) throws Exception;

    List<Trainer> viewTrainers(List<Trainer> trainerList) throws Exception;

}
