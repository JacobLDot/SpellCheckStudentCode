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

    // Checks spelling of words in a given text
    public String[] checkWords(String[] text, String[] dictionary) {

        // 2 Arrays: Ordered and Sorted
        ArrayList<String> misspelledWords = new ArrayList<>();
        ArrayList<String> sortedMisspelledWords = new ArrayList<>();

        // Go through each word in the text
        for (String word : text) {

            // Binary Search - Word is not in the dictionary
            if (Arrays.binarySearch(dictionary, word) < 0) {

                // Binary Search - Word is not in misspelled words
                int index = Collections.binarySearch(sortedMisspelledWords, word);
                if (index < 0) {

                    // Add to Ordered and Sorted
                    misspelledWords.add(word);
                    sortedMisspelledWords.add(-index - 1, word);
                }
            }
        }

        // Return the misspelled words
        System.out.println(misspelledWords);
        return misspelledWords.toArray(new String[0]);
    }
}