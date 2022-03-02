public class Card {
    private Suits suit;
    private Rank rank;

    public Card(Suits suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return rank.toString() + " of " + suit.toString();
    }

    public int getRank(Card cardToCheck) {
        return cardToCheck.rank.getValue(cardToCheck.rank);
    }

}
