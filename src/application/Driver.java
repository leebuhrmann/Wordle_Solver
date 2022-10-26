package application;

import java.io.File;
import classes.Game;

public class Driver 
{
    public static void main(String[] args)
    {
        Game guesser = new Game(new File("src/Wordle_Dictionary.txt")
                                    , new File("src/Weighted_Wordle_List.txt")
                                    , "Stall");

        //guesser.printData();
        //guesser.printDictionary(); 
        guesser.score("Llast");
        guesser.printScore(); 
       // guesser.makeGuess();
    }
}
