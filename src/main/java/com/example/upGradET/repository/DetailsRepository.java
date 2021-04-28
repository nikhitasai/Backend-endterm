package com.example.upGradET.repository;

import com.example.upGradET.model.Details;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class DetailsRepository {

    @PersistenceUnit(name="upGradET")
    private EntityManagerFactory entityManagerFactory;
    public void credentialsAdd(Details details) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(details);
            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }






    }

    public List<Details> getAllDetails() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<Details> query = entityManager.createQuery("SELECT d from Details d", Details.class);
        List<Details> result = query.getResultList();
        return result;


    }

    public Details getDetail(Integer detailId) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(Details.class, detailId);
    }

    public void updateDetails(Details updatedDetail) {


        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(updatedDetail);
            transaction.commit();

        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
    }

    public void deleteDetail(Integer detailId) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();


        try {
            transaction.begin();
            Details d=entityManager.find(Details.class,detailId);
            entityManager.remove(d);
            transaction.commit();

        } catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        }



    }
}
