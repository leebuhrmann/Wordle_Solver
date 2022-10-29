package classes;
import java.util.ArrayList;

public class Letter {

    private int minOcc;
    private int maxOcc;         //if 0, means it is not a correct letter
    private char lett;
    private ArrayList<Integer> correctPlacement;
    private ArrayList<Integer> notAttemptedPlacements;
    private ArrayList<Integer> weightedPlacements;       // The number of occurences of this letter at a specific placment. The placement is the index.
    private ArrayList<Integer> sortedPlacements;   // The most common placement of this letter determined by its weighted values, ordered by most common to least common.
    

    public Letter(char lettIn, int[] weightsIn)
    {
        lett = Character.toUpperCase(lettIn);
        minOcc = 0;
        maxOcc = 5;
        correctPlacement = new ArrayList<Integer>();
        notAttemptedPlacements = new ArrayList<Integer>();
        for(int i = 0; i < 5; i++)
        {
            notAttemptedPlacements.add(i);
        }

        weightedPlacements = new ArrayList<Integer>();
        for(int i = 0; i < 5; i++)
        {
            weightedPlacements.add(weightsIn[i]);
        }
        sortedPlacements = new ArrayList<Integer>();
        for(int i = 0; i < 5; i++)
        {
            sortedPlacements.add(i);
        }
        orderPlacements();
    }

    private void orderPlacements()
    {
        //bubble sort
        int n = weightedPlacements.size();
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                if (weightedPlacements.get(j) < weightedPlacements.get(j + 1))
                {
                    // swap arr[j+1] and arr[j]
                    int temp = weightedPlacements.get(j);
                    weightedPlacements.set(j, weightedPlacements.get(j + 1));
                    weightedPlacements.set(j + 1, temp);

                    temp = sortedPlacements.get(j);
                    sortedPlacements.set(j, sortedPlacements.get(j + 1));
                    sortedPlacements.set(j + 1, temp);
                }
            }
        }
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

    public ArrayList<Integer> getSorted()
    {
        return sortedPlacements;
    }

    public void printLetterData()
    {
        System.out.printf("\n\n%13s : %c"
                        , "Letter", this.getLett());
        System.out.printf("\n%13s : ", "Order");
        for(int i = 0; i < 5; i++)
        {
            System.out.printf("%-4d", sortedPlacements.get(i));
        }
        System.out.printf("\n%13s : ", "Weight");
        for(int i = 0; i < 5; i ++)
        {
            System.out.printf("%-4d", weightedPlacements.get(i));
        }
        System.out.printf("\n%13s :", "Correct");
        if(correctPlacement.isEmpty())
        {
            System.out.print(" Empty");
        }
        {
            for(int i = 0; i < correctPlacement.size(); i ++)
            {
                System.out.print(" " + correctPlacement.get(i));
            }
        }
        System.out.printf("\n%13s :", "Not Attempted");
        if(notAttemptedPlacements.isEmpty())
        {
            System.out.print(" Empty");
        }
        for(int i = 0; i < notAttemptedPlacements.size(); i ++)
        {
            System.out.print(" " + notAttemptedPlacements.get(i));
        }
        System.out.printf("\n%13s : %d", "Minimum", minOcc);
        System.out.printf("\n%13s : %d", "Maximum", maxOcc);
    }
}
