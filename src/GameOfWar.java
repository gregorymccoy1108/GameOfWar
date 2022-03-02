import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameOfWar {

    private Scanner keyboard = new Scanner(System.in);
    private DeckOfCards deck = new DeckOfCards();
    private PlayerOne player;
    private ComputerOpponent opponent;

    public GameOfWar() throws IOException {
        System.out.println("*************************************");
        System.out.println("*****Welcome to the game of war!*****");
        System.out.println("*************************************");
        constructPlayer();
        constructOpponent();
        startWar();
    }

    private void constructPlayer() {
        while(true) {
            try{
                System.out.print("What is your name: ");
                player = new PlayerOne(keyboard.nextLine());
                if(player.getName().isEmpty()) throw new InputMismatchException();
                break;
            }catch (InputMismatchException e) {
                System.out.println("Name can not be empty!");
            }
        }
    }

    private void constructOpponent() {
        while(true) {
            try{
                System.out.print("What is the computers name: ");
                opponent = new ComputerOpponent(keyboard.nextLine());
                if(opponent.getName().isEmpty()) throw new InputMismatchException();
                break;
            }catch (InputMismatchException e) {
                System.out.println("Name for opponent can not be empty!");
            }
        }
    }

    private void startWar() throws IOException {
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.println(player.getName() + " has pitted war against " + opponent.getName());
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        deck.dealCards(player, opponent);
        cardCount();

        while(player.cardCount() != 0 || opponent.cardCount() != 0) {
            war();
        }
    }

    public void cardCount() {
        System.out.println(player.getName() + " has " + player.cardCount() + " cards.");
        System.out.println(opponent.getName() + " has " + opponent.cardCount() + " cards.");
    }

    public void war() throws IOException {
        System.out.println("\n**********************************");
        System.out.print("Press Enter To Fight!");
        System.in.read();
        int warCard = deck.comparison(player.warCard(), opponent.warCard());
        if(warCard == 0) {
            declareWar();
        }else if(warCard == 1) {
            playerWins();
        }else {
            opponentWins();
        }
        System.out.println("\n**********************************");
        cardCount();
    }

    public void declareWar() {
        System.out.print("Cards have the same rank");
    }

    public void playerWins() {
        System.out.print("You have won this fight");
        player.addCard(opponent.topCard());
        opponent.lostCard();
    }

    public void opponentWins() {
        System.out.print("Your opponent has won this round");
        opponent.addCard(player.topCard());
        player.lostCard();
    }

    public void shuffleDecks() {
        player.setDeck(deck.shuffleCards(300, player.getDeck()));
        opponent.setDeck(deck.shuffleCards(300, opponent.getDeck()));
    }

}
