package com.hle.card.exceptions;


import com.hle.card.Card;
import com.hle.card.Play;
import com.hle.card.Player;

public class TurnException extends PlayException {
    private Player player;

    public TurnException(Player player, Play play) {
        super(play);
        this.player = player;
    }

    @Override
    public String getMessage() {
        return "Not player " + this.player.playerId + "\'s turn to play: " + this.getPlayString();
    }
}
