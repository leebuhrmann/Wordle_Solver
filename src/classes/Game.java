package classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    /*
    * File data.
    */
    private File dictionary;
    private File weightedListFile;
    private int[] p1Weights = new int[26];
    private int[] p2Weights = new int[26];
    private int[] p3Weights = new int[26];
    private int[] p4Weights = new int[26];
    private int[] p5Weights = new int[26];
    private ArrayList<String> wordList = new ArrayList<String>();
    private ArrayList<String> guessHistory;

    /*
     * Game and Score fields.
     */
    private String answer;
    private char[] answerChArr;
    private ArrayList<Letter> letters = new ArrayList<Letter>();

    /*
     * Creates a 
     */
    public Game(File dictionaryIn, File weightedListFileIn) 
    {
        constructorHelper(dictionaryIn, weightedListFileIn);

        Random rand = new Random();
        answer = wordList.get(rand.nextInt(2315));
        answer = answer.toUpperCase();
        answerChArr = answer.toCharArray();
    }

    public Game(File dictionaryIn, File weightedListFileIn, String answerIn)
    {
        constructorHelper(dictionaryIn, weightedListFileIn);
        
        answer = answerIn.toUpperCase();
        answerChArr = answer.toCharArray();
    }

    private void constructorHelper(File dictionaryIn, File weightedListFileIn)
    {
        try
        {
            dictionary = dictionaryIn;
            weightedListFile = weightedListFileIn;
            guessHistory = new ArrayList<String>();
    
            fillLetters();
            collectWeightData();
            parseDictionary();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("File was not found!");
        }
    }

    /*
     * Has the computer play the game by itslef. Returns the number of
     * guesses it took to find the correct answer.
     */
    public int botPlay()
    {
        while(guessHistory.get(guessHistory.size() - 1) != answer)
        {
            score(makeGuess());
        }

        return guessHistory.size();
    }

    /*
     * Scores the guess.
     * 
     * If a letter was correct, and in the correct position, marks the correct position.
     * If a letter was correct, but in the incorrect position, marks the incorrect position.
     * If the letter was in the answer, marks the minimum number of occurrences it is aware of.
     * If the letter was not in the word, marks the maximum number of occurrences as 0.
     */
    public void score(String guessIn)
    {
        String guess = guessIn.toUpperCase();
        char[] guessChArr = guess.toCharArray();

        int occurrences = 0;
        boolean occured = false;
        boolean inPlace = false;

        for(int i = 0; i < 5; i++)      //guessChArr
        {  
            Letter temp = letters.get(guessChArr[i] - 65);

            for(int j = 0; j < 5; j++)  //answerChArr
            {
                if(guessChArr[i] == answerChArr[j])
                {
                    if(i == j)
                    {
                        inPlace = true;
                    }
                    
                    occured = true;
                    occurrences++;
                }
            }

            temp.attempted(i);

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

                if(inPlace)
                {
                    temp.addCorrect(i);

                }
                
            } 
            else
            {
                temp.setMaxOcc(0);
            }

            occurrences = 0;
            occured = false;
            inPlace = false;
        }
        
        guessHistory.add(guess);
    }


    public String makeGuess()
    {
        char[] guess = new char[5];
        ArrayList<Integer> list;

        // Places all correct letters with known locations.
        for(Letter l : letters)
        {
            list = l.getCorrect();
            if(!list.isEmpty())
            {
                for(Integer i : list)
                {
                    guess[i] = l.getLett();
                }
            }
        }

        // Places all correct letters without known locations.
        for(Letter l : letters)
        {
            list = l.getNotAttempted();
            if(!list.isEmpty())
            {
                for(Integer i : list)
                {
                    
                }
            }
        }
    }

    /*
     * Retrieves a specified Letter.
     */
    public Letter getLetter(char lettIn)
    {
        lettIn = Character.toUpperCase(lettIn);
        return letters.get(lettIn - 65);
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
        System.out.println("\nGuess: " + guessHistory.get(guessHistory.size() - 1) + "\nAnswer: " + answer);

        System.out.println("\n|let|min|max| correct placements | not attempted placements | ");
        for(Letter l : letters)
        {
            System.out.printf("| %c | %d | %d | ", l.getLett(), l.getMinOcc(), l.getMaxOcc());
            for(Integer p : l.getCorrect())
            {
                System.out.print(p + " ");
            }
            System.out.print("| ");
            for(Integer p : l.getNotAttempted())
            {
                System.out.print(p + " ");
            }
            System.out.println("|");
        }

        System.out.println("\n");
    }
}