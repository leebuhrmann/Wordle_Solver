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

public class Driver 
{
    static Game game;
    static final Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {
        File dictionaryFile = new File("src/Wordle_Dictionary.txt");
        File weightedListFile = new File("src/Weighted_Wordle_List.txt");

        String input;

        /*
         * Initial banner with prompt for user selection.
         */
        System.out.print("\n*** Worlde Solver ***\n");

        while(true)  
        {
            while(true) // Ensures proper input.
            {
                printSelectionMenu();
                input = in.nextLine();
                if(checkForDebugger(input))
                {
                    printDebugMenu();
                    debugger(in.nextLine());
                    System.out.println();
                }
                else if(!checkMenuSelection(input))
                {
                    System.out.println(input + " was not a valid choice.");
                }
                else
                {
                    break;
                }
            }   

            if(input.equals("1"))   // Starts a game played by a person (not played by a bot).
            {
                game = new Game(dictionaryFile, weightedListFile, 6);
                while(!game.completed())
                {
                    System.out.print("\nGuess a five letter word: ");
                    input = in.nextLine();
                    if(checkForDebugger(input)) // Checks for intrusive debugging action.
                    {
                        printDebugMenu();
                        debugger(in.nextLine());
                        System.out.println();
                    }
                    else if(game.gameStep(input)) // Checks input and possibly performs a game step.
                    {
                        game.printGame();
                        game.printAttemptsLeft();
                    }
                    else
                    {
                        System.out.println(input + " was not a valid word.");
                    }
                }

                game.printResultsPlayer();
            }
            else    // Starts a game played by a bot (not played by the person).
            {
                game = new Game(dictionaryFile, weightedListFile, "Atlas", 50); // TODO: current has set answer for testing
                game.botPlays();
                game.printResultsBot();
            }
        
            while(true) // Ensures proper input.
            {
                printPlayAgainMenu();
                input = in.nextLine();
                if(checkForDebugger(input))
                {
                    printDebugMenu();
                    debugger(in.nextLine());
                    System.out.println();
                }
                else if(!checkMenuSelection(input))
                {
                    System.out.println(input + " was not a valid choice.");
                }
                else
                {
                    break;
                }
            }
            
            if(input.equals("2")) // Terminate program.
            {
                break;
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
     * Checks to make sure input is valid for the game menu. Valid game
     * menu options are "1" and "2".
     * 
     * @param   inputIn User input.
     * @return  {@code true} if input was valid, {@code false} otherwise.
     */
    public static boolean checkMenuSelection(String inputIn)
    {
        inputIn = inputIn.toUpperCase();
        boolean flag = false;
        
        if(inputIn.equals("1") || inputIn.equals("2"))  // Is valid input.
        {
            flag = true;
        }
        else  // Prints error and throws false.
        {
            System.out.println(inputIn + " is not a valid seleciton.");
        }

        return flag;
    }

    /**
     * Checks to see if input was asking for the debug window.
     * 
     * @param   inputIn input entered by user.
     * @return  {@code true} if inputIn was "debugger", 
     *          {@code false} otherwise.
     */
    public static boolean checkForDebugger(String inputIn)
    {
        inputIn = inputIn.toUpperCase();
        boolean retFlag = false;

        if(inputIn.equals("DEBUGGER"))
        {
            retFlag = true;
        }

        return retFlag;
    }

    /**
     * Performs one of many debug options.
     * 
     * @param   inputIn User input.
     * @return  {@code true} if debug action was successful,
     *          {@code false} otherwise.
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
