package hangman;

import org.junit.platform.commons.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class EvilHangman {

    public static void main(String[] args) {
        String dictionary = args[0];
        int wordLength = Integer.parseInt(args[1]);
        int guesses = Integer.parseInt(args[2]);

        if (wordLength < 2 || guesses < 1){
            System.out.println("Invalid word length or guesses.");
            return;
        }

        File dictionary_file = new File(dictionary);

        EvilHangmanGame game = new EvilHangmanGame();
        try {
            game.startGame(dictionary_file, wordLength);
        } catch (EmptyDictionaryException e) {
            System.out.println("Word length not valid for selected dictionary.");
            e.printStackTrace();
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> dict = new HashSet<>();

        Scanner user_input = new Scanner(System.in);
        while(guesses > 0)
        {
            String old_word = game.current_word;
            System.out.println("You have " + Integer.toString(guesses) + " guesses left");
            System.out.print("List of guesses: ");
            for (char letter : game.previous_guesses){
                System.out.print(letter + " ");
            }
            System.out.println("\nCurrent word: " + game.current_word);

            System.out.print("Enter letter: ");
            String input = user_input.next();

            while (input.length() > 1 || input.charAt(0) < 'A' || input.charAt(0) > 'z')
            {
                System.out.println("Invalid input. Try again:");
                input = user_input.next();
            }

            try {
                dict = game.makeGuess(input.charAt(0));
            } catch (GuessAlreadyMadeException e) {
                System.out.println("You already used that letter.");
                input = user_input.next();
                while (input.length() > 1 || input.charAt(0) < 'A' || input.charAt(0) > 'z')
                {
                    System.out.println("Invalid input. Try again:");
                    input = user_input.next();
                }
            }

            if (game.current_word.equals(old_word)) {
                System.out.println("Sorry, there are no " + input.charAt(0) + "'s");
                guesses--;
            }
            else {
                int count = 0;
                for (int x=0;x<game.current_word.length();x++){
                    if (game.current_word.charAt(x) == input.charAt(0))
                        count++;
                }
                System.out.println("Yes, there is " + count + " " + input.charAt(0) + "'s");
            }
            System.out.println();


            if (!game.current_word.contains("-"))
                break;

        }

        if (game.current_word.contains("-")){
            System.out.println("Game failed.");
        }
        else
        {
            System.out.println("You win!");
        }
        System.out.println("The word was: " + dict.iterator().next());




    }

}
