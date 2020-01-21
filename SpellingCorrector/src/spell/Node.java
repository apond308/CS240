package spell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Node implements INode {

    public int count = 0;
    public char key;

    public HashMap<Character, Node> node_list = new HashMap<>();

    public Node(char key_in)
    {
        key = key_in;
    }

    public void add(String string_in)
    {
        if (string_in.length() == 0)
        {
            count++;
        }
        else
        {
            if (!node_list.containsKey(string_in.charAt(0)))
                node_list.put(string_in.charAt(0), new Node(string_in.charAt(0)));
            node_list.get(string_in.charAt(0)).add(string_in.substring(1));
        }
    }

    public Node find(String string_in)
    {
        if (string_in.length() == 0)
        {
            if (count > 0)
                return this;
            else
                return null;
        }
        else
        {
            if (!node_list.containsKey(string_in.charAt(0)))
                return null;
            return node_list.get(string_in.charAt(0)).find(string_in.substring(1));
        }
    }

    public int getNodeCount()
    {
        int node_count = 1;
        for (HashMap.Entry<Character, Node> entry : node_list.entrySet())
        {
            node_count += entry.getValue().getNodeCount();
        }
        return node_count;
    }

    public int getWordCount(Boolean full_search)
    {
        int word_count;
        if (full_search)
            word_count = count;
        else
            word_count = count > 0 ? 1 : 0;
        for (HashMap.Entry<Character, Node> entry : node_list.entrySet())
        {
            word_count += entry.getValue().getWordCount(full_search);
        }
        return word_count;
    }

    public ArrayList<String> getWords(String word)
    {
        ArrayList<String> new_words = new ArrayList<String>();
        if (count > 0 && !word.equals(""))
            new_words.add(word + key);
        for (HashMap.Entry<Character, Node> entry : node_list.entrySet())
        {
            new_words.addAll(entry.getValue().getWords(word+key));
        }
        return new_words;
    }

    public int getHash(int depth) {
        int hash = count>0 ? key*13^depth : 1;
        for (HashMap.Entry<Character, Node> entry : node_list.entrySet())
        {
            hash += entry.getValue().getHash(depth+1);
        }

        return hash;
    }

    @Override
    public int getValue() {
        return 0;
    }
}
