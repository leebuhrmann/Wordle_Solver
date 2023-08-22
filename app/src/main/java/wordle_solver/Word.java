

public class Word {

    private String word;
    private char[] wordArr;
    private String score;
    /*
     * Indes 0 is the letter 'A' and 25 is 'Z'
     * the number found at that letters index is the number of 
     * occurrences of that letter in this word.
     */
    private int[] letterOccNum;

    /**
     * Creates a Word to be used in a Game. This Word knows the number of occurrences of
     * each letter of the alphabet within itself. It also stores a possible score of that
     * word against a Game's answer.
     * 
     * @param   wordIn    A word.
     * @param   scoreIn   A score of a word.
     */
    public Word(String wordIn, String scoreIn)
    {
        score = scoreIn;
        constructorHelper(wordIn);
    }

    /**
     * Creates a Word to be used in a Game. This Word knows the number of occurrences of
     * each letter of the alphabet within itself.
     * 
     * @param   wordIn A word.
     */
    public Word(String wordIn)
    {
        score = null;
        constructorHelper(wordIn);
    }

    /**
     * Assist the constructors with instantiating fields of this Word.
     * 
     * @param   wordIn  A word
     */
    private void constructorHelper(String wordIn)
    {
        word = wordIn.toUpperCase();
        wordArr = word.toCharArray();
        letterOccNum = new int[26];
        letterCounter();
    }

    /**
     * Counts the number of occurrences of each letter in this Word.
     */
    private void letterCounter()
    {
        for(int i = 0; i < wordArr.length; i++)
        {
            letterOccNum[wordArr[i] - 65]++;
        }
    }

    /**
     * Sets the score of this Word.
     * 
     * @param   scoreIn   The score to be set.
     */
    public void setScore(String scoreIn)
    {
        score = scoreIn;
    }

    /**
     * Returns this Word as a String.
     * 
     * @return  This word as a String.
     */
    public String getWord()
    {
        return word;
    }

    /**
     * Returns this Word as a character array.
     * 
     * @return  this Word as a character array.
     */
    public char[] getWordArr()
    {
        return wordArr;
    }

    /**
     * Returns this Word's score as a String.
     * @return  this Word's score as a String.
     */
    public String getScore()
    {
        return score;
    }

    /**
     * Returns an integer array containing the number of occurrences of each
     * letter in this Word.
     * 
     * @return  an integer array containing the number of occurrences of each
     * letter in this Word.
     */
    public int[] getLetterOccurrences()
    {
        return letterOccNum;
    }
}
