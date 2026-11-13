package org.example.service;

import org.example.model.Match;
import org.example.model.Player;

import java.util.UUID;

public class MatchService {

    private final CurrentMatchStorage currentMatchStorage;

    public MatchService(CurrentMatchStorage currentMatchStorage) {

        this.currentMatchStorage = currentMatchStorage;

    }

    public UUID createMatch(String name1, String name2) {

        Player player1 = new Player(name1);
        Player player2 = new Player(name2);

        Match match = new Match(player1, player2);

        UUID uuid = currentMatchStorage.addMatch(match);

        return uuid;
    }

}
