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
                ArrayList<Integer> correct;
                ArrayList<Integer> incorrect;
                Letter letter;

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Stalk", "Stall");
                g1.score();

                /*
                 * Testing Letter object 'S'
                 */
                letter = g1.getLetter('S');
                assertEquals('S', letter.getLett());
                assertEquals(1, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(true, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));

                /*
                 * Testing Letter object 'T'
                 */
                letter = g1.getLetter('T');
                assertEquals('T', letter.getLett());
                assertEquals(1, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(true, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));
                
                /*
                 * Testing Letter object 'A'
                 */
                letter = g1.getLetter('A');
                assertEquals('A', letter.getLett());
                assertEquals(1, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(true, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));
                
                /*
                 * Testing Letter object 'L'
                 */
                letter = g1.getLetter('L');
                assertEquals('L', letter.getLett());
                assertEquals(1, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(true, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));

                /*
                 * Testing Letter object 'K'
                 */
                letter = g1.getLetter('K');
                assertEquals('K', letter.getLett());
                assertEquals(0, letter.getMinOcc());
                assertEquals(0, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));
        }

        @Test
        public void VinveVoviv()
        {
                File dictionary = new File("src/Wordle_Dictionary.txt");
                File weightedListFile = new File("src/Weighted_Wordle_List.txt");
                ArrayList<Integer> correct;
                ArrayList<Integer> incorrect;
                Letter letter;

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Vinve", "Voviv");
                g1.score();

                letter = g1.getLetter('V');
                assertEquals('V', letter.getLett());
                assertEquals(2, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(true, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(true, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));  
                
                letter = g1.getLetter('I');
                assertEquals('I', letter.getLett());
                assertEquals(1, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(true, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));

                letter = g1.getLetter('N');
                assertEquals('N', letter.getLett());
                assertEquals(0, letter.getMinOcc());
                assertEquals(0, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));

                letter = g1.getLetter('E');
                assertEquals('E', letter.getLett());
                assertEquals(0, letter.getMinOcc());
                assertEquals(0, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));
        }

        @Test
        public void OovvvVvvoo()
        {
                File dictionary = new File("src/Wordle_Dictionary.txt");
                File weightedListFile = new File("src/Weighted_Wordle_List.txt");
                ArrayList<Integer> correct;
                ArrayList<Integer> incorrect;
                Letter letter;

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Oovvv", "Vvvoo");
                g1.score();

                letter = g1.getLetter('V');
                assertEquals('V', letter.getLett());
                assertEquals(3, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(true, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(true, incorrect.contains(3));
                assertEquals(true, incorrect.contains(4));
        
                letter = g1.getLetter('O');
                assertEquals('O', letter.getLett());
                assertEquals(2, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(true, incorrect.contains(0));
                assertEquals(true, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4)); 
        }

        @Test
        public void OvovoVovov()
        {
                File dictionary = new File("src/Wordle_Dictionary.txt");
                File weightedListFile = new File("src/Weighted_Wordle_List.txt");
                ArrayList<Integer> correct;
                ArrayList<Integer> incorrect;
                Letter letter;

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Ovovo", "Vovov");
                g1.score();

                letter = g1.getLetter('V');
                assertEquals('V', letter.getLett());
                assertEquals(2, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(true, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(true, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));
        
                letter = g1.getLetter('O');
                assertEquals('O', letter.getLett());
                assertEquals(2, letter.getMinOcc());
                assertEquals(2, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(true, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(true, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(true, incorrect.contains(4)); 
        }

        @Test
        public void AbcbdDbcbe()
        {
                File dictionary = new File("src/Wordle_Dictionary.txt");
                File weightedListFile = new File("src/Weighted_Wordle_List.txt");
                ArrayList<Integer> correct;
                ArrayList<Integer> incorrect;
                Letter letter;

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Abcbd", "Dbcbe");
                g1.score();

                letter = g1.getLetter('A');
                assertEquals('A', letter.getLett());
                assertEquals(0, letter.getMinOcc());
                assertEquals(0, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));
                
                letter = g1.getLetter('B');
                assertEquals('B', letter.getLett());
                assertEquals(2, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(true, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(true, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));

                letter = g1.getLetter('C');
                assertEquals('C', letter.getLett());
                assertEquals(1, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(true, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(false, incorrect.contains(4));

                letter = g1.getLetter('D');
                assertEquals('D', letter.getLett());
                assertEquals(1, letter.getMinOcc());
                assertEquals(5, letter.getMaxOcc());
                correct = letter.getCorrect();
                incorrect = letter.getIncorrect();

                assertEquals(false, correct.contains(0));
                assertEquals(false, correct.contains(1));
                assertEquals(false, correct.contains(2));
                assertEquals(false, correct.contains(3));
                assertEquals(false, correct.contains(4));

                assertEquals(false, incorrect.contains(0));
                assertEquals(false, incorrect.contains(1));
                assertEquals(false, incorrect.contains(2));
                assertEquals(false, incorrect.contains(3));
                assertEquals(true, incorrect.contains(4));
        }
}
