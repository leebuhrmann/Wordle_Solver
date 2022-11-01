package classes;
import java.util.ArrayList;

public class Letter {

    private static final int WORD_SIZE = 5;

    private int minOcc;
    private int maxOcc;         //if 0, this letter is not in the Game's answer.
    private char lett;
    private ArrayList<Integer> correctPosition;
    private ArrayList<Integer> notAttemptedPlacements;
    /*
     * The number of occurences of this letter at a specific position. That position is the value
     * found at the corresponding index of sortedPlacements list.
     */
    private ArrayList<Integer> weightedPlacements;
    /*
     * The most common position of this letter determined by its weighted values, ordered by most common to least common.
     */
    private ArrayList<Integer> sortedPlacements;
    
    /**
     * Creates a Letter that represents a single character of the alphabet and its
     * agrigate game data within a Game.
     * 
     * @param   lettIn    The letter of the alphabet this Object represents.
     * @param   weightsIn   The positional weight data of this letter in a Game.
     */
    public Letter(char lettIn, int[] weightsIn)
    {
        lett = Character.toUpperCase(lettIn);
        minOcc = 0;
        maxOcc = WORD_SIZE;
        correctPosition = new ArrayList<Integer>();
        notAttemptedPlacements = new ArrayList<Integer>();
        for(int i = 0; i < WORD_SIZE; i++)
        {
            notAttemptedPlacements.add(i);
        }

        weightedPlacements = new ArrayList<Integer>();
        for(int i = 0; i < WORD_SIZE; i++)
        {
            weightedPlacements.add(weightsIn[i]);
        }
        sortedPlacements = new ArrayList<Integer>();
        for(int i = 0; i < WORD_SIZE; i++)
        {
            sortedPlacements.add(i);
        }
        orderPlacements();
    }

    /**
     * Uses bubble sort to order the positional weight data according to
     * this letters most common position to least common position.
     */
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

    /**
     * Sets the minimum number of occurences of this Letter in a Game.
     * 
     * @param   minOccIn    The minuimum number of occurrences to be set.
     */
    public void setMinOcc(int minOccIn)
    {
        minOcc = minOccIn;
    }

    /**
     * Returns the minimum number of occurrences of this Letter in a Game.
     * 
     * @return  the minimum number of occurrences of this Letter in a Game.
     */
    public int getMinOcc()
    {
        return minOcc;
    }

    /**
     * Sets the maximum number of occurrences of this Letter in a Game.
     * 
     * @param   occIn   the maximum number of occurrences of this Letter in a Game.
     */
    public void setMaxOcc(int occIn)
    {
        maxOcc = occIn;
    }

    /**
     * Returns the maximum number of occurrences of this Letter in a Game.
     * 
     * @return  the maximum number of occurrences of this Letter in a Game.
     */
    public int getMaxOcc()
    {
        return maxOcc;
    }

    /**
     * Returns the character that represents this Letter.
     * 
     * @return  the character that represents this Letter.
     */
    public char getLett()
    {
        return lett;
    }

    /**
     * Adds the passed position to a list of correct positions.
     * 
     * @param position A correct position to be added.
     */
    public void addCorrect(int position)
    {
        correctPosition.add(position);
    }

    /**
     * Returns the list of correct positions.
     * 
     * @return  the list of correct positions.
     */
    public ArrayList<Integer> getCorrect()
    {
        return correctPosition;
    }

    /**
     * 
     * @param placement
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

    /*
     * Prints the data of this Letter
     */
    public void printLetterData()
    {
        System.out.printf("\n\n%13s : %c"
                        , "Letter", this.getLett());
        System.out.printf("\n%13s : ", "Order");
        for(int i = 0; i < WORD_SIZE; i++)
        {
            System.out.printf("%-4d", sortedPlacements.get(i));
        }
        System.out.printf("\n%13s : ", "Weight");
        for(int i = 0; i < WORD_SIZE; i ++)
        {
            System.out.printf("%-4d", weightedPlacements.get(i));
        }
        System.out.printf("\n%13s :", "Correct");
        if(correctPosition.isEmpty())
        {
            System.out.print(" Empty");
        }
        {
            for(int i = 0; i < correctPosition.size(); i ++)
            {
                System.out.print(" " + correctPosition.get(i));
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
