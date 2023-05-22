package com.clubby.clubbyApp.dao;

import com.clubby.clubbyApp.entity.Visitor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class VisitorDAOImpl implements VisitorDAO {
    @Autowired
    private EntityManager entityManager;

    public VisitorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Visitor visitor) {
        entityManager.persist(visitor);
    }

    @Override
    public Visitor findById(Integer id) {
        return entityManager.find(Visitor.class, id);
    }

    @Override
    @Transactional
    public void updateEmail(Visitor visitor) {
        entityManager.merge(visitor);
    }

    @Override
    @Transactional
    public void updatePhoneNum(Visitor visitor) {
        entityManager.merge(visitor);
    }

    @Override
    @Transactional
    public void updatePassword(Visitor visitor) {
        entityManager.merge(visitor);
    }

    @Override
    @Transactional
    public void deleteYourDetails(Integer id) {
        Visitor visitor = entityManager.find(Visitor.class, id);
        entityManager.remove(visitor);
    }


}
