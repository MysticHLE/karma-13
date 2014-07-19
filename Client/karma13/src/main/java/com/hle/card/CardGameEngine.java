package com.hle.card;

import com.hle.card.exceptions.PlayException;

// The card game engine only keeps track of local state of the game - to ensure consistency of game rules. It makes no server calls.
public interface CardGameEngine {
    public void play(Play play) throws PlayException;
    public void reset();
    public void undoPlay(Play play);
}
