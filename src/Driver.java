import java.io.File;
import java.io.FileNotFoundException;

public class Driver 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Guesser guesser = new Guesser(new File("src/Wordle_Answers.txt")
                                    , new File("src/Weighted_Wordle_List.txt")
                                    , "Stall", "Stalk");

        //guesser.printData();
        //guesser.printDictionary(); 
        guesser.score();
        guesser.printScore();
        
    }
}
