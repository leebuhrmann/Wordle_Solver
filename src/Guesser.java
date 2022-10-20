import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Guesser
{
    /*
    * data
    */
    private File dictionary;
    private File weightedListFile;
    private int[] p1Weights = new int[26];
    private int[] p2Weights = new int[26];
    private int[] p3Weights = new int[26];
    private int[] p4Weights = new int[26];
    private int[] p5Weights = new int[26];
    private ArrayList<String> wordList = new ArrayList<String>();

    private int occurrences;
    private boolean occured;
    private String answer;
    private char[] answerChArr;
    private String guess;
    private char[] guessChArr;
    private Random rand;

    private ArrayList<Letter> letters = new ArrayList<Letter>();

    public Guesser(File dictionaryIn, File weightedListFileIn) throws FileNotFoundException
    {
        dictionary = dictionaryIn;
        weightedListFile = weightedListFileIn;
        rand = new Random();
        occurrences = 0;
        occured = false;

        fillLetters();
        collectWeightData();
        parseDictionary();
        
        answer = wordList.get(rand.nextInt(2315));
        answer = answer.toUpperCase();
        answerChArr = answer.toCharArray();
        guess = wordList.get(rand.nextInt(12971));
        guess = guess.toUpperCase();
        guessChArr = guess.toCharArray();
    }

    public Guesser(File dictionaryIn, File weightedListFileIn, String guessIn, String answerIn) throws FileNotFoundException
    {
        dictionary = dictionaryIn;
        weightedListFile = weightedListFileIn;
        rand = new Random();
        occurrences = 0;
        occured = false;

        fillLetters();
        collectWeightData();
        parseDictionary();
        
        answer = answerIn.toUpperCase();
        answerChArr = answer.toCharArray();
        guess = guessIn.toUpperCase();
        guessChArr = guess.toCharArray();
    }


    //TODO: doesn't work
    /*
     * Scores the guess.
     * 
     * If a letter was correct, and in the correct position, marks the correct position.
     * If a letter was correct, but in the incorrect position, marks the incorrect position.
     * If the letter was in the answer, marks the minimum number of occurrences it is aware of.
     * If the letter was not in the word, marks the maximum number of occurrences as 0.
     */
    public void score()
    {
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

            occured = false;
        }
        
    }

    /*
    * Fills list with Instantiated Letters.
    */
    private void fillLetters()
    {
        for(int i = 0; i < 26; i++)
        {
            char cOut = (char)(i + 65);
            letters.add(new Letter(cOut));
        }
    }

    /*
    * Collect data from Weighted_Wordle_List.txt
    */
    private void collectWeightData() throws FileNotFoundException
    {
        int i = 0;
        Scanner in = new Scanner(weightedListFile);
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

    /*
    * Collect every word that can be accepted by Wordle.
    */
    private void parseDictionary() throws FileNotFoundException
    {
        Scanner in = new Scanner(dictionary);
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

    /*
    * output to check data collection was successful.
    */
    public void printData()
    {
        System.out.print("\nPOS|  1  |  2  |  3  |  4  |  5  |\n");
        for(int i = 0; i < 26; i++)
        {
            System.out.printf(" %c | %3d | %3d | %3d | %3d | %3d |\n"
                                , (i + 65), p1Weights[i], p2Weights[i]
                                , p3Weights[i], p4Weights[i]
                                , p5Weights[i]);
        }
    }

    /*
    * Output to check if word collection was successful.
    */
    public void printDictionary()
    {
        int num = 0;
        for(String word : wordList)
        {
            System.out.println(num + " | " + word);
            num++;
        }
    }

    /*
     * Prints the current score of the game.
     */
    public void printScore()
    {
        System.out.println("\nGuess: " + guess + "\nAnswer: " + answer);

        System.out.println("\n|let|min|max| cp... | icp... | ");
        for(Letter l : letters)
        {
            System.out.printf("| %c | %d | %d | ", l.getLett(), l.getMinOcc(), l.getMaxOcc());
            ArrayList<Integer> temp = l.getCorrect();
            for(Integer p : temp)
            {
                System.out.print(p + " ");
            }
            System.out.print("| ");
            temp = l.getIncorrect();
            for(Integer p : temp)
            {
                System.out.print(p + " ");
            }
            System.out.println("|");
        }

        System.out.println("\n");
    }
}