package spell;

import java.util.HashMap;

public class Trie implements ITrie {

    Node root = new Node();

    @Override
    public void add(String word) {
        root.add(word);
    }

    @Override
    public INode find(String word) {
        Node found = root.find(word);
        return found;
    }

    @Override
    public int getWordCount() {
        return root.getWordCount();
    }

    @Override
    public int getNodeCount() {
        return root.getNodeCount();
    }

    public String toString(){
        return "";
    }

    @Override
    public int hashCode(){

        return 0;
    }

    @Override
    public boolean equals(Object obj) {


        return false;
    }

}
