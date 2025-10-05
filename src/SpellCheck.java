import java.util.ArrayList;

/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: [Jacob Lowe]
 * */

public class SpellCheck {

//    // Node in the Trie
//    public class TrieNode {
//
//        // Array for the child nodes of each node (27 children)
//        TrieNode[] children;
//
//        // Indicating end of a string
//        boolean isEndOfWord;
//
//        // Constructor
//        public TrieNode() {
//            isEndOfWord = false;
//            children = new TrieNode[27];
//        }
//
//        public boolean isWord() {
//            return isEndOfWord;
//        }
//
//        public void setWord() {
//            isEndOfWord = true;
//        }
//
//        public TrieNode[] getNext() {
//            return children;
//        }
//    }
//
//    // Store all dictionary words
//    public class Trie {
//        TrieNode root = new TrieNode();
//
//        // Insert a word into the Trie
//        public void insert(String s) {
//            TrieNode current = root;
//            for (char c : s.toCharArray()) {
//                int index;
//                if (c == '\'') {
//
//                    // Apostrophe is stored at index 26
//                    index = 26;
//                } else {
//
//                    // Map index
//                    index = c - 'a';
//                }
//                if (index < 0 || index >= 27) {
//
//                    // Skip characters not from a-z & apostrophe
//                    continue;
//                }
//
//                // If no child node, create one
//                if (current.children[index] == null) {
//                    current.children[index] = new TrieNode();
//                }
//
//                // Move to next node
//                current = current.children[index];
//            }
//
//            // Set the end node as a valid word
//            current.setWord();
//        }
//
//        // Check if word exists in the Trie
//        public boolean lookup(String s) {
//            TrieNode current = root;
//            for (char c : s.toCharArray()) {
//                int index;
//                if (c == '\'') {
//                    index = 26;
//                } else {
//                    index = c - 'a';
//                }
//                if (index < 0 || index >= 27) {
//
//                    // Invalid character / not in dictionary
//                    return false;
//                }
//
//                // Path doesn't exist / not in dictionary
//                if (current.children[index] == null) {
//                    return false;
//                }
//
//                // Move down the Trie
//                current = current.children[index];
//            }
//
//            return current.isWord();
//        }
//    }


    // Node in the TST
    private class TSTNode {
        char character;
        TSTNode left, mid, right;
        boolean isEndOfWord;
    }

    // Set a root of the TST
    private TSTNode dictionaryRoot;
    private TSTNode misspelledRoot;

    // Insert a word into the TST
    public void dictionaryInsert(String word) {
        dictionaryRoot = insert(dictionaryRoot, word, 0);
    }

    public void misspelledInsert(String word) {
        misspelledRoot = insert(misspelledRoot, word, 0);
    }


    private TSTNode insert(TSTNode node, String word, int depth) {
        char currentChar = word.charAt(depth);

        // Create new node if none
        if (node == null) {
            node = new TSTNode();
            node.character = currentChar;
        }

        // Compare current char to node's char
        if (currentChar < node.character) {
            node.left = insert(node.left, word, depth);
        } else if (currentChar > node.character) {
            node.right = insert(node.right, word, depth);
        } else {

            // If not at the end of the word, go straight down
            if (depth < word.length() - 1) {
                node.mid = insert(node.mid, word, depth + 1);
            } else {

                // Mark the node as the end of the word
                node.isEndOfWord = true;
            }
        }
        return node;
    }

    // Look up a word in the TST
    public boolean dictionaryLookup(String word) {

        // Start at the root and try to find the node from the last character of the word
        TSTNode node = get(dictionaryRoot, word, 0);

        // We have found the node & is the end of the word
        return node != null && node.isEndOfWord;
    }

    public boolean misspelledLookup(String word) {

        // Start at the root and try to find the node from the last character of the word
        TSTNode node = get(misspelledRoot, word, 0);

        // We have found the node & is the end of the word
        return node != null && node.isEndOfWord;
    }

    private TSTNode get(TSTNode node, String word, int depth) {

        // Reached a null node, no word
        if (node == null) return null;

        // Character in the word at a depth
        char currentChar = word.charAt(depth);

        if (currentChar < node.character) {
            return get(node.left, word, depth);
        } else if (currentChar > node.character) {
            return get(node.right, word, depth);
        } else {

            // If not at the end, go middle and check next character
            if (depth < word.length() - 1) {
                return get(node.mid, word, depth + 1);
            } else {

                // Found last char
                return node;
            }
        }
    }

    // Checks spelling of words in a given text
    public String[] checkWords(String[] text, String[] dictionary) {

        // TST
        for (String word : dictionary) {
            dictionaryInsert(word);
        }

        misspelledRoot = null;

        ArrayList<String> misspelledWords = new ArrayList<>();

        for (String word : text) {
            if (!dictionaryLookup(word)) {
                if (!misspelledLookup(word)) {
                    misspelledInsert(word);
                    misspelledWords.add(word);
                }
            }
        }

        System.out.println(misspelledWords);
        return misspelledWords.toArray(new String[0]);


//        // Trie
//        Trie trie = new Trie();
//
//        // Insert dictionary words into trie
//        for (String word : dictionary) {
//            trie.insert(word);
//        }
//
//        ArrayList<String> misspelledWords = new ArrayList<>();
//
//        // Check misspelled words
//        for (String word : text) {
//            if (!trie.lookup(word)) {
//                if (!misspelledWords.contains(word)) {
//                    misspelledWords.add(word);
//                }
//            }
//        }
//
//        // Return misspelled words
//        System.out.println(misspelledWords);
//        return misspelledWords.toArray(new String[0]);


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