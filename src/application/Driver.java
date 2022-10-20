package application;

import java.io.File;
import java.io.FileNotFoundException;
import classes.Game;
import classes.Letter;

public class Driver 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Game guesser = new Game(new File("src/Wordle_Dictionary.txt")
                                    , new File("src/Weighted_Wordle_List.txt")
                                    , "Stall", "Stalk");

        //guesser.printData();
        //guesser.printDictionary(); 
        guesser.score();
        guesser.printScore();      
    }
}
