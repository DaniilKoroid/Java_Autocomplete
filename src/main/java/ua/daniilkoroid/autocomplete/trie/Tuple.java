package ua.daniilkoroid.autocomplete.trie;

/**
 * Class that represents such tuple:
 * <ul>
 * <li>Term</li>
 * <li>Term's weight</li>
 * </ul>
 * <p>
 * This class is immutable.
 * </p>
 * 
 * @author Daniil_Koroid
 *
 */
public class Tuple {

	/**
	 * Term to be stored in tuple.
	 */
	private String term;

	/**
	 * Term's {@link #term} weight.
	 * <p>
	 * In this current case term's length is used.
	 * </p>
	 */
	private int weight;

	/**
	 * Creates tuple with given term and it's weights.
	 * 
	 * @param term
	 *            term to be stored in tuple
	 * @param weight
	 *            term's weight
	 */
	public Tuple(String term, int weight) {
		this.term = term;
		this.weight = weight;
	}

        /**
         * Creates tuple with given term.
         * <p>
         * By weight of term is used {@link String#length()}.
         * </p>
         * <p>
         * If given term is <code>null</code> - <code>0</code> weight is used.
         * </p>
         * @param term term to be stored in tuple
         */
        public Tuple(String term) {
            this.term = term;
            this.weight = term != null ? term.length() : 0;
        }
        
	/**
	 * Get tuple's term.
	 * 
	 * @return term that is stored in tuple
	 */
	public String getTerm() {
		return term;
	}

	/**
	 * Get tuple's term weight
	 * 
	 * @return weight of term that is stored in tuple
	 */
	public int getWeight() {
		return weight;
	}
}
