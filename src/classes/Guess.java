package classes;
public class Guess {

    private char[] placement;

    public Guess()
    {
        placement[0] = '!';
        placement[1] = '!';
        placement[2] = '!';
        placement[3] = '!';
        placement[4] = '!';
     }

    public Guess(char p1, char p2, char p3, char p4, char p5)
    {
        placement[0] = p1;
        placement[1] = p2;
        placement[2] = p3;
        placement[3] = p4;
        placement[4] = p5;
    }

    public char getPlacement(int pIn)
    {
        return placement[pIn];
    }

    public void setPlacement (int pIn, char lettIn)
    {
        placement[pIn] = lettIn;
    }

    public char[] getGuess()
    {
        return placement;
    }
}
