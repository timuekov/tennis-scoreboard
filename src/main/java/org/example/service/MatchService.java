package org.example.service;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import org.example.model.Match;
import org.example.model.Player;
import org.example.model.Score;

import java.util.UUID;

public class MatchService {

    private final CurrentMatchStorage currentMatchStorage;

    public MatchService(CurrentMatchStorage currentMatchStorage) {

        this.currentMatchStorage = currentMatchStorage;

    }


    public UUID createMatch(String name1, String name2) {

        if (name1.equals(name2)) {
            throw new IllegalArgumentException("Player names must be different");
        }

        Player player1 = new Player(name1);
        Player player2 = new Player(name2);

        Match match = new Match(player1, player2);

        UUID uuid = currentMatchStorage.addMatch(match);

        return uuid;
    }


    public Match getMatchByUuid(UUID uuid) {
        Match match = currentMatchStorage.getMatch(uuid);

        if (match == null) {

            throw new IllegalArgumentException("Match not found");
        }
        return match;

    }

    void addPointToMatch(UUID uuid, String playerName) {

        Match match = getMatchByUuid(uuid);

        Player player1 = match.getPlayer1();
        Player player2 = match.getPlayer2();

        if (playerName.equals(player1.getName())) {

            match.addPoint(1);

        }  else if (playerName.equals(player2.getName())) {
            match.addPoint(2);
        } else {
            throw new IllegalArgumentException("Player not found.");
        }

    }

}
