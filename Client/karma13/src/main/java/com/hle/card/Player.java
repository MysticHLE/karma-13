package com.hle.card;

import java.util.List;
import java.util.Objects;

public class Player {
    public String playerId;
    public List<Card> cards;

    public Player(String playerId)
    {
        this.playerId = playerId;
    }

    public void deal(List<Card> cards)
    {
        this.cards = cards;
    }

    public final List<Card> getCards() { return this.cards; }

    public void play(List<Card> cards)
    {
        this.cards.removeAll(cards);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Player))
            return false;
        if (other == this)
            return true;

        Player otherPlayer = (Player) other;
        return otherPlayer.playerId.equals(this.playerId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.playerId, this.cards);
    }
}
