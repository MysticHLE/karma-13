package com.hle.card;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    public List<Card> cards;

    public static Integer maxDenomination = 13;
    public static Integer minDenomination = 1;

    public Deck(Boolean useJokers) {
        this.cards.addAll(this.generateSuit(Card.Suit.SPADE));
        this.cards.addAll(this.generateSuit(Card.Suit.CLUB));
        this.cards.addAll(this.generateSuit(Card.Suit.DIAMOND));
        this.cards.addAll(this.generateSuit(Card.Suit.HEART));

        if (useJokers) {
            this.cards.addAll(this.generateSuit(Card.Suit.JOKER));
        }
    }

    public void shuffle() {
        Collections.shuffle(this.cards, new Random(System.nanoTime()));
    }

    // Throw DeckEmptyException
    public Card draw() {
        return this.cards.remove(0);
    }

    private List<Card> generateSuit(Card.Suit suit) {
        List<Card> cards = new ArrayList<Card>();

        if (suit == Card.Suit.JOKER) {
            cards.add(new Card(Card.Suit.JOKER, 0));
            cards.add(new Card(Card.Suit.JOKER, 1));
        } else {
            for (int denomination = Deck.minDenomination; denomination < Deck.maxDenomination; denomination++) {
                cards.add(new Card(suit, denomination));
            }
        }

        return cards;
    }
}
