package classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;

public class Game
{
    //TODO: get rid of magic numbers
    /*
    * File data.
    */
    private File dictionary;
    private File weightedListFile;
    private int[] p1Weights;
    private int[] p2Weights;
    private int[] p3Weights;
    private int[] p4Weights;
    private int[] p5Weights; 
    private ArrayList<String> wordList;
    private ArrayList<String> guessHistory;
    private ArrayList<Character> currentGuess;

    /*
     * Game and Score fields.
     */
    private boolean completed;
    private String answer;
    private char[] answerChArr;
    private ArrayList<Letter> letters;

    /*
     * Creates a game with a random answer.
     */
    public Game(File dictionaryIn, File weightedListFileIn) 
    {
        constructorHelper(dictionaryIn, weightedListFileIn, answer);
        Random rand = new Random();
        answer = wordList.get(rand.nextInt(2315));
        answer = answer.toUpperCase();
        answerChArr = answer.toCharArray();
    }

    /*
     * Creates a game with a specified answer.
     */
    public Game(File dictionaryIn, File weightedListFileIn, String answerIn)
    {
        constructorHelper(dictionaryIn, weightedListFileIn, answerIn);
        answer = answerIn.toUpperCase();
        answerChArr = answer.toCharArray();
    }

    private void constructorHelper(File dictionaryIn, File weightedListFileIn, String answerIn)
    {
        guessHistory = new ArrayList<String>();
        currentGuess = new ArrayList<Character>(5);
        p1Weights = new int[26];
        p2Weights = new int[26];
        p3Weights = new int[26];
        p4Weights = new int[26];
        p5Weights = new int[26];
        wordList = new ArrayList<String>();
        letters = new ArrayList<Letter>();
        completed = false;

        try
        {
            dictionary = dictionaryIn;
            weightedListFile = weightedListFileIn;
            
            collectWeightData();
            fillLetters();
            parseDictionary();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("File was not found!");
            fnfe.printStackTrace();
        }
    }

    /*
     * Has the computer play the game by itslef. Returns the number of
     * guesses it took to find the correct answer.
     */
    // public int botPlay()
    // {
    //     while(guessHistory.get(guessHistory.size() - 1) != answer)
    //     {
    //         score(makeGuess());
    //     }

    //     return guessHistory.size();
    // }

    /*
     * Scores the guess.
     * 
     * If a letter was correct, and in the correct position, marks the correct position.
     * If a letter was correct, but in the incorrect position, marks the incorrect position.
     * If the letter was in the answer, marks the minimum number of occurrences it is aware of.
     * If the letter was not in the word, marks the maximum number of occurrences as 0.
     * 
     * TODO: Expects correct input.
     * Change so it returns a boolean variable.
     * If the guess is not a word or not the correct amount of letters, return false.
     * If the guess is of the correct format, score appropiatly and return true.
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
        
        if(guess.equals(answer)) completed = true;

        guessHistory.add(guess);
    }

    /*
     * 
     */
    public void makeGuess()
    {  
        placeKnownLetters();
        placeUnknownLetters();
    }

    /*
     * Assigns all correct letters with known placements into their known placement in the current guess.
     */
    private void placeKnownLetters()
    {
        for(Letter l : letters)
        {
            ArrayList<Integer> list = l.getCorrect();
            if(!list.isEmpty())
            {
                for(Integer i : list)
                {
                    currentGuess.set(i, l.getLett());
                }
            }
        }
    }

    
    private void placeUnknownLetters()
    {
         // find all letters with unknown locations but are in the word.
         ArrayList<Letter> toPlace = new ArrayList<Letter>();
         for(Letter l : letters)
         {
             if(l.getMinOcc() > 0)
             {
                 for(int i = 0; i < l.getMinOcc() - l.getCorrect().size(); i++ )
                 {
                     toPlace.add(l);
                 }
             }
         }

    }

    private char[] unknownPlacement(char[] guessIn, ArrayList<Letter> toPlaceIn)
    {
        Letter l = toPlaceIn.get(0);
        for(Integer i : l.getSorted())
        {
            if(currentGuess.get(i) == null)
            {
                
            }
        }

        return null;
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
            int[] weightsOut = new int[5];
            weightsOut[0] = p1Weights[i];
            weightsOut[1] = p2Weights[i];
            weightsOut[2] = p3Weights[i];
            weightsOut[3] = p4Weights[i];
            weightsOut[4] = p5Weights[i];

            char cOut = (char)(i + 65);
            letters.add(new Letter(cOut, weightsOut));
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
     * Returns whether the game has been completed or not.
     */
    public boolean completed()
    {
        return completed;
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
     * Prints current game results.
     */
    public void printGame()
    {
        System.out.println();
        for(String s : guessHistory)
        {
            System.out.println(s);
            char[] arr = s.toCharArray();
            for(int i = 0; i < 5; i++)
            {
                char c = Character.toUpperCase(arr[i]);
                Letter l = letters.get(c - 65);
                if(l.getCorrect().contains(i))
                {
                    System.out.print("Y");
                }
                else if(l.getMinOcc() > 0)
                {
                    System.out.print("I");
                }
                else
                {
                    System.out.print("X");
                }
            }
            System.out.println();
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
