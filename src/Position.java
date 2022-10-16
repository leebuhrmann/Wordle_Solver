public class Position {
    
    /*
     * Index 0 represents 'a' and index 25 represents 'z'
     */
    private int[] weight;

    public Position(int pIn)
    {
        weight = new int[26];
    }

    /*
     * lettIn 
     */
    public void setWeight(int lettIn, int weightIn)
    {
        weight[lettIn] = weightIn;
    }

    public int getWeight(int lettIn)
    {
        return weight[lettIn];
    }
}
