// Node in the Trie
public class TrieNode {

    // Array for the child nodes of each node (27 children)
    TrieNode[] children;

    // Indicating end of a string
    boolean isEndOfWord;

    // Constructor
    public TrieNode() {
        isEndOfWord = false;
        children = new TrieNode[256];
    }

    // Location marks the end of a word
    public boolean isWord() {
        return isEndOfWord;
    }

    // Set letter as a word
    public void setWord() {
        isEndOfWord = true;
    }

    // Get next letter
    public TrieNode[] getNext() {
        return children;
    }
}