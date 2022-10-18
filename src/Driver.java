import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Driver 
{
    public static void main(String[] args)
    {
        /*
         * data
         */
        int[] p1Weights = new int[26];
        int[] p2Weights = new int[26];
        int[] p3Weights = new int[26];
        int[] p4Weights = new int[26];
        int[] p5Weights = new int[26];
        ArrayList<String> wordList = new ArrayList<String>();

        int occurrences = 0;
        boolean occured = false;
        String answer;
        String firstGuess;

        ArrayList<Letter> letters = new ArrayList<Letter>();

        for(int i = 0; i < 26; i++)
        {
            char cOut = (char)(i + 65);
            letters.add(new Letter(cOut));
        }

        /*
         * Collect data from Weighted_Wordle_List.txt
         */
        try
        {
            int i = 0;
            Scanner in = new Scanner(new File("src/Weighted_Wordle_List.txt"));
            while(in.hasNextLine() && i < 26)
            {
                String record = in.nextLine();
                Scanner lineParser = new Scanner(record);
                lineParser.useDelimiter(",");

                p1Weights[i] = lineParser.nextInt();
                p2Weights[i] = lineParser.nextInt();
                p3Weights[i] = lineParser.nextInt();
                p4Weights[i] = lineParser.nextInt();
                p5Weights[i] = lineParser.nextInt();

                lineParser.close();
                i++;
            }

            in.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("File not found!");
        }

        /*
         * output to check data collection was successful.
         */
        // System.out.print("\nPOS|  1  |  2  |  3  |  4  |  5  |\n");
        // for(int i = 0; i < 26; i++)
        // {
        //     System.out.printf(" %c | %3d | %3d | %3d | %3d | %3d |\n"
        //                         , (i + 65), p1[i], p2[i], p3[i], p4[i], p5[i]);
        // }

        /*
         * Collect every word that can be accepted by Wordle.
         * Uses Wordle_Answers.txt
         */
        try
        {
            Scanner in = new Scanner(new File("src/Wordle_Answers.txt"));
            in.nextLine(); //skip first line
            while(in.hasNextLine())
            {
                String record = in.nextLine();
                Scanner lineParser = new Scanner(record);
                
                lineParser.next();                  //skip date
                lineParser.nextInt();               //skip word number
                String word = lineParser.next();    //grab word
                word = word.toUpperCase();
                wordList.add(word);

                lineParser.close();
            }

            in.close();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("File not found!");
        }

        /*
         * Output to check if word collection was successful.
         */
        // int num = 0;
        // for(String word : wordList)
        // {
        //     System.out.println(num + " | " + word);
        //     num++;
        // }

        /*
         * Choose a random word as the answer for the Wordle game.
         */
        Random rand = new Random();
        // answer = wordList.get(rand.nextInt(2315));
        // answer = answer.toUpperCase();
        // char[] answerChArr = answer.toCharArray();
        // firstGuess = wordList.get(rand.nextInt(12971));
        // firstGuess = firstGuess.toUpperCase();
        // char[] guessChArr = firstGuess.toCharArray();

        answer = "STALK";
        firstGuess = "STALL";
        char[] answerChArr = answer.toCharArray();
        char[] guessChArr = firstGuess.toCharArray();
        //while(!Arrays.equals(answerChArr, guessChArr))
        //{
            for(int i = 0; i < 5; i++)      //guessChArr
            {  
                Letter temp = letters.get(guessChArr[i] - 65);

                for(int j = 0; j < 5; j++)  //answerChArr
                {
                    if(guessChArr[i] == answerChArr[j])
                    {
                        if(i == j)
                        {
                            temp.addCorrect(i);
                        }
                        else
                        {
                            temp.addIncorrect(i);
                        }
                        occured = true;
                        occurrences++;
                    }
                }

                if(occured)
                {
                    if(occurrences > letters.get(guessChArr[i] - 65).getMinOcc())
                    {
                        temp.incrementMinOcc();
                    }
                    else
                    {
                        temp.setMaxOcc(temp.getMinOcc());
                    }
                } 
                else
                {
                    temp.setMaxOcc(0);
                }
            }
        //}
        System.out.println("|let|min|max| cp... | icp... | ");
        for(Letter l : letters)
        {
            System.out.printf("| %c | %d |");
        }

        System.out.println("Correct! The answer is " + answer);
        
    }
}
