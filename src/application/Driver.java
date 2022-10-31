package application;

import java.io.File;
import java.util.Scanner;

import classes.Game;

/*
 * IDEAS:
 * 
 * Create GUI for game.
 * Have game on webbrowser.
 * Allow player to chose number of attempts.
 * Allow games with different dictionaries, possibly different word sizes and alphabets.
 * Allow Wordle_Letter_Weigher to pass files directly this app, or have this app call that app.7
 */

// TODO: Rewrite all method comments for consistency and understandabilty.
// TODO: Review comments of working code.

public class Driver 
{
    static Game game;
    static final Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {
        File dictionaryFile = new File("src/Wordle_Dictionary.txt");
        File weightedListFile = new File("src/Weighted_Wordle_List.txt");

        String input;
        boolean playAgain = true;

        /*
         * Initial banner with prompt for user selection.
         */
        System.out.print("\n*** Worlde Solver ***");

        while(playAgain)
        {
            System.out.println();
            printSelectionMenu();

            while(!checkMenuSelection(input = in.nextLine()))
            {
                printSelectionMenu();
            }

            if(input.equals("1"))
            {
                game = new Game(dictionaryFile, weightedListFile);
                while(!game.completed())
                {
                    System.out.println("\nGuess a five letter word: ");
                    while(!checkWord(input = in.nextLine()))
                    {
                        System.out.println("\nGuess a five letter word: ");
                    }
                    
                    System.out.println();
                    game.printGame();
                }
            }
            else
            {
                // computer plays!
                System.exit(0);
            }
        
            printPlayAgainMenu();
            while(!checkMenuSelection(input = in.nextLine()))
            {
                printPlayAgainMenu();
            }
            if(input.equals("2"))
            {
                playAgain = false;
            }
        }

        in.close();
        System.out.println("\nThanks for playing!\n");
        System.exit(0);
    }

    /**
     * Prints menu asking if user would like to play or let the computer play.
     */
    public static void printSelectionMenu()
    {
        System.out.print("\nWould you like to play a game of Wordle"
                            + "\nor let the computer play?"
                            + "\n[1] Play Wordle myself"
                            + "\n[2] Let the computer play"
                            + "\n\nPlease type a selection and press [ENTER]: ");
    }

    /**
     * Prints menu asking if player would like to play again.
     */
    public static void printPlayAgainMenu()
    {
        System.out.print("\nWould you like to play again?"
                            + "\n[1] Yes!"
                            + "\n[2] No."
                            + "\n\nPlease make a selection: ");
    }

    /**
     * Prints menu for debug options.
     */
    public static void printDebugMenu()
    {
        System.out.print( "Here are a list of debug options:"
                        + "\n\"ANSWER\"  Prints the answer"
                        + "\n\"GAME\"    Prints current game results"
                        + "\n\"LETTERS\" Prints current letter data"
                        + "\n\"WEIGHTS\" Prints weight data"
                        + "\n\nPlease type an option and press [ENTER]: ");
    }

    /**
     * TODO: I dont like how the method that is supposed to check input also performs the game step.
     * Checks that user input is a five letter word. If input is "DEBUGGER"
     * it interupts the current action with the debug window
     */
    public static boolean checkWord(String inputIn)
    {
        inputIn = inputIn.toUpperCase();
        boolean retFlag = false;

        if(inputIn.equals("DEBUGGER"))
        {
            printDebugMenu();
            debugger(in.nextLine());
        }
        else if(!game.playerGuess(inputIn)) 
        {
            System.out.println("\n" + inputIn + " is not a five letter word."
                                 + "\nGuess a five letter word: ");
        }
        else
        {
            retFlag = true;
        }

        return retFlag;
    }

    /**
     * Checks input for 
     */
    public static boolean checkMenuSelection(String inputIn)
    {
        inputIn = inputIn.toUpperCase();
        boolean flag = false;
        
        if(inputIn.equals("DEBUGGER"))    // intrusive debug action
        {
            printDebugMenu();
            debugger(in.nextLine());
        }
        else if(inputIn.equals("1") || inputIn.equals("2"))  // valid input
        {
            flag = true;
        }
        else                                                      // prints error and throws false
        {
            System.out.println(inputIn + " is not a valid seleciton.");
        }
        return flag;
    }

    /**
     * Performs one of many debug options.
     * 
     * Returns true if input was recognized.
     * Returns false if input was not recognized.
     */
    public static boolean debugger(String inputIn)
    {
        inputIn = inputIn.toUpperCase();
        boolean retFlag = true;

        switch (inputIn)
        {
            case "ANSWER":
                if(game == null)
                {
                    System.out.println("Your not playing a game");
                }
                else
                {
                    System.out.println(game.getAnswer());
                }
                break;
            case "GAME":
                if(game == null)
                {
                    System.out.println("Your not playing a game");
                }
                else
                {
                    game.printGame();
                }
                break;
            case "LETTERS":
                if(game == null)
                {
                    System.out.println("Your not playing a game");
                }
                else
                {
                    game.printLetters();
                }
                break;
            case "WEIGHTS":
                if(game == null)
                {
                    System.out.println("Your not playing a game");
                }
                else
                {
                    game.printWeightData();
                }
                break;
            default:
                System.out.println(inputIn + " is not valid debug input.");
                retFlag = false;
        }

        return retFlag;
    }
}
