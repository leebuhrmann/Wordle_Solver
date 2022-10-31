package tests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.io.File;

import classes.Game;

//TODO: Check https://stackoverflow.com/questions/74201115/filenotfoundexception-during-junit-tests-but-not-when-running-the-actual-app
public class GameTests
{

        @Test
        public void StalkStall()
        {
                File dictionaryFile = new File("src/Wordle_Dictionary");
                File weightedListFile = new File("src/Weighted_Wordle_List");

                Game game = new Game(dictionaryFile, weightedListFile, "Stall");
                assertTrue(game.gameStep("Stalk"));
        }

        // @Test
        // public void StalkStall()
        // {
        //         System.out.println("***" + System.getProperty("user.dir"));
                
        //         File dictionary = new File("ProjectRoot/src/Wordle_Dictionary.txt");
        //         File weightedListFile = new File("ProjectRoot/src/Weighted_Wordle_List.txt");
        //         ArrayList<Integer> correct;
        //         ArrayList<Integer> notAttempted;
        //         Letter letter;

        //         Game g1 = new Game(dictionary, weightedListFile, "Stall");
        //         g1.score("Stalk");

        //         /*
        //          * Testing Letter object 'S'
        //          */
        //         letter = g1.getLetter('S');
        //         assertEquals('S', letter.getLett());
        //         assertEquals(1, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(true, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(false, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4));

        //         /*
        //          * Testing Letter object 'T'
        //          */
        //         letter = g1.getLetter('T');
        //         assertEquals('T', letter.getLett());
        //         assertEquals(1, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(true, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(false, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4));
                
        //         /*
        //          * Testing Letter object 'A'
        //          */
        //         letter = g1.getLetter('A');
        //         assertEquals('A', letter.getLett());
        //         assertEquals(1, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(true, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(false, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4));
                
        //         /*
        //          * Testing Letter object 'L'
        //          */
        //         letter = g1.getLetter('L');
        //         assertEquals('L', letter.getLett());
        //         assertEquals(1, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(true, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(false, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4));

        //         /*
        //          * Testing Letter object 'K'
        //          */
        //         letter = g1.getLetter('K');
        //         assertEquals('K', letter.getLett());
        //         assertEquals(0, letter.getMinOcc());
        //         assertEquals(0, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(false, notAttempted.contains(4));
        // }

        // @Test
        // public void VinveVoviv()
        // {
        //         File dictionary = new File("src/Wordle_Dictionary.txt");
        //         File weightedListFile = new File("src/Weighted_Wordle_List.txt");
        //         ArrayList<Integer> correct;
        //         ArrayList<Integer> notAttempted;
        //         Letter letter;

        //         Game g1 = new Game(dictionary, weightedListFile, "Voviv");
        //         g1.score("Vinve");

        //         letter = g1.getLetter('V');
        //         assertEquals('V', letter.getLett());
        //         assertEquals(2, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(true, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(false, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(false, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4));  
                
        //         letter = g1.getLetter('I');
        //         assertEquals('I', letter.getLett());
        //         assertEquals(1, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(false, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4));

        //         letter = g1.getLetter('N');
        //         assertEquals('N', letter.getLett());
        //         assertEquals(0, letter.getMinOcc());
        //         assertEquals(0, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(false, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4));

        //         letter = g1.getLetter('E');
        //         assertEquals('E', letter.getLett());
        //         assertEquals(0, letter.getMinOcc());
        //         assertEquals(0, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(false, notAttempted.contains(4));
        // }

        // @Test
        // public void OovvvVvvoo()
        // {
        //         File dictionary = new File("src/Wordle_Dictionary.txt");
        //         File weightedListFile = new File("src/Weighted_Wordle_List.txt");
        //         ArrayList<Integer> correct;
        //         ArrayList<Integer> notAttempted;
        //         Letter letter;

        //         Game g1 = new Game(dictionary, weightedListFile, "Vvvoo");
        //         g1.score("Oovvv");

        //         letter = g1.getLetter('V');
        //         assertEquals('V', letter.getLett());
        //         assertEquals(3, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(true, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(false, notAttempted.contains(2));
        //         assertEquals(false, notAttempted.contains(3));
        //         assertEquals(false, notAttempted.contains(4));
        
        //         letter = g1.getLetter('O');
        //         assertEquals('O', letter.getLett());
        //         assertEquals(2, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(false, notAttempted.contains(0));
        //         assertEquals(false, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4)); 
        // }

        // @Test
        // public void OvovoVovov()
        // {
        //         File dictionary = new File("src/Wordle_Dictionary.txt");
        //         File weightedListFile = new File("src/Weighted_Wordle_List.txt");
        //         ArrayList<Integer> correct;
        //         ArrayList<Integer> notAttempted;
        //         Letter letter;

        //         Game g1 = new Game(dictionary, weightedListFile, "Vovov");
        //         g1.score("Ovovo");

        //         letter = g1.getLetter('V');
        //         assertEquals('V', letter.getLett());
        //         assertEquals(2, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(false, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(false, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4));
        
        //         letter = g1.getLetter('O');
        //         assertEquals('O', letter.getLett());
        //         assertEquals(2, letter.getMinOcc());
        //         assertEquals(2, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(false, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(false, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(false, notAttempted.contains(4)); 
        // }

        // @Test
        // public void AbcbdDbcbe()
        // {
        //         File dictionary = new File("src/Wordle_Dictionary.txt");
        //         File weightedListFile = new File("src/Weighted_Wordle_List.txt");
        //         ArrayList<Integer> correct;
        //         ArrayList<Integer> notAttempted;
        //         Letter letter;

        //         Game g1 = new Game(dictionary, weightedListFile, "Dbcbe");
        //         g1.score("Abcbd");

        //         letter = g1.getLetter('A');
        //         assertEquals('A', letter.getLett());
        //         assertEquals(0, letter.getMinOcc());
        //         assertEquals(0, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(false, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4));
                
        //         letter = g1.getLetter('B');
        //         assertEquals('B', letter.getLett());
        //         assertEquals(2, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(true, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(true, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(false, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(false, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4));

        //         letter = g1.getLetter('C');
        //         assertEquals('C', letter.getLett());
        //         assertEquals(1, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(true, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(false, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(true, notAttempted.contains(4));

        //         letter = g1.getLetter('D');
        //         assertEquals('D', letter.getLett());
        //         assertEquals(1, letter.getMinOcc());
        //         assertEquals(5, letter.getMaxOcc());
        //         correct = letter.getCorrect();
        //         notAttempted = letter.getNotAttempted();

        //         assertEquals(false, correct.contains(0));
        //         assertEquals(false, correct.contains(1));
        //         assertEquals(false, correct.contains(2));
        //         assertEquals(false, correct.contains(3));
        //         assertEquals(false, correct.contains(4));

        //         assertEquals(true, notAttempted.contains(0));
        //         assertEquals(true, notAttempted.contains(1));
        //         assertEquals(true, notAttempted.contains(2));
        //         assertEquals(true, notAttempted.contains(3));
        //         assertEquals(false, notAttempted.contains(4));
        // }
}
