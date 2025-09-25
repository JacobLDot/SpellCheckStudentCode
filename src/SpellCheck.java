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

//        // Create a hashset containing every word in the dictionary
//        HashSet<String> dictionaryWords = new HashSet<>();
//
//        // Create an ordered hashset of every misspelled words in the text
//        LinkedHashSet<String> misspelledWords = new LinkedHashSet<>();
//
//        // For each word in the dictionary, add it to dictionaryWords
//        dictionaryWords.addAll(Arrays.asList(dictionary));
//
//        // For each word in the text if it's not in the list of words
//        // in the dictionary, then add it to misspelled words
//        for (String word : text) {
//            if (!dictionaryWords.contains(word)) {
//                misspelledWords.add(word);
//            }
//        }
//
//        // Return the array form of the misspelled words
//        System.out.println(misspelledWords);
//        return misspelledWords.toArray(new String[0]);

        // Binary Search
        LinkedList<String> misspelledWords = new LinkedList<>();

        // Go through each word in the text
        for (String word : text) {

            // Reset boundaries for binary search
            int low = 0;
            int high = dictionary.length - 1;
            boolean found = false;

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
            if (!found) {
                misspelledWords.add(word);
            }
        }

        // Return the misspelled words
        System.out.println(misspelledWords);
        return misspelledWords.toArray(new String[0]);
    }
}
