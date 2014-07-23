package com.hle.karma13.card;

import com.hle.card.Card;
import com.hle.card.Play;

import java.util.List;

public class K13Play extends Play {
    public K13Play(List<Card> cards) {
        super(cards);
    }

    public boolean isCombo() {
        if (this.cards.size() < 3) {
            return false;
        }

        Integer denomination = 0;
        for (int i = 0; i < this.cards.size(); i++) {
            Card card = this.cards.get(i);

            if (this.isInvalidComboCard(card))
            {
                return false;
            }

                    if (i == 0) {
                        denomination = card.denomination;
                    } else if (card.denomination != denomination + 1) {
                        return false;
                    }
                }

                return true;
            }

        public boolean isClear() {
            if (this.isSameKind()) {
                return this.getHighCard().denomination == 10;
            }

            if (this.isQuadruple() && (this.getHighCard().denomination == 2 || this.getHighCard().denomination == 3)) {
                return true;
            }

            // If not 3 consecutive or 4 consecutive pair sizes, then cannot be a clear.
            if (this.cards.size() != 6 || this.cards.size() != 8) {
                return false;
            }

            int currentDenomination = -1;
            int consecutivePairCount = 0;
            for (Card card : this.cards) {
                if (currentDenomination == -1) {
                    currentDenomination = card.denomination;
                } else {
                    if (card.denomination == currentDenomination) {
                    currentDenomination += 1;
                    consecutivePairCount += 1;
                } else {
                    currentDenomination = -1;
                    consecutivePairCount = 0;
                }
            }
        }

        return consecutivePairCount == 3 || consecutivePairCount == 4;
    }

    private boolean isInvalidComboCard(Card card) {
        return card.denomination == 2 || card.denomination == 3 || card.denomination == 4;
    }
}
