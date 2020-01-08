package spell;

import java.util.HashMap;
import java.util.Map;

public class Node implements INode {

    public int count = 0;

    public HashMap<Character, Node> node_list = new HashMap<>();

    public void add(String string_in)
    {
        if (string_in.length() == 0)
        {
            count++;
        }
        else
        {
            if (!node_list.containsKey(string_in.charAt(0)))
                node_list.put(string_in.charAt(0), new Node());
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

    public int getWordCount()
    {
        int word_count = count > 0 ? 1 : 0;
        for (HashMap.Entry<Character, Node> entry : node_list.entrySet())
        {
            word_count += entry.getValue().getWordCount();
        }
        return word_count;
    }

    @Override
    public int getValue() {
        return 0;
    }

}
