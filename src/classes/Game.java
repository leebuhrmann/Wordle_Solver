package classes;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Game
{
    // ############# Constant Parameters #############

    /*
     * This value is the number of words at the beggining of the dictionary that can be answers.
     * The rest of the words can be accepted, but are not answers.
     */
    private static final int CORRECT_WORD_LIST_SIZE = 2314;
    /*
     * Used to shift a character on the ANSII table into a int representing its number in the alphabet
     * and vice a versa.
     */
    private static final int CHAR_SHIFT = 65;
    private static final int ALPHABET_SIZE = 26;
    private static final int WORD_LENGTH = 5;


    // ############# Files Data #############

     /*
      * Contains every word in the passed dictionary File.
      */
    private ArrayList<String> wordList;
    /*
     * Contains a weighted value for each character of the alphabet. The value represents the
     * number of times that character as found in that position of all the words in the dictionary.
     */
    private int[][] pWeights;
    /*
     * The positional weights sorted from most common Letter to least common Letter
     */
    private Letter[][] pSorted;
    /*
     * The weighted values for the Letters in pSorted. Matched by index.
     */
    private int[][] pSortedWeights;

    // ############# Game and Scored Fields #############

    private int num_attempts;
    private ArrayList<Letter> letters;
    private boolean completed;
    private Word answer;
    private ArrayList<Word> guessHistory;
    private char[] guess;
    

    /**
     * Creates a game with a random answer.
     * 
     * @param   dictionaryIn    The File containing all the playable words of this game.
     * @param   weightedListFileIn  The File containing all the weighted letter data derived from the dictionary.
     */
    public Game(String dictionaryIn, String weightedListFileIn, int numAttemptsIn) 
    {
        constructorHelper(dictionaryIn, weightedListFileIn, numAttemptsIn);
        Random rand = new Random(); // Create a random answer for this Game.
        String answerStr = wordList.get(rand.nextInt(CORRECT_WORD_LIST_SIZE + 1));
        answer = new Word(answerStr);
    }

    /**
     * Creates a game with a specified answer.
     * 
     * @param   dictionaryIn    The File containing all the playable words of this game.
     * @param   weightedListFileIn  The File containing all the weighted letter data derived from the dictionary.
     * @param   answerIn    The word to be set as the answer for this Game.
     */
    public Game(String dictionaryIn, String weightedListFileIn, String answerIn, int numAttemptsIn)
    {
        constructorHelper(dictionaryIn, weightedListFileIn, numAttemptsIn);
        answer = new Word(answerIn); // Create specified answer for this Game.
    }

    /**
     * Assist the constructors with instantiating fields of this Game.
     * 
     * @param   dictionaryIn    The File containing all the playable words of this game.
     * @param   weightedListFileIn  The File containing all the weighted letter data derived from the dictionary.
     */
    private void constructorHelper(String dictionaryIn, String weightedListFileIn, int numAttemptsIn)
    {
        num_attempts = numAttemptsIn;
        guessHistory = new ArrayList<Word>();
        pWeights = new int[WORD_LENGTH][ALPHABET_SIZE];
        pSorted = new Letter[WORD_LENGTH][ALPHABET_SIZE];
        pSortedWeights = new int[WORD_LENGTH][ALPHABET_SIZE];
        wordList = new ArrayList<String>();
        letters = new ArrayList<Letter>();
        completed = false;
        guess = new char[WORD_LENGTH];

        try
        {
            collectWeightData(weightedListFileIn);
            fillLetters();
            processWeightData();
            collectDictionaryWords(dictionaryIn);
        }
        catch(RuntimeException re)
        {
            re.printStackTrace();
            System.exit(1);
        }
    }


    /**
     * Attempts to perform a single game step.
     * 
     * Checks to see if the passed guess is valid. If the passed guess is valid it will
     * score the guess, update the game Letter data, and check the completion state.
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
        if(guessIn.length() == WORD_LENGTH  // Check to see if passed word was valid.
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
     * Plays a game where the computer makes its own guesses until it finds the answer.
     * Uses a random first guess.
     */
    public void botPlays()
    {
        Random rand = new Random();
        char[] newGuess = wordList.get(rand.nextInt(CORRECT_WORD_LIST_SIZE)).toCharArray(); // Makes a random first guess.
        setGuess(newGuess);
        gameStep(new String(getGuess()));

        while(!completed() && makeGuess())
        {
            String guessString = new String(getGuess());
            gameStep(guessString);
        }
    }

    /**
     * Plays a game where the computer makes its own guesses until it finds the answer.
     * This version is for testing purposes.
     * 
     * @param firstGuess - The first guess the bot will play.
     */
    public void botPlays(String firstGuess)
    {
        char[] newGuess = firstGuess.toCharArray();
        setGuess(newGuess);
        gameStep(new String(getGuess()));

        while(!completed() && makeGuess())
        {
            String guessString = new String(getGuess());
            gameStep(guessString);
        }
    }

    /**
     * Generates a best guess from past guess data. Specifically the
     * constructed guess follows these conditions:
     * - Uses the minimum number of known occurences of known correct letters
     * - Places the correctly guessed letters in their known positions
     * - Does not guess letters that are known to not exist in the answer
     * - Does not guess known letters in positions that have already been attempted
     * - Does not guess a word that does not exist in the accepted word list
     *  
     * 
     * @return {@code true} If it successfully constructed an adequate guess,
     *         {@code false} If otherwise.
     */
    public boolean makeGuess()
    {
        char[] newGuess = getGuess();
        for(int i = 0; i < newGuess.length; i++) // Fill new guess with 'blanks'
        {
            newGuess[i] = ' ';
        }
      
        Stack<Letter> toRecurse = new Stack<Letter>();

        for(Letter l : letters)     // Loop through all letters.
        {
            ArrayList<Integer> correctPos = l.getCorrect(); // Grab list of all correct positions for that letter.

            if(!correctPos.isEmpty())
            {
                for(Integer n : correctPos)             // Assign that letter to all correct positions in the current guess being made.
                {
                    newGuess[n] = l.getLett();
                }
            }

            int difference = l.getMinOcc() - correctPos.size();
            for(int i = 0; i < difference; i++)            
            {
                toRecurse.push(l);                       // Fill a Letters list with letters we know are in the guess but do not know what position they belong to.
            }
        }

        return makeGuessRecurseA(toRecurse, newGuess);
    }

    /**
     * Helper function for makeGuess. 
     * 
     * @param toRecurseIn a list of letters that are known to occure in the 
     * answer and have yet to be placed.
     * @param newGuessIn the currently constructed guess.
     * @return
     */
    private boolean makeGuessRecurseA(Stack<Letter> toRecurseIn, char[] newGuessIn)
    {
        Stack<Integer> blanks = new Stack<Integer>();

        char[] newGuess = new char[WORD_LENGTH];
        for(int i = 0; i < newGuessIn.length; i++)    // Make deep copy of newGuessIn
        {
            newGuess[i] = newGuessIn[i];
        }

        for(int i = newGuess.length - 1; i >= 0; i--) // Make a list of blank positions of the current guess.
        {
            if(newGuess[i] == ' ') // if 'blank'.
            {
                blanks.push(i);
            }
        }

        if(!toRecurseIn.isEmpty())
        {
            Letter l = toRecurseIn.pop();
            ArrayList<Integer> lSortedCopy = new ArrayList<Integer>();
            for(Integer n : l.getSorted())  // Create deep copy of sorted list.
            {
                lSortedCopy.add(n);
            }

            Iterator<Integer> itr = lSortedCopy.iterator();
            while(itr.hasNext())        // Creates a sorted list with only the positions that are also blank on the current guess.
            {
                int n = itr.next();
                if(!blanks.contains(n))
                {
                    itr.remove(); 
                }
            }

            itr = lSortedCopy.iterator();    // Removes positions that letter has already attempted.
            while(itr.hasNext())
            {
                int n = itr.next();
                if(!l.getNotAttempted().contains(n))
                {
                    itr.remove(); 
                }
            }

            for(int i = 0; i < lSortedCopy.size(); i++) // Puts Letter l in the highest weighted position in its list.
            {
                newGuess[lSortedCopy.get(i)] = l.getLett();
                if(makeGuessRecurseA(toRecurseIn, newGuess))
                {
                    return true;
                }

                newGuess[lSortedCopy.get(i)] = ' ';    // Restore previous guess.
            }


            toRecurseIn.push(l);    // Restore list.
            return false;
        }
        else
        {
            return makeGuessRecurseB(blanks, newGuess);    
        }
    }

    /**
     * Helper function for makeGuessA.
     * 
     * @param blanksIn A list of position in the newGuess that are currently blank.
     * @param newGuessIn The currently constructed guess.
     * @return
     */
    private boolean makeGuessRecurseB(Stack<Integer> blanksIn, char[] newGuessIn)
    {
        if(!blanksIn.isEmpty())
        {
            char[] newGuess = new char[WORD_LENGTH];
            for(int i = 0; i < newGuessIn.length; i++)    // Make deep copy of newGuessIn
            {
                newGuess[i] = newGuessIn[i];
            }

            int n = blanksIn.pop();
            for(int p = 0; p < ALPHABET_SIZE; p++)
            {
                char c =  pSorted[n][p].getLett();
                if(letters.get(c - CHAR_SHIFT).getMaxOcc() > 0                      // If this letter is not known to not occure in this guess, continue. (AND)
                    && letters.get(c - CHAR_SHIFT).getNotAttempted().contains(n))   // If this letter has not been attempted at this position, continue.
                {
                    newGuess[n] = c;
                    if(makeGuessRecurseB(blanksIn, newGuess))
                    {
                        return true;
                    }
                }
            }

            newGuess[n] = ' ';     // Restore previous guess.
            blanksIn.push(n);
            return false;
        }
        else if(wordList.contains(new String(newGuessIn)))
        {
            setGuess(newGuessIn);
            return true;
        }
        else
        {
            return false; 
        }
    }


    /**
     * Detects and sets the completion state of this Game.
     */
    private void completionState()
    {
        if(!guessHistory.isEmpty())
        {
            String guess = guessHistory.get(guessHistory.size() - 1).getWord(); // Gets the string of the last attempted guess.
            if(guess.equals(answer.getWord())               // Checks the state of the game.
                || guessHistory.size() >= num_attempts)
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

        /*
         * Checks to see if a character is in the correct position and marks if it has 
         * been attempted in its coresponding Letter.
         */
        for(int i = 0; i < WORD_LENGTH; i++)      // Loops through characters in guessIn.
        {  
            Letter letter = letters.get(guessChArr[i] - CHAR_SHIFT);    // Grabs the current guess characters corresponding Letter.

            for(int j = 0; j < WORD_LENGTH; j++)  // Loops through characters in the answer.
            {
                if(guessChArr[i] == answerChArr[j])
                {         
                    if(i == j)
                    {
                        inPlace = true;
                    }
                }
            }

            /*
             * Checks to see if the character was in the correct position and has not already 
             * been added to its Letter.
             */
            letter.attempted(i);
            if(inPlace && !letter.getCorrect().contains(i))
            {
                letter.addCorrect(i);
            }

            inPlace = false;
        }

       /*
        * Sets minimum and maximum occurences known for each Letter.
        */
        int[] guessOcc = guessIn.getLetterOccurrences();
        int[] answerOcc = answer.getLetterOccurrences();
        for(int i = 0; i < ALPHABET_SIZE; i++)
        {
            Letter letter = letters.get(i);
            if(guessOcc[i] <= answerOcc[i]
                && guessOcc[i] > letter.getMinOcc())
            {
                letter.setMinOcc(guessOcc[i]);
            }
            else if(guessOcc[i] > answerOcc[i])
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
        char[] score = new char[WORD_LENGTH];

        /*
         * Scores all correct characters in correct positions
         */
        for(int i = 0; i < WORD_LENGTH; i++)      // Loops through characters in guessIn.
        {  
            for(int j = 0; j < WORD_LENGTH; j++)  // Loops through characters in the answer.
            {
                char gc = guessChArr[i];
                char ac = answerChArr[j];
                if(gc == ac && i == j)    
                {
                    score[i] = 'Y';
                    lettCounter[guessChArr[i] - CHAR_SHIFT]++;
                }
            }
        }

        /*
         * Scores all remaining characters.
         */
        for(int i = 0; i < WORD_LENGTH; i++)      // Loops through characters in guessIn.
        {  
            if(score[i] != 'Y') // Skips this character if it has already been scored as correct.
            {
                for(int j = 0; j < WORD_LENGTH; j++)  // Loops through characters in the answer.
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
    * Process positional weight data from passed File.
    *
    * @param    weightedListFileIn  the File to be processed.
    * @throws   FileNotFoundException
    */
    private void collectWeightData(String weightedListFileIn) throws RuntimeException
    {
        int n = 0;
        InputStream is = getClass().getResourceAsStream(weightedListFileIn);
        if (is == null)
        {
            throw new RuntimeException("Cannot find " + weightedListFileIn + " in classpath");
        }
        Scanner in = new Scanner(is);
        
        while(in.hasNextLine() && n < ALPHABET_SIZE)
        {
            String record = in.nextLine();
            Scanner lineParser = new Scanner(record);
            lineParser.useDelimiter(",");
            
            for(int j = 0; j < WORD_LENGTH; j++)
            {
                pWeights[j][n] = lineParser.nextInt();
                pSortedWeights[j][n] = pWeights[j][n];
            }

            lineParser.close();
            n++;
        }
        in.close();
    }

    /**
    * Fills the letters list with Instantiated Letters.
    */
    private void fillLetters()
    {
        for(int i = 0; i < ALPHABET_SIZE; i++)
        {
            int[] weightsOut = new int[WORD_LENGTH];
            for(int j = 0; j < WORD_LENGTH; j++)
            {
                weightsOut[j] = pWeights[j][i];
            }
            char cOut = (char)(i + CHAR_SHIFT);
            letters.add(new Letter(cOut, weightsOut));
        }
    }

    /**
     * Process weight data into additional sorted lists.
     */
    private void processWeightData()
    {
         /*
         * Fills pSorted with letters in each position in alphabetical order.
         */
        for(int i = 0; i < WORD_LENGTH; i++)
        {
            for(int j = 0; j < ALPHABET_SIZE; j++)
            {
                pSorted[i][j] = letters.get(j);
            }
        }

        /*
         * Sorts every position by most common letter to least common letter.
         */
        for(int i = 0; i < WORD_LENGTH; i++)
        {
            int a = ALPHABET_SIZE;
            for (int j = 0; j < a - 1; j++)
            {
                for (int k = 0; k < a - j - 1; k++)
                {
                    if (pSortedWeights[i][k] < pSortedWeights[i][k + 1])
                    {
                        // swap arr[k+1] and arr[k]
                        int temp = pSortedWeights[i][k];
                        pSortedWeights[i][k] = pSortedWeights[i][k + 1];
                        pSortedWeights[i][k + 1] = temp;

                        Letter lTemp = pSorted[i][k];
                        pSorted[i][k] = pSorted[i][k + 1];
                        pSorted[i][k + 1] = lTemp;
                    }
                }
            }
        }
    }

    /**
    * Collect every word from passed File.
    *
    * @param    dictionaryIn    the File to be collected.
    * @throws   FileNotFoundException
    */
    private void collectDictionaryWords(String dictionaryIn) throws RuntimeException
    {
        InputStream is = getClass().getResourceAsStream(dictionaryIn);
        if (is == null)
        {
            throw new RuntimeException("Cannot find " + dictionaryIn + " in classpath");
        }
        Scanner in = new Scanner(is);
        in.nextLine(); // Skip first line.
        while(in.hasNextLine())
        {
            String record = in.nextLine();
            Scanner lineParser = new Scanner(record);
            
            lineParser.next();                  // Skip date.
            lineParser.nextInt();               // Skip word number.
            String word = lineParser.next();    // Grab word.
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
     * Returns this games current guess.
     * 
     * @return  this games current guess.
     */
    private char[] getGuess()
    {
        return guess;
    }

    /**
     * Sets this Game's current guess as passed arguement.
     * 
     * @param   guessIn   what guess will be set to.
     */
    private void setGuess(char[] guessIn)
    {
        guess = guessIn;
    }

    /**
    * Prints positional weight data.
    */
    public void printWeightData()
    {
        System.out.print("\nPOS|  1  |  2  |  3  |  4  |  5  |\n");
        for(int i = 0; i < ALPHABET_SIZE; i++)
        {
            System.out.printf(" %c |", (i + CHAR_SHIFT));
            for(int j = 0; j < WORD_LENGTH; j++)
            {
                System.out.printf(" %3d |", pWeights[j][i]);
            }
            System.out.println();
        }

        System.out.print("\nPOS| 1 | 2 | 3 | 4 | 5 |\n");
        for(int i = 0; i < ALPHABET_SIZE; i++)
        {
            System.out.printf(" * |");
            for(int j = 0; j < WORD_LENGTH; j++)
            {
                System.out.printf(" %c |", pSorted[j][i].getLett());
            }
            System.out.println();
        }
    }

    /**
    * Prints everyword in the wordlist.
    *
    * WARNING: Large number of words printed.
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
        System.out.print("\n============="
                        + "\n*** Score ***"
                        + "\n=============\n");
        for(Word g : guessHistory)
        {
            System.out.println("    " + g.getWord());
            System.out.println("    " + g.getScore());
        }
    }

    /**
     * Prints the current score of the game.
     */
    public void printAttemptsLeft()
    {
        System.out.println("\nNumber of attempts left: " + (num_attempts - guessHistory.size()));
    }

    /**
     * Prints the players results.
     */
    public void printResultsPlayer()
    {
        System.out.println();
        if(guessHistory.get(guessHistory.size() - 1).getWord().equals(answer.getWord()))
        {
            System.out.println("You won!");
        }
        else
        {
            System.out.println("Sorry, you lost. The answer was " + answer.getWord());
        }
    }

    /**
     * Prints the results of the bot.
     */
    public void printResultsBot()
    {
        System.out.println();
        printGame();
        System.out.println("It took the computer " + guessHistory.size() + " attempts to win.");
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
