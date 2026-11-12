package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatchTest {

    @Test
    void addPointAfterMatchFinishedShouldThrowException() {

        Player player1 = new Player("Rob");
        Player player2 = new Player("Alex");

        Match match = new Match(player1, player2);

        for (int i = 1; i <= 48; i++) {
            match.addPoint(1);
        }

        assertThrows(IllegalStateException.class, () -> match.addPoint(1));

    }


    @Test
    void matchShouldFinishAfterTwoSets() {

        Player player1 = new Player("Rob");
        Player player2 = new Player("Alex");

        Match match = new Match(player1, player2);

        for (int i = 1; i <= 48; i++) {
            match.addPoint(1);
        }

        assertEquals(MatchStatus.FINISHED, match.getStatus());

    }


    @Test
    void addPointShouldUpdateScore() {

        Player player1 = new Player("Rob");
        Player player2 = new Player("Alex");

        Match match = new Match(player1, player2);

        match.addPoint(1);

        assertEquals(15, match.getCurrentScore().getPoints1());

    }


    @Test
    void newMatchShouldHaveScore() {

        Player player1 = new Player("Rob");
        Player player2 = new Player("Alex");

        Match match = new Match(player1, player2);

        assertNotNull(match.getCurrentScore());
    }


    @Test
    void newMatchShouldContainBothPlayers() {

        Player player1 = new Player("Rob");
        Player player2 = new Player("Alex");

        Match match = new Match(player1, player2);

        assertEquals(player1, match.getPlayer1());
        assertEquals(player2, match.getPlayer2());

    }


    @Test
    void newMatchShouldHaveInProgressStatus() {

        Player player1 = new Player("Rob");
        Player player2 = new Player("Alex");

        Match match = new Match(player1, player2);

        assertEquals(MatchStatus.IN_PROGRESS, match.getStatus());
    }
}
