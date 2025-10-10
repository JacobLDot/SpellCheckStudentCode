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

    // Checks spelling of words in a given text
    public String[] checkWords(String[] text, String[] dictionary) {

//        // Binary Search
//        BinarySearch binarySearch = new BinarySearch();
//        return binarySearch.runBinarySearch(text, dictionary);

//        // Trie
//        Trie trie = new Trie();
//        return trie.runTrie(text, dictionary);

        // TST
        TST tst = new TST();
        return tst.runTST(text, dictionary);

    }
}