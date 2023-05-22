package com.clubby.clubbyApp.dao;

import com.clubby.clubbyApp.entity.Admin;
import com.clubby.clubbyApp.entity.Visitor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AdminDAOImpl implements AdminDAO {
    @Autowired
    public EntityManager entityManager;

    public AdminDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Admin admin) {
        entityManager.persist(admin);
    }

    @Override
    public void adminLogin(String userName, String password) {
        TypedQuery<Admin> theQuery = entityManager.createQuery("FROM Admin WHERE userName=:theUsername AND " +
                "password=:thePassword",Admin.class);

        theQuery.setParameter("theUsername" ,userName);
        theQuery.setParameter("thePassword", password);
    }

    @Override
    @Transactional
    public void updatePassword(Admin admin) {
        entityManager.merge(admin);
    }

    @Override
    public Visitor findById(Integer id) {
        return entityManager.find(Visitor.class, id);
    }

    @Override
    public List<Visitor> findAll() {
        TypedQuery<Visitor> theQuery = entityManager.createQuery("FROM Visitor", Visitor.class);
        return theQuery.getResultList();
    }

    @Override
    public List<Visitor> findByLastName(String lastName) {
        TypedQuery<Visitor> theQuery = entityManager.createQuery("FROM Visitor WHERE lastName=:theData", Visitor.class);
        theQuery.setParameter("theData", lastName);
        return theQuery.getResultList();
    }

    @Override
    public void delete(Integer id) {
        Visitor visitor = entityManager.find(Visitor.class, id);
        entityManager.remove(visitor);
    }

    @Override
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Visitor").executeUpdate();
        return numRowsDeleted;
    }
}
