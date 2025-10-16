import java.util.ArrayList;

public class TST {

    private TSTNode dictionaryRoot;
    private TSTNode misspelledRoot;

    public String[] runTST(String[] text, String[] dictionary) {

        // Dictionary TST
        for (String word : dictionary) {
            dictionaryRoot = insert(dictionaryRoot, word, 0);
        }

        // Ordered list of misspelled words
        ArrayList<String> misspelledWords = new ArrayList<>();

        for (String word : text) {

            // If not in dictionary
            if (!lookup(dictionaryRoot, word, 0)) {

                // If not in misspelled words already
                if (!lookup(misspelledRoot, word, 0)) {

                    // Add it
                    misspelledRoot = insert(misspelledRoot, word, 0);
                    misspelledWords.add(word);
                }
            }
        }

        // Return misspelled words list
        System.out.println(misspelledWords);
        return misspelledWords.toArray(new String[0]);
    }

    private TSTNode insert(TSTNode node, String word, int depth) {
        char currentChar = word.charAt(depth);

        // Create new node if none
        if (node == null) {
            node = new TSTNode(currentChar);
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
    private boolean lookup(TSTNode node, String word, int depth) {

        // Reached a null node, no word
        if (node == null) return false;

        // Character in the word at a depth
        char currentChar = word.charAt(depth);

        // Less = Left, More = Right, Otherwise go straight down
        if (currentChar < node.character) {
            return lookup(node.left, word, depth);
        } else if (currentChar > node.character) {
            return lookup(node.right, word, depth);
        } else {

            // If not at the end, go middle and check next character
            if (depth < word.length() - 1) {
                return lookup(node.mid, word, depth + 1);
            } else {

                // Found last char
                return node.isEndOfWord;
            }
        }
    }

}
