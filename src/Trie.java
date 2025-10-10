import java.util.ArrayList;

// Store all dictionary words
public class Trie {
    TrieNode root = new TrieNode();

    public String[] runTrie(String[] text, String[] dictionary) {

        // Insert dictionary words into trie
        for (String word : dictionary) {
            this.insert(word);
        }

        ArrayList<String> misspelledWords = new ArrayList<>();
        Trie misspelledTrie = new Trie();

        // Check misspelled words
        for (String word : text) {
            if (!this.lookup(word)) {
                if (!misspelledTrie.lookup(word)) {
                    misspelledTrie.insert(word);
                    misspelledWords.add(word);
                }
            }
        }

        // Return misspelled words
        System.out.println(misspelledWords);
        return misspelledWords.toArray(new String[0]);
    }

    // Insert a word into the Trie
    public void insert(String s) {
        TrieNode current = root;
        for (char c : s.toCharArray()) {
            int index = c;
            if (index < 0 || index >= 256) {
                continue;
            }

            // If no child node, create one
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }

            // Move to next node
            current = current.children[index];
        }

        // Set the end node as a valid word
        current.setWord();
    }

    // Check if word exists in the Trie
    public boolean lookup(String s) {
        TrieNode current = root;
        for (char c : s.toCharArray()) {
            int index = c;
            if (index < 0 || index >= 256) {

                // Invalid character / not in dictionary
                return false;
            }

            // Path doesn't exist / not in dictionary
            if (current.children[index] == null) {
                return false;
            }

            // Move down the Trie
            current = current.children[index];
        }

        return current.isWord();
    }
}