package classes;
import java.util.ArrayList;

public class Letter {

    private int minOcc;
    private int maxOcc;         //if 0, means it is not a correct letter
    private char lett;
    private ArrayList<Integer> correctPlacement;
    private ArrayList<Integer> notAttemptedPlacements;
    private int[] weightedPlacements;       // The number of occurences of this letter at a specific placment. The placement is the index.
    private int[] sortedPlacements;   // The most common placement of this letter determined by its weighted values, ordered by most common to least common.
    

    public Letter(char lettIn, int[] weightsIn)
    {
        lett = lettIn;
        minOcc = 0;
        maxOcc = 5;
        correctPlacement = new ArrayList<Integer>();
        notAttemptedPlacements = new ArrayList<Integer>();
        for(int i = 0; i < 5; i++)
        {
            notAttemptedPlacements.add(i);
        }

        weightedPlacements = weightsIn;
        sortedPlacements = new int[5];
        for(int i = 0; i < 5; i++)
        {
            sortedPlacements[i] = i;
        }
        orderPlacements();
    }

    private void orderPlacements()
    {
        int n = weightedPlacements.length;
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                if (weightedPlacements[j] < weightedPlacements[j + 1])
                {
                    // swap arr[j+1] and arr[j]
                    int temp = weightedPlacements[j];
                    weightedPlacements[j] = weightedPlacements[j + 1];
                    weightedPlacements[j + 1] = temp;

                    temp = sortedPlacements[j];
                    sortedPlacements[j] = sortedPlacements[j + 1];
                    sortedPlacements[j + 1] = temp;
                }
            }
        }

        System.out.println(this.getLett());
        for(int i = 0; i < 5; i++)
        {
            System.out.print(" " + weightedPlacements[i]);
        }
        System.out.println();
        for(int i = 0; i < 5; i ++)
        {
            System.out.print(" " + sortedPlacements[i]);
        }
        System.out.println();
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

    /*
     * Removes the passed placement from the notAttempted list.
     */
    public void attempted(Integer placement)
    {
        notAttemptedPlacements.remove(placement);
    }

    public ArrayList<Integer> getNotAttempted()
    {
        return notAttemptedPlacements;
    }

    public int[] getSorted()
    {
        return sortedPlacements;
    }
}
