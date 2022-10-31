package classes;
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

    // TODO: may not need
    public Word(String wordIn, String scoreIn)
    {
        score = scoreIn;
        constructorHelper(wordIn);
    }

    public Word(String wordIn)
    {
        score = null;
        constructorHelper(wordIn);
    }

    private void constructorHelper(String wordIn)
    {
        word = wordIn.toUpperCase();
        wordArr = word.toCharArray();
        letterOccNum = new int[26];
        letterCounter();
    }

    /*
     * Counts the number of occurrences of each letter in this word
     */
    private void letterCounter()
    {
        for(int i = 0; i < wordArr.length; i++)
        {
            letterOccNum[wordArr[i] - 65]++;
        }
    }

    /*
     * Sets score.
     */
    public void setScore(String scoreIn)
    {
        score = scoreIn;
    }

    /*
     * Returns word as a String
     */
    public String getWord()
    {
        return word;
    }

    /*
     * Returns word as a character array
     */
    public char[] getWordArr()
    {
        return wordArr;
    }

    /*
     * Returns score as a String
     */
    public String getScore()
    {
        return score;
    }

    /*
     * Returns an int[] containing the number of occurrences of each letter
     */
    public int[] getLetterOccurrences()
    {
        return letterOccNum;
    }
}
