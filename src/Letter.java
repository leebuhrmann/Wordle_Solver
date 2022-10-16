public class Letter {
    
    private char lett;
    private int minOcc;
    private int maxOcc;         //if 0, means it is not a correct letter
    private boolean attempted;  //has been used in a guess

    public Letter(char lettIn)
    {
        lett = lettIn;
        minOcc = -1;
        maxOcc = -1;
        attempted = false;
    }

    public void setMinOcc(int occIn)
    {
        minOcc = occIn;
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
}
