package com.hle.karma13.card.engine;


import com.hle.card.Card;
import com.hle.card.CardGameEngine;
import com.hle.card.Deck;
import com.hle.card.Play;
import com.hle.card.exceptions.PlayException;
import com.hle.karma13.card.K13Play;

import java.util.ArrayList;
import java.util.List;

public class K13Engine implements CardGameEngine {
    private K13RuleEngine ruleEngine;
    private List<Card> cards;
    private Deck deck;

    public K13Engine() {
        this.ruleEngine = new K13RuleEngine();
        this.reset();
    }

    public void play(Play play) throws PlayException {
        this.ruleEngine.play((K13Play) play);
        this.cards.addAll(play.cards);
    }

    public void reset() {
        this.cards = new ArrayList<Card>();
        this.deck = new Deck(false);
        this.deck.shuffle();
    }

    public void undoPlay(Play play) {

    }

}
