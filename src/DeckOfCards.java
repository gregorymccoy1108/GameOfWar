import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckOfCards {

    private List<Card> deck = new ArrayList<>();

    public DeckOfCards() {
        for(Suits suit : Suits.values()) {
            for(Rank rank : Rank.values()) {
                Card cardToAdd = new Card(suit, rank);
                deck.add(cardToAdd);
            }
        }
    }

    public List<Card> shuffleCards(int numberToShuffle, List<Card> deckToShuffle) {
        int deckSize = deckToShuffle.size();
        int randomOne;
        int randomTwo;
        Card temp;

        for(int i = 0; i < numberToShuffle; i++) {
            randomOne = randomNumber(deckSize);
            randomTwo = randomNumber(deckSize);
            if (randomOne == randomTwo) {
                continue;
            }else {
                temp = deckToShuffle.get(randomOne);
                deckToShuffle.set(randomOne, deck.get(randomTwo));
                deckToShuffle.set(randomTwo, temp);
            }
        }
        return deckToShuffle;
    }

    public void dealCards(PlayerOne player, ComputerOpponent opponent) {
        shuffleCards(1000, deck);
        while(!deck.isEmpty()) {
            player.addCard(deck.remove(0));
            opponent.addCard(deck.remove(0));
        }
    }

    public void war() {

    }

    public void deckSize() {
        int deckSize = deck.size();
        System.out.printf("You have %d cards left in the deck.", deckSize);
    }

    private int randomNumber(int random) {
        return (int)((Math.random() * random));
    }


    public int comparison(Card playerOne, Card opponent) {
        int playerOneRank = playerOne.getRank(playerOne);
        int opponentRank = opponent.getRank(opponent);

        if(playerOneRank == opponentRank) {
            return 0;
        }else if(playerOneRank > opponentRank) {
            return 1;
        }
        return -1;
    }
}
