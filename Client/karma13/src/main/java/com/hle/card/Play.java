package com.hle.card;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Play {
    public ArrayList<Card> cards;
    public String playId;

    public Play(List<Card> cards)
    {
        this.cards = new ArrayList<Card>(cards);
        Collections.sort(this.cards);
    }

    public Card getHighCard() {
        return this.cards.get(this.cards.size() - 1);
    }

    public boolean isSingle()
    {
        return this.isSameDenomination(1);
    }

    public boolean isDouble()
    {
        return this.isSameDenomination(2);
    }

    public boolean isTriple()
    {
        return this.isSameDenomination(3);
    }

    public boolean isQuadruple()
    {
        return this.isSameDenomination(4);
    }

    public boolean isSameKind() {
        return this.isSingle() || this.isDouble() || this.isTriple() || this.isQuadruple();
    }

    protected boolean isSameDenomination(int numCardsToCheck) {
        if (this.cards.size() != numCardsToCheck) {
            return false;
        }

        Integer denomination = 0;
        boolean isSameDenomination = true;
        for(int i = 0; i < numCardsToCheck; i++) {
            Card card = this.cards.get(i);

            if (i == 0) {
                denomination = card.denomination;
            } else {
                isSameDenomination = isSameDenomination && card.denomination == denomination;
            }
        }

        return isSameDenomination;
    }
}
