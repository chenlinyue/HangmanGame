import acm.util.*;
import java.io.*;

import java.util.*;

public class HangmanLexicon {

    List<String> array = new ArrayList<String>();

    public HangmanLexicon(){
        // First two steps


        try {
            BufferedReader br = new BufferedReader(new FileReader("HangmanLexicon.txt"));

            while (true){
                String word = br.readLine();
                if (word == null) break;
                array.add(word);
            }
        }catch (IOException e){
            throw new ErrorException(e);
        }
    }

    public int getWordCount(){
        return array.size();
    }

    public String getWord(int index){
        return array.get(index);
    }
}
