public class Position {
    
    /*
     * Index 0 represents 'A' and index 25 represents 'Z'
     */
    private int[] weight;

    public Position()
    {
        weight = new int[26];
    }

    public void setWeight(int lettIn, int weightIn)
    {
        weight[lettIn] = weightIn;
    }

    public int getWeight(int lettIn)
    {
        return weight[lettIn];
    }
}
