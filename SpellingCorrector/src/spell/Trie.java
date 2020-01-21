package spell;

import java.util.ArrayList;
import java.util.HashMap;

public class Trie implements ITrie {

	Node root = new Node(' ');

	@Override
	public void add(String word) {
		root.add(word);
	}

	@Override
	public INode find(String word) {
		Node found = root.find(word);
		return found;
	}

	public ArrayList<String> getPossibleWords(String word)
	{
		ArrayList<String> possible_words = new ArrayList<String>();
		for (int x=0;x<word.length();x++) {
			StringBuilder string = new StringBuilder(word);
			string.deleteCharAt(x);
			possible_words.add(string.toString());

			if (x+1<word.length()) {
				string = new StringBuilder(word);
				char temp = string.charAt(x);
				string.setCharAt(x, string.charAt(x + 1));
				string.setCharAt(x + 1, temp);
				possible_words.add(string.toString());
			}

			for (int letter=0;letter<26;letter++)
			{
				string = new StringBuilder(word);
				string.setCharAt(x, (char)('a'+letter));
				possible_words.add(string.toString());
			}

			for (int letter=0;letter<26;letter++)
			{
				string = new StringBuilder(word);
				string.insert(x, (char)('a'+letter));
				possible_words.add(string.toString());
			}
		}
		return possible_words;
	}

	public String getBestMatch(String word)
	{
		ArrayList<String> possible_words = getPossibleWords(word);
		if (possible_words.size() > 0) {
			for (String possible_word : possible_words)
			{
				if (find(possible_word) != null)
					return possible_word;
			}
		}
		else {
			for (String possible_word : possible_words) {
				possible_words.addAll(getPossibleWords(possible_word));
			}
			for (String possible_word : possible_words)
				if (find(possible_word) != null)
					return possible_word;
		}
		return null;
	}

	@Override
	public int getWordCount() {
		return root.getWordCount(false);
	}

	public int getWordCount(boolean full_search) {
		return root.getWordCount(full_search);
	}

	@Override
	public int getNodeCount() {
		return root.getNodeCount();
	}

	public String toString(){
		StringBuilder return_string = new StringBuilder();
		for (HashMap.Entry<Character, Node> node : root.node_list.entrySet()) {
			for (String entry : node.getValue().getWords("")) {
				return_string.append(entry).append("\n");
			}
		}
		return return_string.toString();
	}

	@Override
	public int hashCode(){
		return root.getHash(1);
	}

	@Override
	public boolean equals(Object obj) {
		// If the object is compared with itself then return true
		if (obj == this) {
			return true;
		}

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
		if (!(obj instanceof Trie)) {
			return false;
		}

		// typecast o to Complex so that we can compare data members
		Trie t = (Trie) obj;

		return (getWordCount(true) == t.root.getWordCount(true)) && getNodeCount() == t.getNodeCount();
	}

}
