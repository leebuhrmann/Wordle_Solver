package classes;
import java.util.ArrayList;

public class Letter {
    
    private char lett;
    private ArrayList<Integer> correctPlacement;
    private ArrayList<Integer> incorrectPlacement;
    private int minOcc;
    private int maxOcc;         //if 0, means it is not a correct letter
    private boolean attempted;  //has been used in a guess

    public Letter(char lettIn)
    {
        lett = lettIn;
        minOcc = 0;
        maxOcc = 5;
        attempted = false;
        correctPlacement = new ArrayList<Integer>();
        incorrectPlacement = new ArrayList<Integer>();
    }

    public void incrementMinOcc()
    {
        minOcc++;
    }

    public int getMinOcc()
    {
        return minOcc;
    }

    public void setMaxOcc(int occIn)
    {
        maxOcc = occIn;
    }

    public int getMaxOcc()
    {
        return maxOcc;
    }

    /*
     * Sets attempted to true.
     */
    public void hasBeenAttempted()
    {
        attempted = true;
    }

    public boolean getAttempted()
    {
        return attempted;
    }

    public char getLett()
    {
        return lett;
    }

    public void addCorrect(int placement)
    {
        correctPlacement.add(placement);
    }

    public ArrayList<Integer> getCorrect()
    {
        return correctPlacement;
    }

    public void addIncorrect(int placement)
    {
        incorrectPlacement.add(placement);
    }

    public ArrayList<Integer> getIncorrect()
    {
        return incorrectPlacement;
    }
}
