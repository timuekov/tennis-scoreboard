package org.example.service;

import org.example.config.HibernateUtil;
import org.example.entity.MatchEntity;
import org.example.entity.PlayerEntity;
import org.example.model.Match;
import org.example.model.MatchStatus;
import org.example.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class FinishedMatchPersistenceServiceTest {

    @Test
    void shouldReuseExistingPlayers() {

        PlayerEntity playerEntity = new PlayerEntity("ExistingPlayer" + UUID.randomUUID());

        HibernateUtil hibernateUtil = new HibernateUtil();

        try (SessionFactory sessionFactory = hibernateUtil.createSessionFactory()) {

            try (Session session = sessionFactory.openSession()) {

                Transaction transaction = session.beginTransaction();

                session.persist(playerEntity);

                transaction.commit();
            }

            Player player1 = new Player(playerEntity.getName());
            Player player2 = new Player("Matt_" + UUID.randomUUID());

            Match match = new Match(player1, player2);

            for (int i = 1; i <= 48; i++) {
                match.addPoint(2);
            }

            assertEquals(player2, match.getWinner());
            assertEquals(MatchStatus.FINISHED, match.getStatus());

            FinishedMatchPersistenceService finishedMatchPersistenceService = new FinishedMatchPersistenceService(sessionFactory);

            finishedMatchPersistenceService.saveFinishedMatch(match);

            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();

                MatchEntity matchEntity = session.createQuery("FROM MatchEntity WHERE player1.name = :playerName", MatchEntity.class).setParameter("playerName", player1.getName()).getSingleResultOrNull();

                assertNotNull(matchEntity);
                assertEquals(player2.getName(), matchEntity.getPlayer2().getName());
                assertEquals(player2.getName(), matchEntity.getWinner().getName());

                assertEquals(playerEntity.getId(), matchEntity.getPlayer1().getId());

                session.remove(matchEntity);
                session.remove(matchEntity.getPlayer1());
                session.remove(matchEntity.getPlayer2());

                transaction.commit();

            }
        }
    }


    @Test
    void shouldSaveFinishedMatch() {

        Player player1 = new Player("Bill_" + UUID.randomUUID());
        Player player2 = new Player("Seth_" + UUID.randomUUID());

        Match match = new Match(player1, player2);

        for (int i = 1; i <= 48; i++) {
            match.addPoint(1);
        }

        assertEquals(player1, match.getWinner());
        assertEquals(MatchStatus.FINISHED, match.getStatus());


        HibernateUtil hibernateUtil = new HibernateUtil();

        try (SessionFactory sessionFactory = hibernateUtil.createSessionFactory()) {

            FinishedMatchPersistenceService finishedMatchPersistenceService = new FinishedMatchPersistenceService(sessionFactory);

            finishedMatchPersistenceService.saveFinishedMatch(match);


            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();

                MatchEntity matchEntity = session.createQuery("FROM MatchEntity WHERE player1.name = :playerName", MatchEntity.class).setParameter("playerName", player1.getName()).getSingleResultOrNull();

                assertNotNull(matchEntity);
                assertEquals(player1.getName(), matchEntity.getPlayer1().getName());
                assertEquals(player2.getName(), matchEntity.getPlayer2().getName());
                assertEquals(player1.getName(), matchEntity.getWinner().getName());

                session.remove(matchEntity);
                session.remove(matchEntity.getPlayer1());
                session.remove(matchEntity.getPlayer2());

                transaction.commit();

            }
        }

    }


    @Test
    void shouldRejectUnfinishedMatch() {

        Player player1 = new Player("Bob");
        Player player2 = new Player("Rob");

        Match match = new Match(player1, player2);

        FinishedMatchPersistenceService finishedMatchPersistenceService = new FinishedMatchPersistenceService(null);

        assertThrows(IllegalStateException.class, () -> finishedMatchPersistenceService.saveFinishedMatch(match));

    }
}
