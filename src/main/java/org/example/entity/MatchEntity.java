package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Matches")
public class MatchEntity {

    @JoinColumn(name="Player1", nullable = false)
    @ManyToOne
    private PlayerEntity player1;

    @JoinColumn(name="Player2", nullable = false)
    @ManyToOne
    private PlayerEntity player2;

    @JoinColumn(name="Winner", nullable = false)
    @ManyToOne
    private PlayerEntity winner;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    public MatchEntity(PlayerEntity player1, PlayerEntity player2, PlayerEntity winner) {
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    protected  MatchEntity() {

    }

    public PlayerEntity getPlayer1() {
        return player1;
    }

    public Integer getId() {
        return id;
    }

    public PlayerEntity getWinner() {
        return winner;
    }

    public PlayerEntity getPlayer2() {
        return player2;
    }
}
