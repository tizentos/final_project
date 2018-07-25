package ltd.boku.joketeller;

import java.util.ArrayList;
import java.util.List;

public class JokeTeller {

    private List<String> jokes;
    private   StringBuilder jokeBuilder;

    public JokeTeller(){
        jokes=new ArrayList<>();
    }
    public  List<String> getJokes(int numOfJokes){
        buildJoke(numOfJokes);
        return jokes;
    }

    private  void buildJoke(int numOfJokes){
        int possibleCombination= JokeSubString.values().length;
        for(int i=0; i<numOfJokes; i++) {
            jokeBuilder=new StringBuilder();
            int randomValue= (int) (Math.random() * possibleCombination);
            jokeBuilder.append(JokeSubString.values()[randomValue]);
            jokeBuilder.append(" ");
            randomValue= (int) (Math.random() * possibleCombination);
            jokeBuilder.append(JokeSubString.values()[randomValue]);
            jokeBuilder.append(" ");
            randomValue= (int) (Math.random() * possibleCombination);
            jokeBuilder.append(JokeSubString.values()[randomValue]);
            jokes.add(jokeBuilder.toString());
            jokeBuilder.delete(0,jokeBuilder.length()-1);
        }
    }
}

enum JokeSubString{
    LOLLY,
    LILLY,
    MONKEY,
    BILLY,
    BALLY,
    MOLLY,
    MILLY
}