package application;

import java.io.File;
import java.util.Scanner;

import classes.Game;

public class Driver 
{
    static Game game;
    static final Scanner in = new Scanner(System.in);
    public static void main(String[] args)
    {
        /*
         * Later I may make it possible for the Wordle_Letter_Weigher app to pass fiels directly to this app
         */
        File dictionaryFile = new File("src/Wordle_Dictionary.txt");
        File weightedListFile = new File("src/Weighted_Wordle_List.txt");

        String selection;
        String guess;
        boolean playAgain = true;

        /*
         * Initial banner with prompt for user selection.
         */
        System.out.print("\n*** Worlde Solver ***");

        while(playAgain)
        {
            System.out.println();
            printSelectionMenu();

            while(!checkInput(selection = in.nextLine()))
            {
                printSelectionMenu();
            }

            if(selection.equals("1"))
            {
                game = new Game(dictionaryFile, weightedListFile, "Stall");
                while(!game.completed())
                {
                    System.out.println("\nGuess a five letter word: ");
                    guess = in.nextLine();  // insert intrusive action
                    while(!game.playerGuess(guess))
                    {
                        System.out.println("\n" + guess + " is not a five letter word."
                                           + "\nGuess a five letter word: ");
                        guess = in.nextLine();
                    }
                    System.out.println();
                    game.printGame();
                }
            }
            else
            {
                // computer plays!
            }
        
            printPlayAgainMenu();
            while(!checkInput(selection = in.nextLine()))
            {
                System.out.println(selection + " was not a valid choice.");
                printSelectionMenu();
            }
            if(selection.equals("2"))
            {
                playAgain = false;
            }
        }

        in.close();
        System.out.println("\nThanks for playing!\n");
        System.exit(0);
    }

    /*
     * Prints menu for user selection.
     */
    public static void printSelectionMenu()
    {
        System.out.print("\nWould you like to play a game of Wordle"
                            + "\nor let the computer play?"
                            + "\n[1] Play Wordle myself"
                            + "\n[2] Let the computer play"
                            + "\n\nPlease type a selection and press [ENTER]: ");
    }

    public static void printPlayAgainMenu()
    {
        System.out.print("\nWould you like to play again?"
                            + "\n[1] Yes!"
                            + "\n[2] No."
                            + "\n\nPlease make a selection: ");
    }

    public static void printOptions()
    {
        System.out.print( "Here are a list of debug options:"
                        + "\n\"ANSWER\"  Prints the answer"
                        + "\n\"GAME\"    Prints current game results"
                        + "\n\"LETTERS\" Prints current letter data"
                        + "\n\"WEIGHTS\" Prints weight data"
                        + "\n\nPlease type an option and press [ENTER]: ");
    }

    /*
     * Checks input for user selection.
     */
    public static boolean checkInput(String inputIn)
    {
        String input = inputIn.toUpperCase();
        boolean flag = false;
        if(input.equals("1") || input.equals("2"))  // valid input
        {
            flag = true;
        }
        else if(input.equals("DEBUG"))                     // debug input that doesnt print error, but throws false
        {
            printOptions();
            intrusiveAction(in.nextLine());
        }
        else                                                          // prints error and throws false
        {
            System.out.println(inputIn + " is not a valid seleciton.");
        }
        return flag;
    }

    private static void intrusiveAction(String inputIn)
    {
       inputIn = inputIn.toUpperCase();
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
        }
    }
}
