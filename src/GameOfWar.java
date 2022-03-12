import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameOfWar {

    private Scanner keyboard = new Scanner(System.in);
    private DeckOfCards deck = new DeckOfCards();
    private Player player;
    private Player opponent;
    int roundsPlayed = 0;

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
                player = new Player(keyboard.nextLine());
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
                opponent = new Player(keyboard.nextLine());
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

        while(true) {
            if(player.cardCount() == 0 || opponent.cardCount() == 0) break;
            war();
            shuffleDecks();
            roundsPlayed++;
        }
        if(player.cardCount() == 0) {
            announceWinner(opponent.getName());
        }else {
            announceWinner(player.getName());
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
            playerWins(1);
        }else {
            opponentWins(1);
        }
        System.out.println("\n**********************************");
        cardCount();
    }

    public void declareWar() throws IOException {
        int warCard = 0;
        System.out.println("Cards have the same rank");
        if(player.cardCount() < 5) {
            System.out.println("You do not have enough cards to declare war!");
            announceWinner(opponent.getName());
        }else if(opponent.cardCount() < 5){
            System.out.println("The opponent does not have enough cards to declare war!");
            announceWinner(player.getName());
        }else {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println("----------------War has been declared!!!---------------");
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            System.out.println(player.getName() + " declared war with: " + player.getCard(4).toString());
            System.out.println(opponent.getName() + " declared war with: " + opponent.getCard(4).toString());
            warCard = deck.comparison(player.getCard(4), opponent.getCard(4));
        }
        warDecision(warCard);
    }

    public void warDecision(int warSelection) throws IOException {
        switch(warSelection) {
            case 0:
                if(player.cardCount() < opponent.cardCount()){
                    opponentWins(4);
                }else {
                    playerWins(4);
                }
                break;
            case 1:
                playerWins(4);
                break;
            case -1:
                opponentWins(4);
                break;
        }
    }

    public void playerWins(int loops) {
        System.out.print("You have won this round");
        for(int i = 0; i < loops; i++) {
            player.addCard(opponent.getCard(0));
            opponent.lostCard();
        }
    }

    public void opponentWins(int loops) {
        System.out.print("Your opponent has won this round");
        for(int i = 0; i < loops; i++) {
            opponent.addCard(player.getCard(0));
            player.lostCard();
        }
    }

    public void shuffleDecks() {
        player.setDeck(deck.shuffleCards(300, player.getDeck()));
        opponent.setDeck(deck.shuffleCards(300, opponent.getDeck()));
    }

    public void announceWinner(String winner) throws IOException {
        System.out.println("\n********************************");
        System.out.println("********************************");
        System.out.println("The winner of the war is " + winner + "!!!");
        System.out.println("********************************");
        System.out.println("********************************");
        System.out.println("\n\nIt took " + roundsPlayed + " rounds to win the War!");
        System.in.read();
        System.exit(0);
    }
}
