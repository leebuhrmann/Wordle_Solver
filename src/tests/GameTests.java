package tests;

import java.io.File;
import org.junit.Test;
import classes.Game;

public class GameTests
{
        @Test
        public void GameTest()
        {
                File dictionary = new File("src/Wordle_Dictionary.txt");
                 File weightedListFile = new File("src/Weighted_Wordle_List.txt");

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Stalk", "Stall");
                
                
        }
}
