package classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    /*
     * Game parameters.
     */
    private static final int NUM_ATTEMPTS = 6;
    private static final int WORD_LEGNTH = 5;
    /*
     * Used to shift a character on the ANSII table into a int representing its number in the alphabet
     * and vice a versa.
     */
    private static final int CHAR_SHIFT = 65;
    private static final int ALPHABET_SIZE = 26;
    /*
     * This value is the number of words at the beggining of the dictionary that can be answers.
     * The rest of the words can be accepted, but are not answers.
     */
    private static final int CORRECT_WORD_LIST_SIZE = 2314;

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
    

    /**
     * Creates a game with a random answer.
     * 
     * @param   dictionaryIn    The File containing all the playable words of this game.
     * @param   weightedListFileIn  The File containing all the weighted letter data derived from the dictionary.
     */
    public Game(File dictionaryIn, File weightedListFileIn) 
    {
        constructorHelper(dictionaryIn, weightedListFileIn);
        Random rand = new Random();
        String answerStr = wordList.get(rand.nextInt(CORRECT_WORD_LIST_SIZE + 1));
        answer = new Word(answerStr);
    }

    /**
     * Creates a game with a random answer.
     * 
     * @param   dictionaryIn    The File containing all the playable words of this game.
     * @param   weightedListFileIn  The File containing all the weighted letter data derived from the dictionary.
     * @param   answerIn    The word to be set as the answer for this Game.
     */
    public Game(File dictionaryIn, File weightedListFileIn, String answerIn)
    {
        constructorHelper(dictionaryIn, weightedListFileIn);
        answer = new Word(answerIn);
    }

    /**
     * Assist the constructors with instantiating fields of this Game.
     * 
     * @param   dictionaryIn    The File containing all the playable words of this game.
     * @param   weightedListFileIn  The File containing all the weighted letter data derived from the dictionary.
     */
    private void constructorHelper(File dictionaryIn, File weightedListFileIn)
    {
        guessHistory = new ArrayList<Word>();
        p1Weights = new int[ALPHABET_SIZE];
        p2Weights = new int[ALPHABET_SIZE];
        p3Weights = new int[ALPHABET_SIZE];
        p4Weights = new int[ALPHABET_SIZE];
        p5Weights = new int[ALPHABET_SIZE];
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

    /**
     * Attempts to perform a single game step.
     * 
     * Checks to see if the passed guess is valid. If the passed guess is valid it will
     * score the guess, update the game letter data, and check the completion state.
     * 
     * @param   guessIn The word to be validated with the working dictionary and scored
     * against this Game's answer.
     * 
     * @return  {@code true} if the guess is in the dictionary and has scored the guess.
     *          {@code false} if the guess is not in the dictionary and has not scored 
     *          the guess.
     */
    public boolean gameStep(String guessIn)
    {
        guessIn = guessIn.toUpperCase();
        boolean retFlag = false;
        if(guessIn.length() == WORD_LEGNTH
            && wordList.contains(guessIn))
        {
            Word guess = new Word(guessIn);
            guess.setScore(score(guess));
            updateLetters(guess);
            guessHistory.add(guess);
            retFlag = true;
        }

        completionState();
        return retFlag;
    }

    /**
     * Detects and sets the completion state of this Game.
     */
    private void completionState()
    {
        if(!guessHistory.isEmpty())
        {
            String guess = guessHistory.get(guessHistory.size() - 1).getWord(); // Gets the string of the last attempted guess
            if(guess.equals(answer.getWord())
                || guessHistory.size() >= NUM_ATTEMPTS)
            {
                completed = true;
            }
        }
    }

    /**
     * Updates known letter data after a guess is scored.
     * 
     * @param   guessIn The last scored guess.
     */
    private void updateLetters(Word guessIn)
    {
        char[] guessChArr = guessIn.getWordArr();
        char[] answerChArr = answer.getWordArr();
        boolean inPlace = false;

        //Checks to see if a letter is in the correct position and marks if it has been attempted.
        for(int i = 0; i < WORD_LEGNTH; i++)      //guessChArr
        {  
            Letter letter = letters.get(guessChArr[i] - CHAR_SHIFT);

            for(int j = 0; j < WORD_LEGNTH; j++)  //answerChArr
            {
                if(guessChArr[i] == answerChArr[j])
                {
                    if(i == j)
                    {
                        inPlace = true;
                    }
                }
            }

            letter.attempted(i);
            if(inPlace && !letter.getCorrect().contains(i))
            {
                letter.addCorrect(i);
            }

            inPlace = false;
        }

       /*
        * Sets minimum and maximum occurences known for each letter
        */
        int[] guessOcc = guessIn.getLetterOccurrences();
        int[] answerOcc = answer.getLetterOccurrences();
        for(int i = 0; i < ALPHABET_SIZE; i++)
        {
            Letter letter = letters.get(i);
            if(guessOcc[i] <= answerOcc[i])
            {
                letter.setMinOcc(guessOcc[i]);
            }
            else
            {
                letter.setMinOcc(answerOcc[i]);
                letter.setMaxOcc(answerOcc[i]);
            }
        }
    }
    
    /**
     * Returns a score of a guess against the answer.
     * 
     * @param   guessIn The word to be scored against this Game's answer.
     * 
     * @return  The score of guessIn.
     */
    private String score(Word guessIn)
    {
        char[] guessChArr = guessIn.getWordArr();
        char[] answerChArr = answer.getWordArr();
        int[] lettCounter = new int[ALPHABET_SIZE];
        char[] score = new char[WORD_LEGNTH];

        // Scores all correct letters in correct positions
        for(int i = 0; i < WORD_LEGNTH; i++)      //guessChArr
        {  
            for(int j = 0; j < WORD_LEGNTH; j++)  //answerChArr
            {
                char gc = guessChArr[i];
                char ac = answerChArr[j];
                if(gc == ac && i == j)    // letter in a position of guess found in the same position as answer
                {
                    score[i] = 'Y';
                    lettCounter[guessChArr[i] - CHAR_SHIFT]++;
                }
            }
        }

        // scores remaining letters
        for(int i = 0; i < WORD_LEGNTH; i++)      //guessChArr
        {  
            if(score[i] != 'Y') // skip if already scored as correct
            {
                for(int j = 0; j < WORD_LEGNTH; j++)  //answerChArr
                {
                    char gc = guessChArr[i];
                    char ac = answerChArr[j];
                    if(gc == ac
                        && lettCounter[gc - CHAR_SHIFT] < answer.getLetterOccurrences()[gc - CHAR_SHIFT])
                    {
                        score[i] = 'I';
                        lettCounter[gc - CHAR_SHIFT]++;
                        break;
                    }
                    else
                    {
                        score[i] = 'X';
                    }
                }
            }
        }

        return new String(score);
    }

    /**
     * Returns a Letter object of this game specified by its character.
     * 
     * @param   lettIn  The label of the desired Letter object.
     * 
     * @return  A specified Letter of this Game.
     */
    public Letter getLetter(char lettIn)
    {
        lettIn = Character.toUpperCase(lettIn);
        return letters.get(lettIn - CHAR_SHIFT);
    }

    /**
     * Returns the answer as a String.
     * 
     * @return The answer of this Game.
     */
    public String getAnswer()
    {
        return answer.getWord();
    }

    /**
    * Fills the letters list with Instantiated Letters.
    */
    private void fillLetters()
    {
        for(int i = 0; i < ALPHABET_SIZE; i++)
        {
            int[] weightsOut = new int[WORD_LEGNTH];
            weightsOut[0] = p1Weights[i];
            weightsOut[1] = p2Weights[i];
            weightsOut[2] = p3Weights[i];
            weightsOut[3] = p4Weights[i];
            weightsOut[4] = p5Weights[i];

            char cOut = (char)(i + CHAR_SHIFT);
            letters.add(new Letter(cOut, weightsOut));
        }
    }

    /**
    * Collect positional weight data from this.weightedListFIle.
    *
    * @throws FileNotFoundException
    */
    private void collectWeightData() throws FileNotFoundException
    {
        int i = 0;
        Scanner in = new Scanner(weightedListFile);
        while(in.hasNextLine() && i < ALPHABET_SIZE)
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

    /**
    * Collect every word from this.dictionary.
    *
    * @throws   FileNotFoundException
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

    /**
     * Returns whether the game has been completed or not.
     * 
     * @return  {@code true} if the game has been completed,
     *          {@code false} otherwise.
     */
    public boolean completed()
    {
        return completed;
    }
    
    /**
    * Prints positional weight data.
    */
    public void printWeightData()
    {
        System.out.print("\nPOS|  1  |  2  |  3  |  4  |  5  |\n");
        for(int i = 0; i < ALPHABET_SIZE; i++)
        {
            System.out.printf(" %c | %3d | %3d | %3d | %3d | %3d |\n"
                                , (i + CHAR_SHIFT), p1Weights[i], p2Weights[i]
                                , p3Weights[i], p4Weights[i]
                                , p5Weights[i]);
        }
    }

    /**
    * Prints everyword in the wordlist.
    *
    * Warning: Large number of words printed.
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

    /**
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

    /**
     * Prints all Letters and their data.
     */
    public void printLetters()
    {
        for(Letter l : letters)
        {
            l.printLetterData();
        }
    }

    /**
     * Prints answer data.
     */
    public void printAnswerData()
    {
        System.out.println("\nAnswer: " + answer.getWord());
        int[] occurrences = answer.getLetterOccurrences();
        for(int i = 0; i < occurrences.length; i++)
        {
            System.out.printf("%c : %d\n", (i + CHAR_SHIFT), occurrences[i]);
        }
    }
}
