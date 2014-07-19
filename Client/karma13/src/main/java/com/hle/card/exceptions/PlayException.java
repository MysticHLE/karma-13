package com.hle.card.exceptions;


import com.hle.card.Card;
import com.hle.card.Play;

public class PlayException extends Exception {
    public Play play;

    public PlayException(Play play) {
        super();
        this.play = play;
    }

    @Override
    public String getMessage() {
        return "Invalid play: " + this.getPlayString();
    }

    protected String getPlayString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Card card : this.play.cards) {
            stringBuilder.append(card.toString());
        }
        return stringBuilder.toString();
    }
}
