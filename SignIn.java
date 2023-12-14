import java.util.Scanner;

public class SignIn {
    private static Scanner input = new Scanner(System.in);
    private static boolean loginLoop = true;
    private static boolean tryLogin = true;
    private static String choice;
    private static int index;

    SignIn() {

    }

    public static int start() {
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

    private static void login() {
        while (tryLogin) {
            System.out.println("Enter username");
            String username = input.nextLine();
            System.out.println("Enter password");
            String password = input.nextLine();
            if (FileIO.getUsernames().contains(username)) {
                index = FileIO.getUsernames().indexOf(username);
                if (FileIO.getPasswords().get(index).equals(password)) {
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

    private static void register() {
        System.out.println("Enter username");
        String username = input.nextLine();
        if (!FileIO.getUsernames().contains(username)) {
            System.out.println("Enter password");
            String password = input.nextLine();

            FileIO.getUsernames().add(username);
            FileIO.getPasswords().add(password);
            FileIO.getScore().add("5");
        } else {
            System.out.println("This username is already taken!");
        }
    }

}
