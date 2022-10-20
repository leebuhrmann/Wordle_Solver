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

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Stalk", "Stall");
                g1.score();

                /*
                 * Testing Letter object 'S'
                 */
                Letter s = g1.getLetter('S');
                assertEquals('S', s.getLett());
                assertEquals(1, s.getMinOcc());
                assertEquals(5, s.getMaxOcc());
                correct = s.getCorrect();
                incorrect = s.getIncorrect();

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
                Letter t = g1.getLetter('T');
                assertEquals('T', t.getLett());
                assertEquals(1, t.getMinOcc());
                assertEquals(5, t.getMaxOcc());
                correct = t.getCorrect();
                incorrect = t.getIncorrect();

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
                Letter a = g1.getLetter('A');
                assertEquals('A', a.getLett());
                assertEquals(1, a.getMinOcc());
                assertEquals(5, a.getMaxOcc());
                correct = a.getCorrect();
                incorrect = a.getIncorrect();

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
                Letter l = g1.getLetter('L');
                assertEquals('L', l.getLett());
                assertEquals(1, l.getMinOcc());
                assertEquals(5, l.getMaxOcc());
                correct = l.getCorrect();
                incorrect = l.getIncorrect();

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
                Letter k = g1.getLetter('K');
                assertEquals('K', k.getLett());
                assertEquals(0, k.getMinOcc());
                assertEquals(0, k.getMaxOcc());
                correct = k.getCorrect();
                incorrect = k.getIncorrect();

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

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Vinve", "Voviv");
                g1.score();

                Letter v = g1.getLetter('V');
                assertEquals('V', v.getLett());
                assertEquals(2, v.getMinOcc());
                assertEquals(5, v.getMaxOcc());
                correct = v.getCorrect();
                incorrect = v.getIncorrect();

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
                
                Letter i = g1.getLetter('I');
                assertEquals('I', i.getLett());
                assertEquals(1, i.getMinOcc());
                assertEquals(5, i.getMaxOcc());
                correct = i.getCorrect();
                incorrect = i.getIncorrect();

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

                Letter n = g1.getLetter('N');
                assertEquals('N', n.getLett());
                assertEquals(0, n.getMinOcc());
                assertEquals(0, n.getMaxOcc());
                correct = n.getCorrect();
                incorrect = n.getIncorrect();

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

                Letter e = g1.getLetter('E');
                assertEquals('E', e.getLett());
                assertEquals(0, e.getMinOcc());
                assertEquals(0, e.getMaxOcc());
                correct = e.getCorrect();
                incorrect = e.getIncorrect();

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

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Oovvv", "Vvvoo");
                g1.score();

                Letter v = g1.getLetter('V');
                assertEquals('V', v.getLett());
                assertEquals(3, v.getMinOcc());
                assertEquals(5, v.getMaxOcc());
                correct = v.getCorrect();
                incorrect = v.getIncorrect();

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
        
                Letter o = g1.getLetter('O');
                assertEquals('O', o.getLett());
                assertEquals(2, o.getMinOcc());
                assertEquals(5, o.getMaxOcc());
                correct = o.getCorrect();
                incorrect = o.getIncorrect();

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

                Game g1 = new Game(dictionary, weightedListFile
                                        , "Ovovo", "Vovov");
                g1.score();

                Letter v = g1.getLetter('V');
                assertEquals('V', v.getLett());
                assertEquals(2, v.getMinOcc());
                assertEquals(5, v.getMaxOcc());
                correct = v.getCorrect();
                incorrect = v.getIncorrect();

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
        
                Letter o = g1.getLetter('O');
                assertEquals('O', o.getLett());
                assertEquals(2, o.getMinOcc());
                assertEquals(5, o.getMaxOcc());
                correct = o.getCorrect();
                incorrect = o.getIncorrect();

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
}
