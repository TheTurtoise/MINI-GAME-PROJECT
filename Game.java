import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Game {

    private static Scanner Input = new Scanner(System.in);
    private static int bet;
    private static int index;

    private static Random random_method = new Random();

    private static ArrayList<Integer> cards = new ArrayList<>();

    // private static int[] values = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};

    private static ArrayList<Integer> playerHand = new ArrayList<>();

    private static ArrayList<Integer> dealerHand = new ArrayList<>();

    Game () {

    }

    public static void betting() {
        System.out.println("Welcome to our blackjack game!");
        System.out.println("You currently have " +  FileIO.getScore().get(index) + " points");

        System.out.print("How much would you like ot bet: ");
        bet = Input.nextInt();

        if (bet > Integer.parseInt(FileIO.getScore().get(index))) {
            System.out.println("You don't have enough points to bet that much!");
        } else {
            System.out.println("You have bet " + bet + " points!");
            FileIO.getScore().set(index, String.valueOf(Integer.parseInt(FileIO.getScore().get(index)) - bet));
        }
    }


    public static void deck() {
        // int index = 0;
        // for (int i = 0; i <= 3; i++) {
        //     for (int rank = 1; rank <= 13; rank++) {
        //         cards[index] = values[]);
        //         index++;
        //     }
        // }

        int value;
        for(int i = 0; i <= 3; i++) {
            for(int m = 1; m < 14; m++) {
                if (m > 10) {
                    value = 10;
                } else {
                    value = m;
                }
                cards.add(value);
            }
        }

        Collections.shuffle(cards);
    }

    public static void setIndex(int newIndex) {
        index = newIndex;
    }

    public static void startGame() {
        hit(playerHand);
        hit(dealerHand);
        hit(playerHand);
        hit(dealerHand);

        System.out.println(playerHand);
        System.out.println(sumOfHand(playerHand));

        System.out.println(dealerHand);
        System.out.println(sumOfHand(dealerHand));

        score(5);

    }

    public static void hit(ArrayList<Integer> person) {
        int randCard = random_method.nextInt(cards.size());
        person.add(cards.get(randCard));
        cards.remove(randCard);
    }

    public static void score(int bet) {
        if (sumOfHand(playerHand) > sumOfHand(dealerHand) || sumOfHand(dealerHand) > 21) {
            System.out.println("You win!");
            FileIO.getScore().set(index, String.valueOf(Integer.parseInt(FileIO.getScore().get(index)) + bet * 2));
        } else if (sumOfHand(playerHand) < sumOfHand(dealerHand) || sumOfHand(playerHand) > 21) {
            System.out.println("You lose!");
        } else {
            System.out.println("It's a tie!");
        }
      
      // playerHand.clear();
      // dealerHand.clear();
      // cards.clear();
      // deck();
      // startGame();
    }

    public static int sumOfHand(ArrayList<Integer> hand) {
        int sum = 0;
        for (Integer integer : hand) {
            sum += integer;
        }
        return sum;
    }
}
