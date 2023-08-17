package tests;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import classes.*;

public class GameTests
{

        /*
         * The Bot Game creates a guess and then searches through Wordle_Dictionary to see if the
         * created guess was a real word.
         * 
         * The average time for a brute force method with a O(n) is a little more than 700,000,000ns
         */
        public void BotTimePerformance()
        {
                String dictionaryFile = "/Wordle_Dictionary.txt";
                String weightedListFile = "/Weighted_Wordle_List.txt";

                Game game1 = new Game(dictionaryFile, weightedListFile, "Stall", 50);
                Game game2 = new Game(dictionaryFile, weightedListFile, "Ghast", 50);
                Game game3 = new Game(dictionaryFile, weightedListFile, "Feast", 50);
                Game game4 = new Game(dictionaryFile, weightedListFile, "Index", 50);
                Game game5 = new Game(dictionaryFile, weightedListFile, "Plant", 50);
                Game game6 = new Game(dictionaryFile, weightedListFile, "Extra", 50);
                Game game7 = new Game(dictionaryFile, weightedListFile, "Brake", 50);
                Game game8 = new Game(dictionaryFile, weightedListFile, "Rigor", 50);
                Game game9 = new Game(dictionaryFile, weightedListFile, "Water", 50);
                Game game10 = new Game(dictionaryFile, weightedListFile, "Quilt", 50);
                Game game11 = new Game(dictionaryFile, weightedListFile, "Track", 50);
                Game game12 = new Game(dictionaryFile, weightedListFile, "Liked", 50);
                Game game13 = new Game(dictionaryFile, weightedListFile, "Ether", 50);
                Game game14 = new Game(dictionaryFile, weightedListFile, "Usher", 50);
                Game game15 = new Game(dictionaryFile, weightedListFile, "Frank", 50);
                Game game16 = new Game(dictionaryFile, weightedListFile, "Knife", 50);
                Game game17 = new Game(dictionaryFile, weightedListFile, "Otter", 50);
                Game game18 = new Game(dictionaryFile, weightedListFile, "Arbor", 50);
                Game game19 = new Game(dictionaryFile, weightedListFile, "Goose", 50);
                Game game20 = new Game(dictionaryFile, weightedListFile, "Words", 50);

                long start = System.nanoTime();                
                game1.botPlays("Stalk");           
                game2.botPlays("Still");   
                game3.botPlays("Vivid");
                game4.botPlays("Comma");
                game5.botPlays("Trade");
                game6.botPlays("Blank");
                game7.botPlays("Later");
                game8.botPlays("Named");
                game9.botPlays("House");
                game10.botPlays("Young");
                game11.botPlays("Crash");
                game12.botPlays("Blast");
                game13.botPlays("Haste");
                game14.botPlays("Light");
                game15.botPlays("Mommy");
                game16.botPlays("Rivet");
                game17.botPlays("Jolly");
                game18.botPlays("Shout");
                game19.botPlays("Apple");
                game20.botPlays("Walks");
                long finish = System.nanoTime();

                long timeElapsed = finish - start;
                System.out.printf("Time Elapsed for this Bot Game: %,dns", timeElapsed);
                System.out.printf("\nTime Elapsed for this Bot Game: %2.6fms", ((double)timeElapsed / 1000000));
        }

        // @Test
        // public void StalkStall() throws Exception
        // {
        //         System.out.println("***" + System.getProperty("user.dir"));
                
        //         String dictionary = "/Wordle_Dictionary.txt";
        //         String weightedListFile = "/Weighted_Wordle_List.txt";
        //         ArrayList<Integer> correct;
        //         ArrayList<Integer> notAttempted;
        //         Letter letter;

        //         Word guess = new Word("Stalk");
        //         Game g1 = new Game(dictionary, weightedListFile, "Stall", 50);
        //         Method privateScore = g1.class.getDeclaredMethod("score", null);
        //         g1.score(guess);

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
        //         String dictionary = "src/Wordle_Dictionary.txt";
        //         String weightedListFile = "src/Weighted_Wordle_List.txt";
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
