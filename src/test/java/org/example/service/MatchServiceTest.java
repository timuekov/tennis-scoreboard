package org.example.service;

import org.example.model.Match;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MatchServiceTest {

    @Test
    void createMatchShouldSaveMatch() {
        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        MatchService matchService = new MatchService(currentMatchStorage);

        UUID uuid = matchService.createMatch("Kate", "Mary");

        Match match = currentMatchStorage.getMatch(uuid);

        assertNotNull(match);

        assertEquals("Kate", match.getPlayer1().getName());
        assertEquals("Mary", match.getPlayer2().getName());
    }

}
