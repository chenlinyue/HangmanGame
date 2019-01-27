import java.util.*;
import acm.program.ConsoleProgram;

public class Hangman extends ConsoleProgram {

    private HangmanCanvas canvas;

    public void run(){
        boolean play = true;
        while (play){
            game();
            while (true){
                char again = readLine("Play again? Y/N\n").charAt(0);
                if (again == 'N' || again == 'n'){
                    play = false;
                    break;
                }else if(again == 'Y' || again == 'y'){
                    break;
                }else {
                    println("Illegal Input");
                }
            }
        }
    }

    private void game(){
        canvas.reset();
        println("Welcome to Hangman!");

        HangmanLexicon word = new HangmanLexicon();
        // Get the word
        String wordText = word.getWord((int) (Math.random() * 10));
        String showcase = String.join("", Collections.nCopies(wordText.length(), "-"));

        // Start Guessing
        int leftChances = 8;
        String wrongGuess = "";
        char[] letter = wordText.toCharArray();
        int rightGuess = 0;
        while(leftChances > 0 && !showcase.equals(wordText)) {
            println("The word looks like this: " + showcase);
            canvas.displayWord(showcase);

            println("You have " + leftChances + " chances left.");

            // Get the letter guessed
            String reader = readLine("One character at a time. Your guess: ");
            char guess = Character.toUpperCase(reader.charAt(0));

            // Check if there is the letter
            String showchange = showcase;
            for (int i = 0; i < wordText.length(); i++){
                if (letter[i] == guess){
                    showcase = showcase.substring(0, i) + guess + showcase.substring(i + 1);
                }
            }
            if (showcase == showchange){
                println("There is no " + guess + "'s in the word");
                leftChances -= 1;
                wrongGuess = wrongGuess.concat(String.valueOf(guess));
                canvas.noteIncorrectGuess(wrongGuess);
            }else{
                println("That guess is correct.");
            }
        }
        if (leftChances == 0){
            println("You're completely hung.\nThe word was: " + wordText + ".\nYou lose.");
        }else{
            println("You guessed the word: " + wordText + "\nYou win.");
            canvas.displayWord(showcase);
        }
    }

    public void init(){
        canvas = new HangmanCanvas();
        add(canvas);
    }


}

