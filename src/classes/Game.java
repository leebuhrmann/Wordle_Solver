package classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    /*
     * TODO: get rid of magic numbers.
     * In the future I may want to pass different dictionaries to play games with words of differnt length or different languages.
     * Portions of the code(for loops) use the size of the word as the condition (need to make this not a magic number).
     * Also, if we have a different alphabet we will need to change the size of the positional weight fields (p1Weights, ...).
     * Will likely require the first line of the dictionary to contain word size and alphabet information.  
     */
    /*
     * Files data.
     */
    private File dictionary;
    private File weightedListFile;
    private ArrayList<String> wordList;
    private int[] p1Weights;
    private int[] p2Weights;
    private int[] p3Weights;
    private int[] p4Weights;
    private int[] p5Weights; 

    /*
     * Game and Score fields.
     */
    private ArrayList<Letter> letters;
    private boolean completed;
    private Word answer;
    private ArrayList<Word> guessHistory;
    

    /*
     * Creates a game with a random answer.
     */
    public Game(File dictionaryIn, File weightedListFileIn) 
    {
        constructorHelper(dictionaryIn, weightedListFileIn);
        Random rand = new Random();
        String answerStr = wordList.get(rand.nextInt(2315));
        answer = new Word(answerStr);
    }

    /*
     * Creates a game with a specified answer.
     */
    public Game(File dictionaryIn, File weightedListFileIn, String answerIn)
    {
        constructorHelper(dictionaryIn, weightedListFileIn);
        answer = new Word(answerIn);
    }

    private void constructorHelper(File dictionaryIn, File weightedListFileIn)
    {
        guessHistory = new ArrayList<Word>();
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
            collectDictionaryWords();
        }
        catch(FileNotFoundException fnfe)
        {
            System.out.println("File was not found!");
            fnfe.printStackTrace();
            System.exit(1);
        }
    }

    /*
     * Checks to see if the players guess is valid and check completion state.
     * 
     * Returns True, if the guess is in the dictionary and has scored the guess.
     * Returns False, if the guess is not in the dictionary and has not scored the guess.
     */
    public boolean playerGuess(String guessIn)
    {
        guessIn = guessIn.toUpperCase();
        boolean retFlag = false;
        if(guessIn.length() == 5
            && wordList.contains(guessIn))
        {
            Word guess = new Word(guessIn);
            guess.setScore(score(guess));
            guessHistory.add(guess);
            retFlag = true;
        }

        completionState();
        return retFlag;
    }

    /*
     * Checks to see if the player has run out of attempts or guessed the correct word.
     */
    private void completionState()
    {
        String guess = guessHistory.get(guessHistory.size() - 1).getWord(); // Gets the string of the last attempted guess
        if(guess.equals(answer.getWord())
            || guessHistory.size() >= 6)
        {
            completed = true;
        }
    }

    // private String guessLetterStats(String guessIn)
    // {
    //     char[] guessChArr = guessIn.toCharArray();
    //     String score = "";
    //     String concat = "";

    //     int occurrences = 0;
    //     boolean occured = false;
    //     boolean inPlace = false;

    //     for(int i = 0; i < 5; i++)      //guessChArr
    //     {  
    //         Letter letter = letters.get(guessChArr[i] - 65);

    //         for(int j = 0; j < 5; j++)  //answerChArr
    //         {
    //             if(guessChArr[i] == answerChArr[j])
    //             {
    //                 if(i == j)
    //                 {
    //                     inPlace = true;
    //                 }
                    
    //                 occured = true;
    //                 occurrences++;
    //             }
    //         }

    //         letter.attempted(i);

    //         if(occured)
    //         {
    //             if(occurrences > letters.get(guessChArr[i] - 65).getMinOcc())    // if the letter has occured more then the amount it is currently aware of
    //             {
    //                 letter.incrementMinOcc();
    //                 concat = "I";
    //             }
    //             else    // if the letter has occured, but not more then the letter is aware of
    //             {
    //                 letter.setMaxOcc(letter.getMinOcc());
    //                 concat = "X";
    //             }

    //             if(inPlace)    // if the letter in guess occured in that exact location
    //             {
    //                 letter.addCorrect(i);
    //                 concat = "Y";
    //             }
                
    //         } 
    //         else       // if the letter in guess did not occure in the answer at all
    //         {
    //             letter.setMaxOcc(0);
    //             concat = "X";
    //         }

    //         occurrences = 0;
    //         occured = false;
    //         inPlace = false;
    //         score = score.concat(concat);
    //     }

    //     return score;
    // }
    
    // TODO: seperate score and letter data algorithm.
    /*
     * Scores the guess and returns the score of that guess. Also marks current game Letter data.
     * 
     * If a letter was correct, and in the correct position, marks the correct position.
     * If a letter was correct, but in the incorrect position, marks the incorrect position.
     * If the letter was in the answer, marks the minimum number of occurrences it is aware of.
     * If it has found the maximum number of occurrences of a letter, marks that maximum.
     * If the letter was not in the word, marks the maximum number of occurrences as 0.
     * 
     * Returns a String that is the score of the passed guess.
     */
    private String score(Word guessIn)
    {
        char[] guessChArr = guessIn.getWordArr();
        char[] answerChArr = answer.getWordArr();
        String score = "";
        String concat = "";

        int occurrences = 0;
        boolean occured = false;
        boolean inPlace = false;

        for(int i = 0; i < 5; i++)      //guessChArr
        {  
            Letter letter = letters.get(guessChArr[i] - 65);

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

            letter.attempted(i);

            if(occured)
            {
                if(occurrences > letters.get(guessChArr[i] - 65).getMinOcc())    // if the letter has occured more then the amount it is currently aware of
                {
                    letter.incrementMinOcc();
                    concat = "I";
                }
                else    // if the letter has occured, but not more then the letter is aware of
                {
                    letter.setMaxOcc(letter.getMinOcc());
                    concat = "X";
                }

                if(inPlace)    // if the letter in guess occured in that exact location
                {
                    letter.addCorrect(i);
                    concat = "Y";
                }
                
            } 
            else       // if the letter in guess did not occure in the answer at all
            {
                letter.setMaxOcc(0);
                concat = "X";
            }

            occurrences = 0;
            occured = false;
            inPlace = false;
            score = score.concat(concat);
        }

        return score;
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
     * Returns the answer for the game.
     */
    public String getAnswer()
    {
        return answer.getWord();
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
    private void collectDictionaryWords() throws FileNotFoundException
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
    * Prints positional weight data
    */
    public void printWeightData()
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
    * Prints everyword in the dictionary
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
        for(Word g : guessHistory)
        {
            System.out.println(g.getWord());
            System.out.println(g.getScore());
        }
    }

    /*
     * Prints the all Letters and their data.
     */
    public void printLetters()
    {
        for(Letter l : letters)
        {
            l.printLetterData();
        }
    }

    /*
     * Prints answer data
     */
    public void printAnswerData()
    {
        System.out.println("\nAnswer: " + answer.getWord());
        int[] occurrences = answer.getLetterOccurrences();
        for(int i = 0; i < occurrences.length; i++)
        {
            System.out.printf("%c : %d\n", (i + 65), occurrences[i]);
        }
    }

}
