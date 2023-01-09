/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.DBmanager;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import pl.polsl.entities.NumberCalculated;
import pl.polsl.entities.NumberData;

/**
 *
 * @author SuperStudent.EN
 */
public class DBManager {

    private EntityManager em;
    private EntityTransaction txn;

    public EntityManager getEntityManager() {
        return em;
    }

    public EntityTransaction getTransaction() {
        return txn;
    }

    public DBManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_WebProject_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        txn = em.getTransaction();
    }

    /**
     * this method sends NumberData and NumberCalculated into the database
     *
     * @param num number searched
     * @param occurrences the number's occurrences
     * @param rafflesNum the number's number of raffles
     * @param probability the number's probability of occurring in the next
     * raffle
     */
    public void SendNumber(String num, int occurrences, String rafflesNum, Double probability) {

        NumberData numD = new NumberData();
        NumberCalculated numC = new NumberCalculated();
        numD.setNumber(num);
        numD.setRafflesNum(rafflesNum);
        numC.setOccurrences(occurrences);
        numC.setProbability(probability);
        numC.setNumberData(numD);

        this.persistObject(numD);
        this.persistObject(numC);

    }

    /**
     * this method persists a chosen object
     *
     * @param object object to be persisted in the database
     */
    public void persistObject(Object object) {
        txn.begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
        }
    }

    /**
     * debugging method for displaying NumberCalculated
     */
    public void find() {
        try {
            Query query = em.createQuery("SELECT n FROM NumberCalculated n");
            List<NumberCalculated> numberCalculatedList = query.getResultList();
            for (NumberCalculated numC : numberCalculatedList) {
                System.out.println("Found NumberCalculated: " + " in " + numC.getProbability() + numC.getId() + " raffles has appeared " + numC.getOccurrences());
            }
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
        } finally {
        }
    }

    /**
     * this method is for gathering and returning a list of NumberData present
     * in the database
     *
     * @return a list of NumberData objects
     */
    public List<NumberData> getNumbersData() {
        Query query = em.createQuery("SELECT n FROM NumberData n");
        List<NumberData> numberDataList = query.getResultList();
        return numberDataList;
    }

    /**
     * this method is for gathering and returning a list of NumberCalculated
     * present in the database
     *
     * @return a list of NumberCalculated objects
     */
    public List<NumberCalculated> getNumbersCalculated() {
        Query query = em.createQuery("SELECT n FROM NumberCalculated n");
        List<NumberCalculated> numberCalculatedList = query.getResultList();
        return numberCalculatedList;
    }

    /**
     * this method initializes the database in case there is no data made purely
     * to meet the conditions of the project
     */
    public void initDB() {
        Query query = em.createQuery("SELECT n FROM NumberData n");
        List<NumberData> numberDataList = query.getResultList();
        if (numberDataList.isEmpty()) {
            this.SendNumber("2", 1, "3", 7.0);
        }
    }

}
