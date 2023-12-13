import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Game {
    private static Scanner Input = new Scanner(System.in);
    private static int bet;
    private static int index;
    private static int sum = 0;
    private static Random random_method = new Random();
    private static ArrayList<Integer> cards = new ArrayList<>();
    private static ArrayList<Integer> playerHand = new ArrayList<>();
    private static ArrayList<Integer> dealerHand = new ArrayList<>();

    Game () {

    }

    public static void setIndex(int newIndex) {
        index = newIndex;
    }

    public static void betting() {
        System.out.println("Welcome to our blackjack game!");
        System.out.println("You currently have " +  FileIO.getScore().get(index) + " points");

        System.out.print("How much would you like to bet: ");
        bet = Input.nextInt();

        if (bet > Integer.parseInt(FileIO.getScore().get(index))) {
            System.out.println("You don't have enough points to bet that much!");
        } else {
            System.out.println("You have bet " + bet + " points!");
            FileIO.getScore().set(index, String.valueOf(Integer.parseInt(FileIO.getScore().get(index)) - bet));
        }
    }

    public static void deck() {
        int value;
        for(int i = 0; i <= 3; i++) {
            for(int m = 1; m < 14; m++) {
                if (m > 10) {
                    value = 10;
                } else if (m == 1) {
                    value = 11;
                } else {
                    value = m;
                }
                cards.add(value);
            }
        }

        Collections.shuffle(cards);
    }

    public static void startGame() {
        betting();

        deck();

        hit(playerHand);
        hit(dealerHand);
        hit(playerHand);
        hit(dealerHand);

        System.out.println(dealerHand);
        System.out.println(playerHand);

        System.out.println("Your cards total a value of " + sumOfHand(playerHand));
        if (playerHand.contains(11)) {
            System.out.println("You have an Ace!");
        }
        System.out.println("The dealer's first card has a value of " + dealerHand.get(0));
        if (dealerHand.get(0) == 11) {
            System.out.println("Its an Ace!");
        }

        boolean bypassDealer = false;

        while (true) {
            System.out.println("Would like to hit or stand? (h/s) ");
            String choice = Input.next();

            while (!choice.equalsIgnoreCase("h") && !choice.equalsIgnoreCase("s")) {
                System.out.println("Invalid selection");
                System.out.println("Would like to hit or stand? (h/s) ");
                choice = Input.next();
            }

            if (choice.equalsIgnoreCase("h")) {
                hit(playerHand);
                System.out.println("Your cards total a value of " + sumOfHand(playerHand));
                if (sumOfHand(playerHand) > 21) {
                    bypassDealer = true;
                    break;
                }
            } else if (choice.equalsIgnoreCase("s")) {
                break;
            }

        }

        if (!bypassDealer) {
            System.out.println("The dealer's cards total a value of " + sumOfHand(dealerHand));
            while (sumOfHand(dealerHand) < 17) {
                hit(dealerHand);
                System.out.println("The dealer draws");
                System.out.println("The dealer's cards now total a value of " + sumOfHand(dealerHand));
            }
        }

        score(5);

    }

    public static void hit(ArrayList<Integer> person) {
        int randCard = random_method.nextInt(cards.size());
        person.add(cards.get(randCard));
        cards.remove(randCard);
    }

    public static void score(int bet) {
        if (sumOfHand(playerHand) > sumOfHand(dealerHand) && sumOfHand(playerHand) <= 21 || sumOfHand(dealerHand) > 21) {
            System.out.println("You win!");
            FileIO.getScore().set(index, String.valueOf(Integer.parseInt(FileIO.getScore().get(index)) + bet * 2));
        } else if (sumOfHand(playerHand) < sumOfHand(dealerHand) && sumOfHand(dealerHand) <= 21 || sumOfHand(playerHand) > 21) {
            System.out.println("You lose!");
        } else {
            System.out.println("It's a tie!");
            System.out.println(playerHand);
            System.out.println(dealerHand);
        }
      
      // playerHand.clear();
      // dealerHand.clear();
      // cards.clear();
      // deck();
      // startGame();
    }

    public static int sumOfHand(ArrayList<Integer> hand) {
        // ace case in here
        sum = 0;

        for (int integer : hand) {
            sum += integer;
        }

        if (sum > 21 && hand.contains(11)) {
            hand.set(hand.indexOf(11), 1);
            sumOfHand(hand);
        }

        return sum;
    }
}
