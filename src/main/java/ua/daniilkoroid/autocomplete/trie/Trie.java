package ua.daniilkoroid.autocomplete.trie;

/**
 * Interface that represents in-memory dictionary.
 *
 * @author Daniil_Koroid
 *
 */
public interface Trie {

    /**
     * Adds given {@link ua.daniilkoroid.autocomplete.trie.Tuple} to this trie.
     *
     * @param tuple tuple to be added
     */
    public void add(Tuple tuple);

    /**
     * Checks if given word is contained in trie.
     * <p>
     * Returns <code>true</code> if given word is present in trie. If not -
     * returns false.
     * </p>
     *
     * @param word word to check for being contained in this trie
     * @return <code>true</code> if given word is contained in this trie.
     * Otherwise returns <code>false</code>
     */
    public boolean contains(String word);

    /**
     * Deletes given word from trie.
     * <p>
     * If given word is present in trie - deletes it and returns
     * <code>true</code>. If given word is not present in trie - does nothing
     * and returns <code>false</code>.
     * </p>
     *
     * @param word word to delete from trie
     * @return <code>true</code> if word is contained in trie and was deleted.
     * Otherwise - <code>false</code>
     */
    public boolean delete(String word);

    /**
     * Iterator over all stored words.
     * <p>
     * Class that implements interface must guarantee that elements in 
     * returned {@link Iterable} were collected using breadth-first search.
     * </p>
     *
     * @return iterator over all words
     */
    public Iterable<String> words();

    /**
     * Iterator over all words that start from given prefix.
     * <p>
     * Class that implements interface must guarantee that elements in
     * returned {@link Iterable} were collected using breadth-first search.
     * </p>
     *
     * @param pref prefix for all words to find
     * @return iterator over all words that start from given prefix
     */
    public Iterable<String> wordsWithPrefix(String pref);

    /**
     * Number of stored words in trie.
     *
     * @return number of stored words in trie
     */
    public int size();
}
