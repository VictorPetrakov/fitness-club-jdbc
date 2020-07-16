package com.victorp.db.dao;

import com.victorp.model.Trainer;

public interface TrainerDao extends GeneralDao<Trainer>{
    Trainer signUp(String login, String password) throws Exception;
    Trainer getByLogin(String login) throws Exception;
    Trainer getByGroup(String group) throws Exception;

}


