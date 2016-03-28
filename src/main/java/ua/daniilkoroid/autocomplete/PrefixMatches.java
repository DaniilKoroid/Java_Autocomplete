/**
 *
 */
package ua.daniilkoroid.autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import ua.daniilkoroid.autocomplete.trie.RWayTrie;
import ua.daniilkoroid.autocomplete.trie.Trie;
import ua.daniilkoroid.autocomplete.trie.Tuple;

/**
 * Class that provides such functionality as as auto-completing for the first
 * two letters entered.
 *
 * @author Daniil_Koroid
 *
 */
public class PrefixMatches {

    /**
     * Default length of search.
     */
    private final int DEFAULT_SEARCH_LENGTH = 3;

    /**
     * Minimal length of string that can be added to in-memory dictionary.
     */
    private final int MINIMAL_STRING_TO_ADD_LENGTH = 2;

    /**
     * Trie that is used to store words.
     */
    private final Trie trie;

    /**
     * The number of times this structure was modified.
     */
    private int modCount;
    
    /**
     * Create object with default in-memory dictionary which is {@link RWayTrie}
     * .
     */
    public PrefixMatches() {
        trie = new RWayTrie();
    }

    /**
     * Create object with given in-memory dictionary.
     *
     * @param trie in-memory dictionary to be used
     */
    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    /**
     * Add given strings to in-memory dictionary.
     * <p>
     * If there are sentences - they will be divided into words. Only words that
     * are longer than {@link #MINIMAL_STRING_TO_ADD_LENGTH} will be added.
     * </p>
     *
     * @param strings strings to be stored in in-memory dictionary
     * @return number of strings that were added
     */
    public int add(String... strings) {
        String[] filteredStrings = filterInputStrings(strings);
        int beforeAddSize = size();
        for (String string : filteredStrings) {
            updateModCount();
            trie.add(new Tuple(string));
        }
        int afterAddSize = size();
        int addedElements = afterAddSize - beforeAddSize;
        return addedElements;
    }

    /**
     * Checks if given word is contained.
     *
     * @param word word to check for being contained
     * @return <code>true</code> if word is contained. Otherwise -
     * <code>false</code>
     */
    public boolean contains(String word) {
        return isFiltered(word) ? trie.contains(word) : false;
    }

    /**
     * Deletes given word if such is contained.
     * <p>
     * If given word is present - deletes it and returns <code>true</code>. If
     * given word is not present - does nothing and returns <code>false</code>.
     * </p>
     *
     * @param word word to delete
     * @return <code>true</code> if word is contained and was deleted. Otherwise
     * - <code>false</code>
     */
    public boolean delete(String word) {
        boolean result = false;
        if(isFiltered(word)) {
            result = trie.delete(word);
            if(result) {
                updateModCount();
            }
        }
        return result;
    }

    /**
     * Get stored words count.
     *
     * @return stored words count
     */
    public int size() {
        return trie.size();
    }

    /**
     * Iterable over all words that start from given prefix and have different
     * lengths count that is not greater than given parameter k.
     * <p>
     * If no such words are found - {@link Collections#emptyList()} is returned.
     * </p>
     *
     * @param pref prefix for all words to find
     * @param k maximum differ lengths count
     * @return iterable over all words that start from given prefix and
     * different lengths count that is not greater than given parameter k. If no
     * such words are found - {@link Collections#emptyList()} is returned
     */
    public Iterable<String> wordsWithPrefix(String pref, int k) {
        return new PrefixMatchesIterable(pref, k);
    }

    /**
     * Iterator over all words that start from given prefix and have length that
     * is not longer than given prefix size and
     * {@link PrefixMatches#DEFAULT_SEARCH_LENGTH}.
     *
     * @param pref prefix for all words to find
     * @return iterator over all words that start from given prefix and have
     * length that is not longer than given prefix size and given parameter
     * {@link PrefixMatches#DEFAULT_SEARCH_LENGTH}.
     */
    public Iterable<String> wordsWithPrefix(String pref) {
        return wordsWithPrefix(pref, DEFAULT_SEARCH_LENGTH);
    }

    /**
     * Filters given strings for being sentences.
     * <p>
     * If given string is a sentence - splits it by space. Returns array of
     * single words. Only words on which
     * {@link #isLongerThanMinimalRequiredLength(java.lang.String)} returns true
     * added.
     * </p>
     *
     * @param strings strings to filter
     * @return array of words that were splited by space from given strings
     */
    private String[] filterInputStrings(String... strings) {
        List<String> result = new ArrayList<>();
        String space = " ";
        for (String string : strings) {
            String[] splited = string.split(space);
            for (String splitedString : splited) {
                if (isFiltered(splitedString)) {
                    result.add(splitedString);
                }
            }
        }
        String[] resultArray = result.toArray(new String[result.size()]);
        return resultArray;
    }

    /**
     * Checks if given word has bigger length than minimal required
     * ({@link #MINIMAL_STRING_TO_ADD_LENGTH}).
     * <p>
     * If null is given - returns false.
     * </p>
     *
     * @param word word to check length
     * @return <code>true</code> if given word has bigger length than
     * {@link #MINIMAL_STRING_TO_ADD_LENGTH}. Otherwise - <code>false</code>
     */
    private boolean isLongerThanMinimalRequiredLength(String word) {
        return word == null ? false : word.length() > MINIMAL_STRING_TO_ADD_LENGTH;
    }

    /**
     * Check if given word passes filter to be operated with in-memory
     * dictionary.
     *
     * @param word word to pass filter
     * @return <code>true</code> if passes filter. Otherwise <code>false</code>
     */
    private boolean isFiltered(String word) {
        return isLongerThanMinimalRequiredLength(word);
    }

    private void updateModCount() {
        modCount++;
    }
    
    private class PrefixMatchesIterable implements Iterable<String> {

        private Iterator<String> iterator;

        public PrefixMatchesIterable(String pref, int k) {
            iterator = new PrefixMatchesIterator(pref, k);
        }

        @Override
        public Iterator<String> iterator() {
            return iterator;
        }
    }

    private class PrefixMatchesIterator implements Iterator<String> {
        
        Iterator<String> trieIterator;       
        private final int totalK;
        private int currentK;
        private String next;
        int expectedModCount = modCount;
        
        public PrefixMatchesIterator(String pref, int k) {
            totalK = k;
            currentK = 1;
            trieIterator = trie.wordsWithPrefix(pref).iterator();
            if(trieIterator.hasNext()) {
                next = trieIterator.next();
            }
        }
        
        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public String next() {
            checkForComodification();
            checkForPresenceOfNextElement();
            String result = next;
            String newNext = trieIterator.next();
            if(newNext.length() > next.length()) {
                currentK++;
                if(currentK > totalK) {
                    next = null;
                } else {
                    next = newNext;
                }
            }
            return result;
        }
        
        private void checkForComodification() {
            if(expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
        
        private void checkForPresenceOfNextElement() {
            if(next == null) {
                throw new NoSuchElementException();
            }
        }
    }
}
