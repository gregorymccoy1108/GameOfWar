import java.util.ArrayList;
import java.util.List;

public class ComputerOpponent {
    private List<Card> opponentCards = new ArrayList<>();
    private String name;

    public ComputerOpponent(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        opponentCards.add(card);
    }

    public void lostCard() {
        opponentCards.remove(0);
    }

    public int cardCount() {
        return opponentCards.size();
    }

    public Card warCard() {
        System.out.println(name + " played the " + opponentCards.get(0).toString());
        return opponentCards.get(0);
    }

    public Card topCard() {
        return opponentCards.get(0);
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getDeck() {
        return this.opponentCards;
    }

    public void setDeck(List<Card> deck) {
        this.opponentCards = deck;
    }
}
