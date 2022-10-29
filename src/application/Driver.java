package application;

import java.io.File;
import java.util.Scanner;

import classes.Game;

public class Driver 
{
    static Game game;
    public static void main(String[] args)
    {
        /*
         * Later I may make it possible for the Wordle_Letter_Weigher app to pass fiels directly to this app
         */
        File dictionaryFile = new File("src/Wordle_Dictionary.txt");
        File weightedListFile = new File("src/Weighted_Wordle_List.txt");

        Scanner in = new Scanner(System.in);
        String selection;
        String guess;
        boolean playAgain = true;

        /*
         * Initial banner with prompt for user selection.
         */
        System.out.print("\n*** Worlde Solver ***"
                        + "\n\nAt anypoint you may enter the following:");
        printOptions();


        while(playAgain)
        {
            System.out.println();
            printSelectionMenu();
            
            while(!checkInput(selection = in.next()))
            {
                System.out.println(selection + " was not a valid choice.");
                printSelectionMenu();
            }

            if(selection.equals("1"))
            {
                game = new Game(dictionaryFile, weightedListFile, "Stall");
                while(!game.completed())
                {
                    System.out.println("\nGuess a five letter word: ");
                    guess = in.next();  // insert intrusive action
                    while(!game.playerGuess(guess))
                    {
                        System.out.println("\n" + guess + " is not a five letter word."
                                           + "\nGuess a five letter word: ");
                        guess = in.next();
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
            while(!checkInput(selection = in.next()))
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
                            + "\n\nPlease make a selection: ");
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
        System.out.print( "\n\"HELP\"    Prints this list"
                        + "\n\"ANSWER\"  Prints the answer"
                        + "\n\"GAME\"    Prints current game results"
                        + "\n\"LETTERS\" Prints current letter data"
                        + "\n\"WEIGHTS\" Prints weight data");
    }

    /*
     * Checks input for user selection.
     */
    public static boolean checkInput(String inputIn)
    {
        boolean flag = false;
        if(inputIn.equals("1") || inputIn.equals("2"))
        {
            flag = true;
        }
        else
        {
            flag = intrusiveAction(inputIn);
        }
        return flag;
    }

    private static boolean intrusiveAction(String inputIn)
    {
        boolean retFlag = true;
        String input = inputIn.toUpperCase();
        switch (input)
        {
            case "HELP":
                printOptions();
                break;
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
                retFlag = false;
        }

        return retFlag;
    }
}
