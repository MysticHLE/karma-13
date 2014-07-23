package com.hle.karma13.card;

import com.hle.card.Card;
import com.hle.card.CardGame;
import com.hle.card.CardGameService;
import com.hle.card.Deck;
import com.hle.card.Player;
import com.hle.karma13.card.engine.K13Engine;

import java.util.ArrayList;
import java.util.List;


public class K13Game extends CardGame {
    private static final boolean USE_JOKERS = false;


    public K13Game(K13Engine cardGameEngine, CardGameService cardGameService)
    {
        this.cardGameEngine = new K13Engine();
        this.cardGameService = cardGameService;
    }

    @Override
    public void newGame() {
        Deck deck = new Deck(this.USE_JOKERS);
        for (Player player: this.players) {
            List<Card> cards = new ArrayList<Card>();
            for (int i = 0; i < 13; i++) {
                Card card = deck.draw();
                if (card.denomination == 5 && card.suit == Card.Suit.SPADE) {
                    this.activePlayer = player;
                }

                cards.add(card);
            }

            player.deal(cards);
        }
    }
}
