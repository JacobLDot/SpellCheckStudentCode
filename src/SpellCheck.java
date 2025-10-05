import java.util.*;

/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: [Jacob Lowe]
 * */

public class SpellCheck {

    // Node in the Trie
    public class TrieNode {

        // Array for the child nodes of each node (27 children)
        TrieNode[] children;

        // Indicating end of a string
        boolean isEndOfWord;

        // Constructor
        public TrieNode() {
            isEndOfWord = false;
            children = new TrieNode[27];
        }

        public boolean isWord() {
            return isEndOfWord;
        }

        public void setWord() {
            isEndOfWord = true;
        }

        public TrieNode[] getNext() {
            return children;
        }
    }

    // Store all dictionary words
    public class Trie {
        TrieNode root = new TrieNode();

        // Insert a word into the Trie
        public void insert(String s) {
            TrieNode current = root;
            for (char c : s.toCharArray()) {
                int index;
                if (c == '\'') {

                    // Apostrophe is stored at index 26
                    index = 26;
                } else {

                    // Map index
                    index = c - 'a';
                }
                if (index < 0 || index >= 27) {

                    // Skip characters not from a-z & apostrophe
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
                int index;
                if (c == '\'') {
                    index = 26;
                } else {
                    index = c - 'a';
                }
                if (index < 0 || index >= 27) {

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

    // Checks spelling of words in a given text
    public String[] checkWords(String[] text, String[] dictionary) {
        Trie trie = new Trie();

        // Insert dictionary words into trie
        for (String word : dictionary) {
            trie.insert(word);
        }

        ArrayList<String> misspelledWords = new ArrayList<>();

        // Check misspelled words
        for (String word : text) {
            if (!trie.lookup(word)) {
                if (!misspelledWords.contains(word)) {
                    misspelledWords.add(word);
                }
            }
        }

        // Return misspelled words
        System.out.println(misspelledWords);
        return misspelledWords.toArray(new String[0]);


//        // Binary Search
//        // 2 Arrays: Ordered and Sorted
//        ArrayList<String> misspelledWords = new ArrayList<>();
//        ArrayList<String> sortedMisspelledWords = new ArrayList<>();
//
//        // Go through each word in the text
//        for (String word : text) {
//
//            // Binary Search - Word is not in the dictionary
//            if (Arrays.binarySearch(dictionary, word) < 0) {
//
//                // Binary Search - Word is not in misspelled words
//                int index = Collections.binarySearch(sortedMisspelledWords, word);
//                if (index < 0) {
//
//                    // Add to Ordered and Sorted
//                    misspelledWords.add(word);
//                    sortedMisspelledWords.add(-index - 1, word);
//                }
//            }
//        }
//
//        // Return the misspelled words
//        System.out.println(misspelledWords);
//        return misspelledWords.toArray(new String[0]);
    }
}