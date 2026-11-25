package org.example;

import org.example.config.HibernateUtil;
import org.example.entity.MatchEntity;
import org.example.entity.PlayerEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MatchEntityPersistenceTest {

    @Test
    void saveMatchWithExistingAndNewPlayer() {

        HibernateUtil hibernateUtil = new HibernateUtil();

        try (SessionFactory sessionFactory = hibernateUtil.createSessionFactory()) {

            try (Session session = sessionFactory.openSession()) {

                Transaction transaction = session.beginTransaction();

                PlayerEntity playerEntity = new PlayerEntity("ExistingPlayer" + UUID.randomUUID());

                session.persist(playerEntity);

                transaction.commit();

                PlayerEntity foundPlayer = session.createQuery("FROM PlayerEntity  WHERE name = :playerName",
                        PlayerEntity.class).setParameter("playerName",
                        playerEntity.getName()).getSingleResultOrNull();


                assertNotNull(foundPlayer);
                assertEquals(playerEntity.getId(), foundPlayer.getId());

                Transaction matchTransaction = session.beginTransaction();

                PlayerEntity newPlayer = new PlayerEntity("NewPlayer_" + UUID.randomUUID());

                session.persist(newPlayer);

                MatchEntity match = new MatchEntity(foundPlayer, newPlayer, newPlayer);

                session.persist(match);

                matchTransaction.commit();

                assertNotNull(match.getId());

                Transaction deleteTransaction = session.beginTransaction();

                session.remove(match);

                deleteTransaction.commit();

                Transaction deletePlayersTransaction = session.beginTransaction();

                session.remove(newPlayer);
                session.remove(playerEntity);

                deletePlayersTransaction.commit();
            }

        }

    }

    @Test
    void saveAndReadFinishedMatch() {

        HibernateUtil hibernateUtil = new HibernateUtil();

        try (SessionFactory sessionFactory = hibernateUtil.createSessionFactory()) {

            Integer matchId;

            Integer player1Id;
            Integer player2Id;

            try (Session session = sessionFactory.openSession()) {

                Transaction transaction = session.beginTransaction();

                PlayerEntity player1 = new PlayerEntity("Roger_" + UUID.randomUUID());
                PlayerEntity player2 = new PlayerEntity("Rafael_" + UUID.randomUUID());

                session.persist(player1);
                session.persist(player2);

                MatchEntity match = new MatchEntity(player1, player2, player1);

                session.persist(match);

                transaction.commit();

                matchId = match.getId();
                player1Id = player1.getId();
                player2Id = player2.getId();

            }

            try (Session readSession = sessionFactory.openSession()) {

                MatchEntity loadedMatch = readSession.get(MatchEntity.class, matchId);

                assertNotNull(loadedMatch);

                assertEquals(matchId, loadedMatch.getId());

                assertNotNull(loadedMatch.getPlayer1());
                assertNotNull(loadedMatch.getPlayer2());
                assertNotNull(loadedMatch.getWinner());

                assertEquals(loadedMatch.getPlayer1().getId(), loadedMatch.getWinner().getId());

                assertEquals(player1Id, loadedMatch.getPlayer1().getId());
                assertEquals(player2Id, loadedMatch.getPlayer2().getId());


            }
        }
    }
}
