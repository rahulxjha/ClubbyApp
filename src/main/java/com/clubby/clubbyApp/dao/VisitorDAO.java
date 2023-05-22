package com.clubby.clubbyApp.dao;

import com.clubby.clubbyApp.entity.Visitor;

public interface VisitorDAO {
    void save(Visitor visitor);

    Visitor findById(Integer id);

    void updateAllDetails(Visitor visitor);

    void updateEmail(Visitor visitor);

    void updatePhoneNum(Visitor visitor);

    void updatePassword(Visitor visitor);

    void deleteYourDetails(Visitor visitor);
}
