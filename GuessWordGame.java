
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class GuessWordGame {

    static Scanner enterScanner = new Scanner(System.in);
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    boolean game = true;
    int attempts = 0;
    String word;
    String[] nullWord;
    String userChar;

    public static void main(String[] args) {
        GuessWordGame guessWordGame = new GuessWordGame();
        guessWordGame.startGame();
    }

    void startGame() {
        clearScreen();

        attempts = 0;
        game = true;

        System.out.println("Can you guess the word? Just type in a letter and I'll show you if there is a letter in the word!");

        System.out.println("Click ENTER");
        enterScanner.nextLine();

        generateWord();
    }

    void generateWord() {
        clearScreen();

        String[] words = {"MOUNTAIN", "OCEAN", "FOREST", "DESERT", "RIVER", "VALLEY", "ISLAND", "JUNGLE", "WILDERNESS", "WATERFALL", "SUNSET", "STORM", "HORIZON", "GALAXY", "UNIVERSE", "EARTH", "SKY", "VOLCANO", "CANYON", "LAGOON"};

        int randomNumber = random.nextInt(words.length);
        word = words[randomNumber];

        generateNullWord();
    }

    void generateNullWord() {
        nullWord = new String[word.length()];

        for (int i = 0; i < nullWord.length; i++) {
            nullWord[i] = "_";
        }

        controlLetter();
    }

    void gameLetter() {
        if (game == true) {
            while (true) {
                System.out.println("You letter:");
                userChar = scanner.nextLine();
                clearScreen();

                if (userChar.trim().length() != 1) {
                    clearScreen();

                    attempts++;

                    System.out.println("Type one letter!");
                    System.out.println("Click ENTER");
                    enterScanner.nextLine();

                    clearScreen();
                    printStats();
                } else {
                    attempts++;

                    controlLetter();
                    break;
                }
            }   
        }
    }

    void controlLetter() {
        String[] arrayOfWord = word.split("");
        for (int i = 0; i < arrayOfWord.length; i++) {
            if (arrayOfWord[i].equalsIgnoreCase(userChar)) {
                nullWord[i] = userChar.toUpperCase();
            }
        }

        printStats();
        controlWin();
        gameLetter();
    }

    void controlWin() {
        int count = 0;

        for (String nullWordLoop : nullWord) {
            if (nullWordLoop.equals("_")) {
                count++;
            }
        }

        if (count == 0) {
            game = false;

            clearScreen();
            printStats();

            System.out.println("WIN");

            System.out.println("Click ENTER");
            enterScanner.nextLine();

            startGame();
        }
    }

    public void printStats() {
        System.out.println("Attempts made: " + attempts);
        System.out.println("-------------------");
        System.out.println(Arrays.toString(nullWord));  
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}