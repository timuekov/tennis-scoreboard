package org.example.model;

public class Score {
    private int points1;
    private int points2;
    private int games1;
    private int games2;
    private int sets1;
    private int sets2;

    public int getPoints1() {
        return points1;
    }

    public int getPoints2() {
        return points2;
    }

    public int getGames1() {
        return games1;
    }

    public int getSets1() {
        return sets1;
    }

    public int getSets2() {
        return sets2;
    }

    public int getGames2() {
        return games2;
    }


    public void addPoint(int playNumber) {
        int winnerPoints = 0;
        int opponentPoints = 0;

        if (playNumber != 1 && playNumber != 2) {
            throw new IllegalArgumentException("Некорректный номер! Введите 1 или 2");
        }

        if (playNumber == 2) {
            winnerPoints = points2;
            opponentPoints = points1;
        } else if (playNumber == 1) {
            winnerPoints = points1;
            opponentPoints = points2;
        }

        if (games1 == 6 && games2 == 6) {
            if (playNumber == 1) {
                points1++;
            } else {
                points2++;
            }

            if ((points1 - points2 >= 2) && (points1 >= 7)) {
                sets1++;
                points1 = 0;
                points2 = 0;
                games1 = 0;
                games2 = 0;
            } else if ((points2 - points1 >= 2) && (points2 >= 7)) {
                sets2++;
                points1 = 0;
                points2 = 0;
                games1 = 0;
                games2 = 0;
            }
            return;
        }


        if ((winnerPoints == 40 && opponentPoints < 40) || (winnerPoints == 50 && opponentPoints == 40)) {
            points1 = 0;
            points2 = 0;

            if (playNumber == 1) {
                games1++;
            } else {
                games2++;
            }

            if ((games1 - games2 >= 2) && (games1 >= 6)) {
                games1 = 0;
                games2 = 0;
                sets1++;
            } else if ((games2 - games1 >= 2) && (games2 >= 6)) {
                games1 = 0;
                games2 = 0;
                sets2++;
            }
            return;
        }


        if (winnerPoints == 40 && opponentPoints == 40) {
            winnerPoints = 50;
        }

        if (winnerPoints == 40 && opponentPoints == 50) {
            if (playNumber == 1) {
                points2 = 40;
            } else if (playNumber == 2) {
                points1 = 40;

            }
            return;
        }

        if (winnerPoints == 0) {
            winnerPoints = 15;
        } else if (winnerPoints == 15) {
            winnerPoints = 30;
        } else if (winnerPoints == 30) {
            winnerPoints = 40;
        }

        if (playNumber == 1) {
            points1 = winnerPoints;
        } else if (playNumber == 2) {
            points2 = winnerPoints;
        }
    }
}