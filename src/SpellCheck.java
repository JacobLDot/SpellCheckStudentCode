import java.lang.reflect.Array;
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

        // Binary Search
        ArrayList<String> misspelledWords = new ArrayList<>();
        ArrayList<String> sortedMisspelledWords = new ArrayList<>();

        // Go through each word in the text
        for (String word : text) {

            // Reset boundaries for binary search
            int low = 0;
            int high = dictionary.length - 1;
            boolean found = false;

            // While the word range is not 0
            while (low <= high) {

                // Find the middle index of the range
                int mid = low + (high - low) / 2;

                // Find the word at the middle index
                String midWord = dictionary[mid];

                // Compare dictionary word to selected word from text
                int comparison = midWord.compareTo(word);

                // Word found in dictionary
                if (comparison == 0) {
                    found = true;
                    break;
                }

                // Midpoint comes alphabetically before the word, so restrict the lower half
                else if (comparison < 0) {
                    low = mid + 1;
                }

                // Midpoint comes alphabetically after the word, so restrict the higher half
                else {
                    high = mid - 1;
                }
            }

            // Binary search to duplicate check
            if (!found) {
                int index = Collections.binarySearch(sortedMisspelledWords, word);

                // Not found
                if (index < 0) {
                    misspelledWords.add(word);
                    sortedMisspelledWords.add(-index - 1, word);
                }

                // Otherwise it is already present
            }
        }

        // Return the misspelled words
        System.out.println(misspelledWords);
        return misspelledWords.toArray(new String[0]);
    }
}