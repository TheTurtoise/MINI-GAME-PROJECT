import java.util.Scanner;

public class SignIn {
    private Scanner input = new Scanner(System.in);
    private boolean loginLoop = true;
    private boolean tryLogin = true;
    private String choice;
    private int index;

    SignIn() {

    }

    public int start() {
        while (loginLoop) {
            System.out.println("Enter L to login, and R to register (Input X to exit) ");
            choice = input.nextLine().toUpperCase();
            switch (choice) {
                case "L":
                    login();
                    break;
                case "R":
                    register();
                    break;
                case "X":
                    index = -1;
                    loginLoop = false;
                    break;
            }
        }
        return index;
    }

    private void login() {
        while (tryLogin) {
            System.out.println("Enter username");
            String username = input.nextLine();
            System.out.println("Enter password");
            String password = input.nextLine();
            if (Main.getUsernames().contains(username)) {
                index = Main.getUsernames().indexOf(username);
                if (Main.getPasswords().get(index).equals(password)) {
                    System.out.println("Login successful");

                    System.out.println("Big dubs");

                    loginLoop = false;
                    break;
                } else {
                    System.out.println("Login failed");
                    do {
                        System.out.print("Try again? (y/n) ");
                        choice = input.nextLine().toLowerCase();
                        if (choice.equalsIgnoreCase("y")) {

                        } else if (choice.equalsIgnoreCase("n")) {
                            tryLogin = false;
                            break;
                        } else {
                            System.out.println("Invalid Input");
                        }
                    } while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
                }
            } else {
                System.out.println("Login failed");
                do {
                    System.out.print("Try again? (y/n) ");
                    choice = input.nextLine().toLowerCase();
                    if (choice.equalsIgnoreCase("y")) {

                    } else if (choice.equalsIgnoreCase("n")) {
                        tryLogin = false;
                        break;
                    } else {
                        System.out.println("Invalid Input");
                    }
                } while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
            }
        }
    }

    private void register() {
        System.out.println("Enter username");
        String username = input.nextLine();
        if (!Main.getUsernames().contains(username)) {
            System.out.println("Enter password");
            String password = input.nextLine();

            Main.getUsernames().add(username);
            Main.getPasswords().add(password);
            Main.getScore().add("5");
        } else {
            System.out.println("This username is already taken!");
        }
    }

}