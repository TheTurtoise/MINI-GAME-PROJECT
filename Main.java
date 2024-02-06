import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static List<String> usernames = new ArrayList<>();
    private static List<String> passwords = new ArrayList<>();
    private static List<String> score = new ArrayList<>();
    private static String line;



    public static void main(String[] args) {
        int index;

        // Reading

        reader();

        // Logic

        SignIn initialization;

        while (true) {
            initialization = new SignIn();
            index = initialization.start();

            if (index == -1) {
                break;
            } else {
                ActiveAccount accountInstance = new ActiveAccount(usernames.get(index), passwords.get(index), score.get(index));
                Game instance = new Game(accountInstance);
                instance.startGame();
                updateAccountScore(accountInstance);
            }
        }

        // Writing

        writer();


    }

    public static void updateAccountScore(ActiveAccount account) {
        score.set(usernames.indexOf(account.getUsername()), account.getScore());
    }

    public static void reader() {
        try {
            // read usernames
            FileReader fileReader = new FileReader("usernames.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                usernames.add(line);
            }

            fileReader.close();

            // read passwords
            fileReader = new FileReader("passwords.txt");
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                passwords.add(line);
            }

            fileReader.close();

            // read score
            fileReader = new FileReader("score.txt");
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                score.add(line);
            }

            bufferedReader.close();
            fileReader.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        // This just has to be here in order for the file to read and write properly
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void writer() {
        try {
            // write usernames
            FileWriter fileWriter = new FileWriter("usernames.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String item : usernames) {
                bufferedWriter.write(item);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();

            // write passwords
            fileWriter = new FileWriter("passwords.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            for (String item : passwords) {
                bufferedWriter.write(item);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

            // write score
            fileWriter = new FileWriter("score.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            for (String item : score) {
                bufferedWriter.write(item);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

            bufferedWriter.close();
            fileWriter.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        // This just has to be here in order for the file to read and write properly
        catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<String> getUsernames() {
        return usernames;
    }

    public static List<String> getPasswords() {
        return passwords;
    }

    public static List<String> getScore() {
        return score;
    }
}