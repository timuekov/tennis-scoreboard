package org.example;

import org.example.config.HibernateUtil;
import org.example.entity.PlayerEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerEntityPersistenceTest {

    @Test
    void findPlayerByIdSuccessfully() {

        HibernateUtil hibernateUtil = new HibernateUtil();

        SessionFactory sessionFactory = hibernateUtil.createSessionFactory();

        PlayerEntity player = new PlayerEntity("TestFindPlayer");


        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            session.persist(player);

            transaction.commit();

            session.clear();

            PlayerEntity foundPlayer = session.get(PlayerEntity.class, player.getId());

            assertNotNull(foundPlayer);
            assertEquals("TestFindPlayer", foundPlayer.getName());

            Transaction deleteTransaction = session.beginTransaction();

            session.remove(foundPlayer);

            deleteTransaction.commit();

        }

    }


    @Test
    void savePlayerSuccessfully() {

        HibernateUtil hibernateUtil = new HibernateUtil();

        SessionFactory sessionFactory = hibernateUtil.createSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            PlayerEntity player = new PlayerEntity("TestPlayer");

            session.persist(player);

            transaction.commit();

            assertNotNull(player.getId());

            Transaction deleteTransaction = session.beginTransaction();

            session.remove(player);

            deleteTransaction.commit();

        }

    }

}
