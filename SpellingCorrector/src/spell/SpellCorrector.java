package spell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SpellCorrector implements ISpellCorrector{

    Trie dictionary = new Trie();

    @Override
    public void useDictionary(String dictionaryFileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dictionaryFileName));
        String[] input_file = Files.readString(Paths.get(dictionaryFileName)).split("\\s+");
        for (String word : input_file)
        {
            dictionary.add(word);
        }
    }

    @Override
    public String suggestSimilarWord(String inputWord) {
        if (dictionary.find(inputWord) != null)
            return inputWord;
        else
            return dictionary.getBestMatch(inputWord);
    }
}
