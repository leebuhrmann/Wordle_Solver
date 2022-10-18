import java.io.File;
import java.io.FileNotFoundException;
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
        int[] p1 = new int[26];
        int[] p2 = new int[26];
        int[] p3 = new int[26];
        int[] p4 = new int[26];
        int[] p5 = new int[26];
        ArrayList<String> wordList = new ArrayList<String>();

        String answer;

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

                p1[i] = lineParser.nextInt();
                p2[i] = lineParser.nextInt();
                p3[i] = lineParser.nextInt();
                p4[i] = lineParser.nextInt();
                p5[i] = lineParser.nextInt();

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
        answer = wordList.get(rand.nextInt(2315));

        
    }
}
