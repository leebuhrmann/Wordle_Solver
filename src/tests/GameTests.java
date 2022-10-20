package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;
import classes.Game;
import classes.Letter;

public class GameTests
{
        @Test
        public void StalkStall()
        {
                File dictionary = new File("src/Wordle_Dictionary.txt");
                File weightedListFile = new File("src/Weighted_Wordle_List.txt");

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Stalk", "Stall");
                g1.score();
                Letter s = g1.getLetter('S');

                assertEquals('S', s.getLett());
                assertEquals(1, s.getMinOcc());
                assertEquals(5, s.getMaxOcc());

                assertEquals(true, s.getCorrect().contains(0));
                assertEquals(false, s.getCorrect().contains(1));
                assertEquals(false, s.getCorrect().contains(2));
                assertEquals(false, s.getCorrect().contains(3));
                assertEquals(false, s.getCorrect().contains(4));

                assertEquals(false, s.getIncorrect().contains(0));
                assertEquals(false, s.getIncorrect().contains(1));
                assertEquals(false, s.getIncorrect().contains(2));
                assertEquals(false, s.getIncorrect().contains(3));
                assertEquals(false, s.getIncorrect().contains(4));    
        }

        @Test
        public void VinveVoviv()
        {
                File dictionary = new File("src/Wordle_Dictionary.txt");
                File weightedListFile = new File("src/Weighted_Wordle_List.txt");

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Vinve", "Voviv");
                g1.score();

                Letter v = g1.getLetter('V');
                assertEquals('V', v.getLett());
                assertEquals(2, v.getMinOcc());
                assertEquals(5, v.getMaxOcc());

                assertEquals(true, v.getCorrect().contains(0));
                assertEquals(false, v.getCorrect().contains(1));
                assertEquals(false, v.getCorrect().contains(2));
                assertEquals(false, v.getCorrect().contains(3));
                assertEquals(false, v.getCorrect().contains(4));

                assertEquals(false, v.getIncorrect().contains(0));
                assertEquals(false, v.getIncorrect().contains(1));
                assertEquals(false, v.getIncorrect().contains(2));
                assertEquals(true, v.getIncorrect().contains(3));
                assertEquals(false, v.getIncorrect().contains(4));    
        }
}
