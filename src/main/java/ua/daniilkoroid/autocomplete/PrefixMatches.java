/**
 *
 */
package ua.daniilkoroid.autocomplete;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

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
	private final int MINIMAL_STRING_TO_ADD_LENGTH = 3;

	/**
	 * Trie that is used to store words.
	 */
	private final Trie trie;

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
	 * @param trie
	 *            in-memory dictionary to be used
	 */
	public PrefixMatches(Trie trie) {
		this.trie = trie;
	}

	/**
	 * Add given strings to in-memory dictionary.
	 *
	 * @param strings
	 *            strings to be stored in in-memory dictionary
	 * @return number of strings that were added
	 */
	public int add(String... strings) {
		int beforeAddSize = size();
		for (String string : strings) {
			if (string.length() < MINIMAL_STRING_TO_ADD_LENGTH) {
				continue;
			}
			trie.add(new Tuple(string));
		}
		int afterAddSize = size();
		int addedElements = afterAddSize - beforeAddSize;
		return addedElements;
	}

	/**
	 * Checks if given word is contained.
	 *
	 * @param word
	 *            word to check for being contained
	 * @return <code>true</code> if word is contained. Otherwise -
	 *         <code>false</code>
	 */
	public boolean contains(String word) {
		return trie.contains(word);
	}

	/**
	 * Deletes given word if such is contained.
	 * <p>
	 * If given word is present - deletes it and returns <code>true</code>. If
	 * given word is not present - does nothing and returns <code>false</code>.
	 * </p>
	 *
	 * @param word
	 *            word to delete
	 * @return <code>true</code> if word is contained and was deleted. Otherwise
	 *         - <code>false</code>
	 */
	public boolean delete(String word) {
		return trie.delete(word);
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
	 * @param pref
	 *            prefix for all words to find
	 * @param k
	 *            maximum differ lengths count
	 * @return iterable over all words that start from given prefix and
	 *         different lengths count that is not greater than given parameter
	 *         k. If no such words are found - {@link Collections#emptyList()}
	 *         is returned
	 */
	public Iterable<String> wordsWithPrefix(String pref, int k) {
		Iterable<String> wordsWithPrefix = trie.wordsWithPrefix(pref);
		LinkedList<String> wordsWithPrefixAndLength = new LinkedList<>();
		int prefLength = pref.length();
		int currentPrefLength = prefLength;
		int differentLengthCounter = 0;
		for (String wordWithPrefix : wordsWithPrefix) {
			if (wordWithPrefix.length() != currentPrefLength) {
				differentLengthCounter++;
				if (differentLengthCounter >= k) {
					break;
				}
			}
			wordsWithPrefixAndLength.add(wordWithPrefix);
		}
		Iterable<String> result;
		if (wordsWithPrefixAndLength.isEmpty()) {
			result = Collections.emptyList();
		} else {
			result = wordsWithPrefixAndLength;
		}
		return result;
	}

	/**
	 * Iterator over all words that start from given prefix and have length that
	 * is not longer than given prefix size and
	 * {@link PrefixMatches#DEFAULT_SEARCH_LENGTH}.
	 *
	 * @param pref
	 *            prefix for all words to find
	 * @return iterator over all words that start from given prefix and have
	 *         length that is not longer than given prefix size and given
	 *         parameter {@link PrefixMatches#DEFAULT_SEARCH_LENGTH}.
	 */
	public Iterable<String> wordsWithPrefix(String pref) {
		return wordsWithPrefix(pref, DEFAULT_SEARCH_LENGTH);
	}
}
