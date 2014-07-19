package com.hle.karma13.card.engine;


import com.hle.card.Card;
import com.hle.card.exceptions.PlayException;
import com.hle.karma13.card.K13Card;
import com.hle.karma13.card.K13Play;

public class K13RuleEngine {
    static enum Rule {
        SINGLE, DOUBLE, TRIPLE, QUADRUPLE, COMBO, CLEAR
    }

    private Rule rule;
    private Card highCard; // Highest card of the rule that was played

    private int numConsecutiveDenomination; // Used to keep track of total number of consecutive denominations played that might form a clear
    private int lastPlayedDenomination;
    private int comboLength;

    K13RuleEngine() {
        this.comboLength = 0;
    }

    public Rule getCurrentRule() {
        return this.rule;
    }

    public Card getCurrentHighestCard() {
        return this.highCard;
    }

    public int getCurrentComboLength() {
        return this.comboLength;
    }

    public boolean isSameKind() {
        return this.rule == Rule.SINGLE || this.rule == Rule.DOUBLE || this.rule == Rule.TRIPLE;
    }

    void play(K13Play play) throws PlayException {
        if (!this.isValidPlay(play)) {
            throw new PlayException(play);
        }

        Rule rule = this.getRule(play);
        Card newHighCard = play.getHighCard();

        // Check for clear
        if (rule == Rule.CLEAR) {
            this.clearRule();
            return;
        }

        // Check for valid 4-of-a-kind clear
        if (this.isSameKind() && rule == Rule.QUADRUPLE) {
            if (this.highCard.getDenominationRank() < newHighCard.getDenominationRank()) {
                this.clearRule();
                return;
            } else {
                throw new PlayException(play);
            }
        }

        // Check for valid higher combo
        if (this.rule == Rule.COMBO && rule == Rule.COMBO) {
            if (play.cards.size() == this.comboLength && this.highCard.getDenominationRank() < newHighCard.getDenominationRank()) {
                this.highCard = newHighCard;
                return;
            } else {
                throw new PlayException(play);
            }
        }

        // Same denomination played this round as last round
        if (this.lastPlayedDenomination == newHighCard.denomination) {
            // If a 2 or 3, simply keep track of whether it totals up to be a clear
            if (newHighCard.denomination == 2 || newHighCard.denomination == 3) {
                this.numConsecutiveDenomination += play.cards.size();
            }
            // Check suit rank and throw exception as needed, tracking if it totals up to be a clear
            else if (this.rule == rule && this.highCard.getSuitRank() < newHighCard.getSuitRank()) {
                this.numConsecutiveDenomination += play.cards.size();
                this.highCard = newHighCard;
            }
            // Played the same denomination but lower suit
            else {
                throw new PlayException(play);
            }
        }
        // Greater or equal rank value this round compared to last round
        else if (newHighCard.compareTo(this.highCard) >= 0) {
            if (newHighCard.denomination == 2) {
                this.reduceRule();
            } else if (this.rule != rule && newHighCard.denomination != 3){
                throw new PlayException(play);
            }

            this.lastPlayedDenomination = newHighCard.denomination;
            this.numConsecutiveDenomination = play.cards.size();
            this.highCard = newHighCard;
        } else {
            throw new PlayException(play);
        }

        if (this.isCompletedClear()) {
            this.clearRule();
        }

        // Fresh play
        if (this.rule == null) {
            this.setRule(rule, newHighCard, play.cards.size());
        }
    }

    public boolean isValidPlay(K13Play play) {
        return play.isSameKind() || play.isClear() || play.isCombo();
    }

    private boolean isCompletedClear() {
        return numConsecutiveDenomination == 4;
    }

    private void setRule(Rule rule, Card newHighCard, int numCards) {
        this.rule = rule;
        this.highCard = newHighCard;

        if (rule == Rule.SINGLE || rule == Rule.DOUBLE || rule == Rule.TRIPLE || rule == Rule.QUADRUPLE) {
            this.lastPlayedDenomination = this.highCard.denomination;
            this.numConsecutiveDenomination = numCards;
        } else if (rule == Rule.COMBO) {
            this.comboLength = numCards;
        }
    }

    public void reduceRule() {
        if (this.rule == Rule.SINGLE) {
            this.highCard = new K13Card(Card.Suit.SPADE, 5);
        } else if (this.rule == Rule.DOUBLE) {
            this.highCard = new K13Card(Card.Suit.CLUB, 5);
        } else if (this.rule == Rule.TRIPLE) {
            this.highCard = new K13Card(Card.Suit.DIAMOND, 5);
        } else if (this.rule == Rule.COMBO) {
            this.highCard = new K13Card(Card.Suit.SPADE, 4 + this.comboLength);
        }
    }

    public void clearRule() {
        this.rule = null;
        this.lastPlayedDenomination = 0;
        this.numConsecutiveDenomination = 0;
    }

    public Rule getRule(K13Play play) throws PlayException {
        if (play.isSingle()) {
            return Rule.SINGLE;
        } else if (play.isDouble()) {
            return Rule.DOUBLE;
        } else if (play.isTriple()) {
            return Rule.TRIPLE;
        } else if (play.isQuadruple()) {
            return Rule.QUADRUPLE;
        } else if (play.isCombo()) {
            return Rule.COMBO;
        } else if (play.isClear()) {
            return Rule.CLEAR;
        } else {
            throw new PlayException(play);
        }
    }
}
