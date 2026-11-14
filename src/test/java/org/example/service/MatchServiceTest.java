package org.example.service;

import org.example.model.Match;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class MatchServiceTest {

    @Test
    void addPointToMatchShouldThrowExceptionWhenPlayerNotFound() {

        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        MatchService matchService = new MatchService(currentMatchStorage);

        UUID uuid = matchService.createMatch("Alex", "Bob");

        assertThrows(IllegalArgumentException.class, ()-> matchService.addPointToMatch(uuid, "Mike"));
    }



    @Test
    void addPointToMatchShouldUpdateScoreByPlayerName() {

        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        MatchService matchService = new MatchService(currentMatchStorage);

        UUID uuid =  matchService.createMatch("Alex", "Bob");

        matchService.addPointToMatch(uuid,"Bob");

        Match match = matchService.getMatchByUuid(uuid);

        assertEquals(15, match.getCurrentScore().getPoints2());
        assertEquals(0, match.getCurrentScore().getPoints1());



    }



    @Test
    void createMatchShouldThrowExceptionWhenPlayerNamesAreEqual() {

        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();
        MatchService matchService = new MatchService(currentMatchStorage);

        assertThrows(IllegalArgumentException.class, ()-> matchService.createMatch("Alex", "Alex"));

    }



    @Test
    void addPointToMatchShouldThrowExceptionWhenMatchNotFound() {
        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        MatchService matchService = new MatchService(currentMatchStorage);

        UUID uuid = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, ()-> matchService.addPointToMatch(uuid, "Bob"));
    }



    @Test
    void addPointToMatchShouldUpdateScore2() {

        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        MatchService matchService = new MatchService(currentMatchStorage);

        UUID uuid = matchService.createMatch("Don", "Ron");

        matchService.addPointToMatch(uuid, "Ron");

        Match match = matchService.getMatchByUuid(uuid);

        assertEquals(15, match.getCurrentScore().getPoints2());
        assertEquals(0, match.getCurrentScore().getPoints1());
    }


    @Test
    void addPointToMatchShouldUpdateScore() {

        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        MatchService matchService = new MatchService(currentMatchStorage);

        UUID uuid = matchService.createMatch("Don", "Ron");

        matchService.addPointToMatch(uuid, "Don");

        Match match = matchService.getMatchByUuid(uuid);

        assertEquals(15, match.getCurrentScore().getPoints1());

    }


    @Test
    void getMatchByUuidThrowsExceptionWhenMatchNotFound() {
        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        MatchService matchService = new MatchService(currentMatchStorage);

        UUID uuid = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, () -> matchService.getMatchByUuid(uuid));

    }


    @Test
    void getMatchByUuidTest() {
        CurrentMatchStorage currentMatchStorage = new CurrentMatchStorage();

        MatchService matchService = new MatchService(currentMatchStorage);

        UUID uuid = matchService.createMatch("Pete", "Greg");

        Match match = currentMatchStorage.getMatch(uuid);

        Match result = matchService.getMatchByUuid(uuid);

        assertSame(match, result);

    }


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
