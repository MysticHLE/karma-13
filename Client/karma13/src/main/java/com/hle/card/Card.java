package com.hle.card;

import java.util.Objects;

public class Card implements Comparable<Card> {
    static public enum Suit {
        SPADE, CLUB, DIAMOND, HEART, JOKER
    }

    static public enum DenominationName {
        JACK, QUEEN, KING, ACE
    }

    public int denomination;
    public Suit suit;

    public Card(Suit suit, int denomination)
    {
        this.suit = suit;
        this.denomination = denomination;
    }

    public int getDenominationRank() {
        return this.denomination;
    }

    public int getSuitRank() {
        return this.suit.ordinal();
    }

    @Override
    public String toString() {
        switch (this.denomination) {
            case 11:
                return this.getShortString(DenominationName.JACK.name());
            case 12:
                return this.getShortString(DenominationName.QUEEN.name());
            case 13:
                return this.getShortString(DenominationName.KING.name());
            case 1:
                return this.getShortString(DenominationName.ACE.name());
            default:
                return this.getShortString(Integer.toString(this.denomination));
        }
    }

    private String getShortString(String denominationString) {
        return denominationString + this.suit.name().substring(0, 1);
    }

    @Override
    public int compareTo(Card otherCard) {
        boolean isSameDenomination = this.getDenominationRank() == otherCard.getDenominationRank();
        boolean isSameCard = isSameDenomination && this.suit == otherCard.suit;
        if (isSameCard) {
            return 0;
        }

        boolean isLowerDenomination = this.getDenominationRank() < otherCard.getDenominationRank();
        boolean isLower = isLowerDenomination || (isSameDenomination && this.getSuitRank() < otherCard.getSuitRank());
        if (isLower) {
            return -1;
        }

        return 1;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Card))
            return false;
        if (other == this)
            return true;

        Card otherCard = (Card) other;
        return otherCard.denomination == this.denomination && otherCard.suit == this.suit;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.denomination, this.suit);
    }
}
