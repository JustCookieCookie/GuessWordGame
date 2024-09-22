
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class GuessWordGame {

    public static final String resetColor = "\u001B[0m";
    public static final String redColor = "\u001B[31m";
    public static final String greenColor = "\u001B[32m";
    public static final String yellowColor = "\u001B[33m";
    public static final String blueColor = "\u001B[34m";
    public static final String purpleColor = "\u001B[35m";

    static Scanner enterScanner = new Scanner(System.in);
    Random random = new Random();
    Scanner scanner = new Scanner(System.in);

    boolean game = true;
    int maxAttempts;
    int attempts = 0;
    int count;
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
        userChar = "";

        System.out.println("Can you guess the word? Just type in a letter and I'll show you if there is a letter in the word!");

        System.out.println(purpleColor + "Click ENTER" + resetColor);
        enterScanner.nextLine();

        generateWord();
    }

    void generateWord() {
        clearScreen();

        String[] words = {"MOUNTAIN", "OCEAN", "FOREST", "DESERT", "RIVER", "VALLEY", "ISLAND", "JUNGLE", "WILDERNESS", "WATERFALL", "SUNSET", "STORM", "HORIZON", "GALAXY", "UNIVERSE", "EARTH", "SKY", "VOLCANO", "CANYON", "LAGOON"};

        int randomNumber = random.nextInt(words.length);
        word = words[randomNumber];

        maxAttempts = word.length() * 2;

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
                System.out.print(blueColor + "You letter: " + resetColor);
                userChar = scanner.nextLine();

                clearScreen();
                controlAttempts();

                if (userChar.trim().length() != 1) {
                    clearScreen();

                    attempts++;
                    controlAttempts();

                    System.out.println(redColor + "Type one letter!" + resetColor);
                    System.out.println(purpleColor + "Click ENTER" + resetColor);
                    enterScanner.nextLine();

                    clearScreen();
                    printStats();
                } else {
                    attempts++;

                    controlLetter();
                    break;
                }
                controlAttempts();
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

        controlWin();
        controlAttempts();
        printStats();
        gameLetter();
    }

    void controlWin() {
        count = 0;

        for (String nullWordLoop : nullWord) {
            if (nullWordLoop.equals("_")) {
                count++;
            }
        }

        if (count == 0) {
            game = false;

            clearScreen();
            printStats();

            System.out.println(greenColor + "WIN" + resetColor);

            System.out.println(purpleColor + "Click ENTER" + resetColor);
            enterScanner.nextLine();

            startGame(); 
        }
    }

    void controlAttempts() {
        if (attempts >= maxAttempts) {
            game = false;
            
            clearScreen();

            System.out.println(redColor + "DEAD" + resetColor);

            System.out.println(purpleColor + "Click ENTER" + resetColor);
            enterScanner.nextLine();

            startGame();   
        }
    }

    void printStats() {
        System.out.println(yellowColor + "Attempts made: " + attempts + "/" + maxAttempts);
        System.out.println("-------------------");
        System.out.println(Arrays.toString(nullWord) + resetColor);  
    }

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}