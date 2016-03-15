/**
 * 
 */
package ua.daniilkoroid.autocomplete;

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
	 * Trie that is used to store words.
	 */
	private Trie trie;

	/**
	 * Add given strings to in-memory dictionary.
	 * 
	 * @param strings
	 *            strings to be stored in in-memory dictionary
	 * @return
	 */
	public int add(String... strings) {
		for (String string : strings) {
			trie.add(new Tuple(string, string.length()));
		}

		return 1;
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
	 * Iterator over all words that start from given prefix and have length that
	 * is not longer than given prefix size and given parameter k.
	 * 
	 * @param pref
	 *            prefix for all words to find
	 * @param k
	 *            maximum differ length from prefix length
	 * @return iterator over all words that start from given prefix and have
	 *         length that is not longer than given prefix size and given
	 *         parameter k.
	 */
	public Iterable<String> wordsWithPrefix(String pref, int k) {
		throw new UnsupportedOperationException("This operation is currently not supported.");
	}

	/**
	 * Iterator over all words that start from given prefix and have length that
	 * is not longer than given prefix size and
	 * {@link PrefixMatches#DEFAULT_SEARCH_LENGTH}.
	 * 
	 * @param pref
	 *            prefix for all words to find
	 * @param k
	 *            maximum differ length from prefix length
	 * @return iterator over all words that start from given prefix and have
	 *         length that is not longer than given prefix size and given
	 *         parameter {@link PrefixMatches#DEFAULT_SEARCH_LENGTH}.
	 */
	public Iterable<String> wordsWithPrefix(String pref) {
		return wordsWithPrefix(pref, DEFAULT_SEARCH_LENGTH);
	}
}
