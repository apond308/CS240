package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class EvilHangmanGame implements IEvilHangmanGame{

    SortedSet<Character> previous_guesses;
    HashSet<String> dictionary;
    private int GuessAlreadyMadeException;

    @Override
    public void startGame(File dictionary, int wordLength) throws IOException, EmptyDictionaryException {
        BufferedReader reader = new BufferedReader(new FileReader(dictionary));
        String[] input_file = Files.readString(Paths.get(dictionary.getPath())).split("\\s+");
        previous_guesses = new TreeSet<>();
        this.dictionary = new HashSet<String>();

        for (String word : input_file)
        {
            if (word.length() == wordLength)
                this.dictionary.add(word);
        }
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        if (previous_guesses.contains(guess))
            throw new GuessAlreadyMadeException();
        previous_guesses.add(guess);

        ArrayList<HashMap<Integer, String>> word_groups;
        for (String element : dictionary)
        {
//            word_groups.add()
        }

        return null;
    }

    @Override
    public SortedSet<Character> getGuessedLetters() {
        return null;
    }
}
