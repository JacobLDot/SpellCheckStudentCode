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
        LinkedList<String> misspelledWords = new LinkedList<>();

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

            // If the word isn't found in the dictionary, add it to the list of misspelled words
            if (!found & !misspelledWords.contains(word)) {
                misspelledWords.add(word);
            }
            for (String misspelledWord : misspelledWords) {
                int low2 = 0;
                int high2 = misspelledWords.size() - 1;
                boolean found2 = false;
                while (low2 < high2) {
                    int mid2 = low2 + (high2 - low2) / 2;
                    String midword2 = misspelledWords.get(mid2);
                    int comparison2 = midword2.compareTo(misspelledWord);
                    if (comparison2 == 0) {
                        found2 = true;
                        break;
                    }
                    else if (comparison2 < 0) {
                        low2 = mid2 + 1;
                    }
                    else {
                        high2 = mid2 - 1;
                    }
                }
                if (found2 = true) {
                    misspelledWords.remove(misspelledWord);
                }
            }
        }

        // Return the misspelled words
        System.out.println(misspelledWords);
        return misspelledWords.toArray(new String[0]);
    }
}
