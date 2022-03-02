import java.util.ArrayList;
import java.util.List;

public class PlayerOne {
    private List<Card> playerCards = new ArrayList<>();
    private String name;

    public PlayerOne(String name) {
        this.name = name;
    }

    public void addCard(Card card) {
        playerCards.add(card);
    }

    public void lostCard() {
        playerCards.remove(0);
    }

    public Card warCard() {
        System.out.println(name + " played the " + playerCards.get(0).toString());
        return playerCards.get(0);
    }

    public int cardCount() {
        return playerCards.size();
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getDeck() {
        return this.playerCards;
    }

    public Card topCard() {
        return playerCards.get(0);
    }

    public void setDeck(List<Card> deck) {
        this.playerCards = deck;
    }
}
