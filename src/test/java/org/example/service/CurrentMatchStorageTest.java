package org.example.service;

import org.example.model.Match;
import org.example.model.Player;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CurrentMatchStorageTest {

    @Test
    void removeMatchShouldDeleteMatch() {

        Player player1 = new Player("Max");
        Player player2 = new Player("Leo");

        Match match = new Match(player1, player2);

        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        UUID uuid = currentMatchStorage.addMatch(match);

        currentMatchStorage.removeMatch(uuid);

        Match res = currentMatchStorage.getMatch(uuid);

        assertNull(res);

    }



    @Test
    void getMatchShouldReturnNullForUnknownUuid() {

        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        UUID uuid = UUID.randomUUID();

        Match match = currentMatchStorage.getMatch(uuid);

        assertNull(match);

    }



    @Test
    void getMatchShouldReturnSavedMatch() {

        Player player1 = new Player("Max");
        Player player2 = new Player("Leo");

        Match match = new Match(player1, player2);

        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        UUID uuid = currentMatchStorage.addMatch(match);
        Match res = currentMatchStorage.getMatch(uuid);

        assertSame(match, res);

    }

    @Test
    void addMatchShouldReturnUuid() {

        Player player1 = new Player("Max");
        Player player2 = new Player("Leo");

        Match match = new Match(player1, player2);

        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        UUID uuid = currentMatchStorage.addMatch(match);

        assertNotNull(uuid);

    }
}
