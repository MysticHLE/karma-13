package com.hle.karma13.card;

import com.hle.card.Card;

public class K13Card extends Card {
    public K13Card(Card.Suit suit, int denomination) {
        super(suit, denomination);
    }

    @Override
    public int getDenominationRank() {
        // Given a full set of cards of a suit, give rank order to them as follows:
        // 5, 6, 7, 8, 9, 10, J, Q, K, A, 4, {2, 3}
        if (this.denomination == 1) {
            return 10;
        } else if (this.denomination == 4) {
            return 11;
        } else if (this.denomination == 2 || this.denomination == 3) {
            return 12;
        } else {
            return this.denomination - 4;
        }
    }

    @Override
    public int getSuitRank() {
        switch (this.suit) {
            case SPADE:
                return 0;
            case CLUB:
                return 1;
            case DIAMOND:
                return 2;
            default:
                return 4;
        }
    }
}
