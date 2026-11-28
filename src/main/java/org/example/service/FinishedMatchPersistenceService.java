package org.example.service;

import org.example.entity.MatchEntity;
import org.example.entity.PlayerEntity;
import org.example.model.Match;
import org.example.model.MatchStatus;
import org.example.model.Player;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FinishedMatchPersistenceService {

    private final SessionFactory sessionFactory;

    public FinishedMatchPersistenceService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveFinishedMatch(Match match) {

        if (match.getStatus() != MatchStatus.FINISHED) {
            throw new IllegalStateException("Матч еще не завершен");
        }

        Player player1 = match.getPlayer1();
        Player player2 = match.getPlayer2();
        Player winner = match.getWinner();

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            try {

               PlayerEntity player1Entity = findOrCreatePlayer(session, player1.getName());
               PlayerEntity player2Entity = findOrCreatePlayer(session, player2.getName());
               PlayerEntity winnerEntity;

               if (winner == player1) {
                   winnerEntity = player1Entity;
               } else {
                   winnerEntity = player2Entity;
               }

                MatchEntity matchEntity = new MatchEntity(player1Entity, player2Entity, winnerEntity);

               session.persist(matchEntity);

                transaction.commit();
            } catch (RuntimeException e) {

                if (transaction.isActive()) {
                    transaction.rollback();
                }
                throw e;

            }
        }
    }

    private PlayerEntity findOrCreatePlayer(Session session, String name) {

        PlayerEntity foundPlayer = session.createQuery("FROM PlayerEntity  WHERE name = :playerName", PlayerEntity.class).setParameter("playerName", name).getSingleResultOrNull();

        if (foundPlayer == null) {
            foundPlayer = new PlayerEntity(name);
            session.persist(foundPlayer);
        }

        return foundPlayer;
    }
}
