package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ScoreTest {

    @Test
    void shouldThrowExceptionForInvalidPlayerNumber() {

        Score score = new Score();

        assertThrows(IllegalArgumentException.class, ()-> score.addPoint(3));
        assertEquals(0, score.getPoints1());
        assertEquals(0, score.getPoints2());
    }

    @Test
    void shouldWinTieBreakAtEightSix() {

        Score score = new Score();

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 4; j++) {
                score.addPoint(1);
            }
        }

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 4; j++) {
                score.addPoint(2);
            }
        }


        for (int i = 1; i <= 4; i++) {
            score.addPoint(1);
        }

        for (int i = 1; i <= 4; i++) {
            score.addPoint(2);
        }

        for (int i = 1; i <= 6; i++) {
            score.addPoint(1);
            score.addPoint(2);
        }

        score.addPoint(1);
        score.addPoint(1);

        assertEquals(1, score.getSets1());
        assertEquals(0, score.getSets2());

        assertEquals(0, score.getPoints1());
        assertEquals(0, score.getPoints2());

        assertEquals(0, score.getGames1());
        assertEquals(0, score.getGames2());


    }

    @Test
    void shouldNotWinTieBreakAtSevenSix() {
        Score score = new Score();

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 4; j++) {
                score.addPoint(1);
            }
        }

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 4; j++) {
                score.addPoint(2);
            }
        }


        for (int i = 1; i <= 4; i++) {
            score.addPoint(1);
        }

        for (int i = 1; i <= 4; i++) {
            score.addPoint(2);
        }

        for (int i = 1; i <= 6; i++) {
            score.addPoint(1);
            score.addPoint(2);
        }

        score.addPoint(1);

        assertEquals(0, score.getSets1());
        assertEquals(0, score.getSets2());

        assertEquals(7, score.getPoints1());
        assertEquals(6, score.getPoints2());

        assertEquals(6, score.getGames1());
        assertEquals(6, score.getGames2());

    }

    @Test
    void shouldWinTieBreakAtSevenFive() {
        Score score = new Score();

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 4; j++) {
                score.addPoint(1);
            }
        }

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 4; j++) {
                score.addPoint(2);
            }
        }


        for (int i = 1; i <= 4; i++) {
            score.addPoint(1);
        }

        for (int i = 1; i <= 4; i++) {
            score.addPoint(2);
        }

        for (int i = 1; i <= 5; i++) {
            score.addPoint(1);
            score.addPoint(2);
        }

        score.addPoint(1);
        score.addPoint(1);


        assertEquals(1, score.getSets1());
        assertEquals(0, score.getSets2());

        assertEquals(0, score.getPoints1());
        assertEquals(0, score.getPoints2());

        assertEquals(0, score.getGames1());
        assertEquals(0, score.getGames2());
    }

    @Test
    void shouldStartTieBreakAtSixSix() {
        Score score = new Score();

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 4; j++) {
                score.addPoint(1);
            }
        }

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 4; j++) {
                score.addPoint(2);
            }
        }

        score.addPoint(1);
        score.addPoint(1);
        score.addPoint(1);
        score.addPoint(1);
        score.addPoint(2);
        score.addPoint(2);
        score.addPoint(2);
        score.addPoint(2);
        score.addPoint(1);

        assertEquals(1, score.getPoints1());
        assertEquals(0, score.getPoints2());

        assertEquals(6, score.getGames1());
        assertEquals(6, score.getGames2());

    }


    @Test
    void shouldNotWinSetAtSixFive() {
        Score score = new Score();

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 4; j++) {
                score.addPoint(1);
            }
        }

        for (int i = 1; i <= 5; i++) {

            for (int j = 1; j <= 4; j++) {
                score.addPoint(2);
            }
        }

        score.addPoint(1);
        score.addPoint(1);
        score.addPoint(1);
        score.addPoint(1);


        assertEquals(6, score.getGames1());
        assertEquals(5, score.getGames2());

        assertEquals(0, score.getSets1());
        assertEquals(0, score.getSets2());

    }


    @Test
    void shouldWinSetAfterSixGames() {

        Score score = new Score();

        for (int i = 1; i <= 24; i++) {

            score.addPoint(1);
        }

        assertEquals(1, score.getSets1());
        assertEquals(0, score.getSets2());
        assertEquals(0, score.getGames1(), "Test failed");
        assertEquals(0, score.getGames2(), "Test failed");

    }

    @Test
    void shouldAddFifteenPointsToFirstPlayer() {

        Score score = new Score();

        score.addPoint(1);

        assertEquals(15, score.getPoints1(), "Тест провален.");
        assertEquals(0, score.getPoints2(), "Тест провален.");

    }

    @Test
    void shouldAddFifteenPointsToSecondPlayer() {
        Score score = new Score();

        score.addPoint(2);

        assertEquals(15, score.getPoints2(), "Тест провален.");
        assertEquals(0, score.getPoints1(), "Тест провален.");
    }

    @Test
    void shouldIncreasePointsFromZeroToForty() {
        Score score = new Score();

        score.addPoint(1);
        assertEquals(15, score.getPoints1(), "Тест провален.");
        score.addPoint(1);
        assertEquals(30, score.getPoints1(), "Тест провален.");
        score.addPoint(1);
        assertEquals(40, score.getPoints1(), "Тест провален.");
    }

    @Test
    void shouldReachDeuceAtFortyForty() {
        Score score = new Score();

        score.addPoint(1);
        score.addPoint(2);
        score.addPoint(1);
        score.addPoint(2);
        score.addPoint(1);
        score.addPoint(2);

        assertEquals(40, score.getPoints1(), "Тест провален.");
        assertEquals(40, score.getPoints2(), "Тест провален.");
    }

    @Test
    void shouldGiveAdvantageToFirstPlayer() {
        Score score = new Score();

        score.addPoint(1);
        score.addPoint(2);
        score.addPoint(1);
        score.addPoint(2);
        score.addPoint(1);
        score.addPoint(2);
        score.addPoint(1);

        assertEquals(50, score.getPoints1(), "Тест провален.");
        assertEquals(40, score.getPoints2(), "Тест провален.");

    }

    @Test
    void shouldReturnToDeuceAfterLosingAdvantage() {
        Score score = new Score();

        score.addPoint(1);
        score.addPoint(2);
        score.addPoint(1);
        score.addPoint(2);
        score.addPoint(1);
        score.addPoint(2);
        score.addPoint(1);
        score.addPoint(2);

        assertEquals(40, score.getPoints1(), "Тест провален.");
        assertEquals(40, score.getPoints2(), "Тест провален.");
    }

    @Test
    void shouldWinGameAfterFourPoints() {
        Score score = new Score();

        score.addPoint(1);
        score.addPoint(1);
        score.addPoint(1);
        score.addPoint(1);

        assertEquals(1, score.getGames1(), "Тест провален.");
        assertEquals(0, score.getGames2(), "Тест провален.");
        assertEquals(0, score.getPoints1(), "Тест провален.");
        assertEquals(0, score.getPoints2(), "Тест провален.");

    }

    @Test
    void shouldWinGameAfterAdvantage() {
        Score score = new Score();

        score.addPoint(1);
        score.addPoint(2);
        score.addPoint(1);
        score.addPoint(2);
        score.addPoint(1);
        score.addPoint(2);

        score.addPoint(1);
        score.addPoint(1);

        assertEquals(1, score.getGames1(), "Тест провален.");
        assertEquals(0, score.getGames2(), "Тест провален.");
        assertEquals(0, score.getPoints1(), "Тест провален.");
        assertEquals(0, score.getPoints2(), "Тест провален.");
    }

}
