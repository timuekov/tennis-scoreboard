package org.example.model;

public class Match {
    private final Player player1;
    private final Player player2;
    private final Score currentScore;
    private MatchStatus status;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentScore = new Score();
        this.status = MatchStatus.IN_PROGRESS;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Score getCurrentScore() {
        return currentScore;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void addPoint(int playerNumber) {
        if (status == MatchStatus.FINISHED) {
            throw new IllegalStateException("Ошибка! Матч завершен.");
        }
        currentScore.addPoint(playerNumber);

        if (currentScore.getSets1() == 2 || currentScore.getSets2() == 2) {
            status = MatchStatus.FINISHED;
        }
    }

}
