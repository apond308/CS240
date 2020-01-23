package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.lang.Math.pow;

public class EvilHangmanGame implements IEvilHangmanGame{

    SortedSet<Character> previous_guesses;
    HashSet<String> dictionary;
    private int word_length;

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
        this.word_length = wordLength;

        if (this.dictionary.size() == 0)
            throw new EmptyDictionaryException();
    }

    @Override
    public Set<String> makeGuess(char guess) throws GuessAlreadyMadeException {
        guess = Character.toLowerCase(guess);
        if (previous_guesses.contains(guess))
            throw new GuessAlreadyMadeException();
        previous_guesses.add(guess);

        HashMap<Integer, ArrayList<String>> word_groups = new HashMap<>();
        for (int x=0;x<pow(2, word_length);x++)
        {
            for (String word : dictionary) {
                boolean valid = true;
                for (int pos = 0; pos < word_length && valid; pos++) {
                    if ((x >> pos & 0x1) != 0) {
                        if (word.charAt(word_length-pos-1) != guess)
                            valid = false;
                    }
                    else if (word.charAt(word_length-pos-1) == guess)
                        valid = false;
                }
                if (valid)
                {
                    if (!word_groups.containsKey(x))
                        word_groups.put(x, new ArrayList<String>());
                    word_groups.get(x).add(word);
                }
            }
        }

        int worst_group = 0;
        int max_size = 0;
        for (HashMap.Entry<Integer, ArrayList<String>> word_group : word_groups.entrySet()) {
            if (word_group.getValue().size() > max_size || (word_group.getValue().size() == max_size && word_group.getKey() < worst_group)) {
                worst_group = word_group.getKey();
                max_size = word_group.getValue().size();
            }
        }

        dictionary = new HashSet<>(word_groups.get(worst_group));
        return dictionary;
    }

    @Override
    public SortedSet<Character> getGuessedLetters() {
        return null;
    }
}
