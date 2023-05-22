package com.clubby.clubbyApp.dao;

import com.clubby.clubbyApp.entity.Admin;
import com.clubby.clubbyApp.entity.Visitor;

import java.util.List;

public interface AdminDAO {
    void save(Admin admin);

    void adminLogin(String userName, String password);

    void updatePassword(Admin admin);

    Visitor findById(Integer id);

    List<Visitor> findAll();

    List<Visitor> findByLastName(String lastName);

    void  delete(Integer id);

    int deleteAll();
}
