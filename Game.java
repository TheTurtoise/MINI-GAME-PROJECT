import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Game {
    private Scanner Input = new Scanner(System.in);
    private int bet;
    private ActiveAccount player;
    private int sum = 0;
    private Random random_method = new Random();
    private ArrayList<Integer> cards = new ArrayList<>();
    private ArrayList<Integer> playerHand = new ArrayList<>();
    private ArrayList<Integer> dealerHand = new ArrayList<>();

    Game () {

    }

    Game (ActiveAccount account) {
        player = account;
    }

    public void setActiveAccount(ActiveAccount account) {
        player = account;
    }

    public void betting() {

        System.out.println("You currently have " +  player.getScore() + " points");

        while (true) {
            System.out.print("How much would you like to bet: ");
            bet = Input.nextInt();

            if (bet > Integer.parseInt(player.getScore())) {
                System.out.println("You don't have enough points to bet that much!");
            } else if (bet < 0) {
                System.out.println("That bet is negative!");
            } else if (bet == 0) {
                System.out.println("You can't bet nothing you silly goose! ( ͡° ͜ʖ ͡°)");
            } else {
                System.out.println("You have bet " + bet + " points!");
                player.setScore(String.valueOf(Integer.parseInt(player.getScore()) - bet));
                break;
            }
        }
    }

    public void deck() {
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

    public void startGame() {

        System.out.println("Welcome to our blackjack game!");
        while (true) {
            playerHand.clear();
            dealerHand.clear();
            cards.clear();

            betting();

            deck();

            hit(playerHand);
            hit(dealerHand);
            hit(playerHand);
            hit(dealerHand);

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
                System.out.println("The dealer flips his face down card");
                System.out.println("The dealer's cards total a value of " + sumOfHand(dealerHand));
                int cardNum = 1;
                while (sumOfHand(dealerHand) < 17) {
                    hit(dealerHand);
                    if (cardNum == 1) {
                        System.out.println("The dealer draws their 1st card");
                    }
                    else if (cardNum == 2) {
                        System.out.println("The dealer draws their 2nd card");
                    }
                    else if (cardNum == 3) {
                        System.out.println("The dealer draws their 3rd card");
                    } else {
                        System.out.println("The dealer draws their " + cardNum + "th card");
                    }
                    cardNum++;
                    System.out.println("The dealer's cards now total a value of " + sumOfHand(dealerHand));
                }
            }

            score(bet);

            System.out.println("You now have " + player.getScore() + " points");

            if (player.getScore().equals("0")) {
                System.out.println("Wow, I guess 99% of gamblers don't quit before they win big");
                System.out.println("But the casino pities you, and you now have 5 points");
                player.setScore("5");
            }

            System.out.println("Would you like to play again? (y/n) ");
            String choice = Input.next();

            while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
                System.out.println("Invalid selection");
                System.out.println("Would you like to play again? (y/n) ");
                choice = Input.next();
            }

            if (choice.equalsIgnoreCase("y")) {
                continue;
            }
            else if (choice.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    public void hit(ArrayList<Integer> person) {
        int randCard = random_method.nextInt(cards.size());
        person.add(cards.get(randCard));
        cards.remove(randCard);
    }

    public void score(int bet) {
        if (sumOfHand(playerHand) > sumOfHand(dealerHand) && sumOfHand(playerHand) <= 21 || sumOfHand(dealerHand) > 21) {
            System.out.println("You win!");
            player.setScore(String.valueOf(Integer.parseInt(player.getScore()) + bet * 2));
        } else if (sumOfHand(playerHand) < sumOfHand(dealerHand) && sumOfHand(dealerHand) <= 21 || sumOfHand(playerHand) > 21) {
            System.out.println("You lose!");
        } else {
            System.out.println("It's a tie!");
            System.out.println(playerHand);
            System.out.println(dealerHand);
        }

    }

    public int sumOfHand(ArrayList<Integer> hand) {
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