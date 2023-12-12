import java.util.Scanner;

public class SignIn {
    private static Scanner input = new Scanner(System.in);
    private static boolean login = true;
    private static String choice;
    private static int index;
    SignIn() {

    }

    public static int start() {
        while (login) {
            System.out.println("Enter L to login, and R to register (Input X to exit) ");
            choice = input.nextLine();
            switch (choice) {
                case "L":
                    login();
                    break;
                case "R":
                    register();
                    break;
                case "X":
                    break;
            }
        }
        return index;
    }

    private static void login() {
        while (true) {
            System.out.println("Enter username");
            String username = input.nextLine();
            System.out.println("Enter password");
            String password = input.nextLine();
            if (FileIO.getUsernames().contains(username)) {
                index = FileIO.getUsernames().indexOf(username);
                if (FileIO.getPasswords().get(index).equals(password)) {
                    System.out.println("Login successful");

                    System.out.println("Big dubs");

                    login = false;
                    break;
                } else {
                    System.out.println("Login failed");
                    do {
                        System.out.print("Try again? (y/n) ");
                        choice = input.nextLine();
                        if (choice.equals("y")) {

                        } else if (choice.equals("n")) {
                            break;
                        } else {
                            System.out.println("Invalid Input");
                        }
                    } while (!choice.equals("y"));
                }
            } else {
                System.out.println("Login failed");
                do {
                    System.out.print("Try again? (y/n) ");
                    choice = input.nextLine();
                    if (choice.equals("y")) {

                    } else if (choice.equals("n")) {
                        break;
                    } else {
                        System.out.println("Invalid Input");
                    }
                } while (!choice.equals("y"));
            }
        }
    }

    private static void register() {
        System.out.println("Enter username");
        String username = input.nextLine();
        System.out.println("Enter password");
        String password = input.nextLine();

        FileIO.getUsernames().add(username);
        FileIO.getPasswords().add(password);
    }

}
