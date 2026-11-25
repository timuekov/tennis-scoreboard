package org.example;

import org.example.config.HibernateUtil;
import org.example.entity.PlayerEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerEntityPersistenceTest {

    @Test
    void existingPlayerShouldNotBeCreatedAgain() {

        HibernateUtil hibernateUtil = new HibernateUtil();

        SessionFactory sessionFactory = hibernateUtil.createSessionFactory();

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            PlayerEntity player = new PlayerEntity("TestExistingPlayer_" + UUID.randomUUID());

            session.persist(player);

            transaction.commit();

            assertNotNull(player.getId());

            PlayerEntity foundPlayer = session.createQuery("FROM PlayerEntity  WHERE name = :playerName", PlayerEntity.class).setParameter("playerName", player.getName()).getSingleResultOrNull();

            assertNotNull(foundPlayer);

            assertEquals(player.getId(), foundPlayer.getId());

            Transaction deleteTransaction = session.beginTransaction();

            session.remove(player);

            deleteTransaction.commit();

        }

    }


    @Test
    void createPlayerWhenNotFound() {

        HibernateUtil hibernateUtil = new HibernateUtil();

        SessionFactory sessionFactory = hibernateUtil.createSessionFactory();

        try (Session session = sessionFactory.openSession()) {


            PlayerEntity foundPlayerName = session.createQuery("FROM PlayerEntity  WHERE name = :playerName", PlayerEntity.class).setParameter("playerName", "TestNewPlayer").getSingleResultOrNull();

            Transaction transaction = session.beginTransaction();

            boolean playerCreated = false;

            if (foundPlayerName == null) {

                foundPlayerName = new PlayerEntity("TestNewPlayer");

                session.persist(foundPlayerName);

                playerCreated = true;

            }
            transaction.commit();

            assertTrue(playerCreated);

            assertNotNull(foundPlayerName.getId());

            if (playerCreated) {
                Transaction deleteTransaction = session.beginTransaction();
                session.remove(foundPlayerName);
                deleteTransaction.commit();
            }
        }
    }

    @Test
    void findPlayerByNameReturnsNullWhenPlayerDoesNotExist() {


        HibernateUtil hibernateUtil = new HibernateUtil();

        SessionFactory sessionFactory = hibernateUtil.createSessionFactory();


        try (Session session = sessionFactory.openSession()) {

            PlayerEntity foundPlayerName = session.createQuery("FROM PlayerEntity WHERE name = :playerName", PlayerEntity.class).setParameter("playerName", "NonExistentPlayer12345").getSingleResultOrNull();

            assertNull(foundPlayerName);

        }
    }


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

            PlayerEntity foundPlayerName = session.createQuery("FROM PlayerEntity WHERE name = :playerName", PlayerEntity.class).setParameter("playerName", "TestFindPlayer").getSingleResultOrNull();

            PlayerEntity foundPlayer = session.get(PlayerEntity.class, player.getId());

            assertNotNull(foundPlayerName);

            assertEquals("TestFindPlayer", foundPlayerName.getName());

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
