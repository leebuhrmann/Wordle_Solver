package classes;
public class Guess {

    private String guess;
    private String score;

    public Guess(String guessIn, String scoreIn)
    {
        guess = guessIn;
        score = scoreIn;
    }

    public String getGuess()
    {
        return guess;
    }

    public String getScore()
    {
        return score;
    }
}
