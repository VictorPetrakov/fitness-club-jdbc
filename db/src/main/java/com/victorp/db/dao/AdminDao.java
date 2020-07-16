package com.victorp.db.dao;

import com.victorp.model.Admin;

public interface AdminDao extends GeneralDao<Admin>{
    Admin signUp(String login, String password) throws Exception;
    Admin getByLogin(String login) throws Exception;
}
