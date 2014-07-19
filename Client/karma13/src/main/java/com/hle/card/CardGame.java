package com.hle.card;


import com.hle.card.exceptions.PlayException;
import com.hle.card.exceptions.TurnException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Application-level class that maintains basic information about a card game.
// Uses the CardGameEngine and CardGameService to perform its tasks
abstract public class CardGame {
    public List<Player> players; // Ordered
    public Player activePlayer;
    protected String gameId;
    protected Deck deck;
    protected CardGameEngine cardGameEngine;
    protected CardGameService cardGameService;

    public CardGame() {
        this.players = new ArrayList<Player>();
    }

    public void addPlayer(Player player)
    {
        if (!this.players.contains(player)) {
            this.players.add(player);
        }
    }

    public boolean removePlayer(Player player)
    {
        return this.players.remove(player);
    }

    private void setTurnOnPlayer(Player player) {
        if (this.activePlayer.equals(player)) {
            return;
        }

        this.activePlayer = player;
    }

    public void play(Player player, Play play) throws PlayException
    {
        if (!this.activePlayer.equals(player)) {
            throw new TurnException(player, play);
        }

        this.cardGameEngine.play(play);
        this.cardGameService.play(this.gameId, player, play, null);
        player.play(play.cards);
        this.activePlayer = this.getNextPlayer();
    }

    abstract protected Player getNextPlayer();

    abstract protected Player getPreviousPlayer();

    abstract public void newGame();
}
