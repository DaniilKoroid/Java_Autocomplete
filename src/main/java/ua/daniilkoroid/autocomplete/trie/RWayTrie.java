package ua.daniilkoroid.autocomplete.trie;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class that represents in-memory dictionary using R-way trie.
 *
 * @author Daniil_Koroid
 *
 */
public class RWayTrie implements Trie {

    /**
     * Inner class that represents node of trie.
     * <p>
     * This node stores given value and also link to all next nodes. Size of
     * used alphabet is determined by {@link #ALPHABET_SIZE}.
     * </p>
     *
     * @author Daniil_Koroid
     *
     */
    private static class Node {

        /**
         * Value to be stored.
         * <p>
         * Currently value is weight of term.
         * </p>
         */
        private int value;

        /**
         * Links to next nodes.
         */
        private Node[] next;

        /**
         * Create empty node.
         */
        public Node() {
            next = new Node[ALPHABET_SIZE];
        }
    }

    /**
     * Size of used alphabet.
     * <p>
     * Currently used alphabet is English alphabet which size is 26.
     * </p>
     */
    private static final int ALPHABET_SIZE = 26;

    /**
     * Empty prefix.
     * <p>
     * Empty prefix is prefix for all words.
     * </p>
     */
    private final String EMPTY_PREFIX = "";

    /**
     * First letter in used alphabet.
     */
    private static final char FIRST_ALPHABET_LETTER = 'a';

    /**
     * Empty node root.
     */
    private Node root;

    /**
     * Number of stored tuples.
     */
    private int size;

    /**
     * Stores number of modifications.
     */
    private int modCount;

    /**
     * Create RWayTrie.
     * <p>
     * Currently supported alphabet size is {@link #ALPHABET_SIZE}. Created
     * in-memory dictionary is created empty.
     * </p>
     */
    public RWayTrie() {
        this.root = new Node();
        size = 0;
    }

    @Override
    public void add(Tuple tuple) {
        if (!contains(tuple.getTerm())) {
            updateModificationsCount();
            put(root, tuple, 0);
        }
    }

    @Override
    public boolean contains(String word) {
        Node node = get(root, word, 0);
        return node != null;
    }

    @Override
    public boolean delete(String word) {
        if (!contains(word)) {
            return false;
        }
        updateModificationsCount();
        root = delete(root, word, 0);
        size--;
        return true;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix(EMPTY_PREFIX);
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        Node node = get(root, pref, 0);
        return new RWayTrieIterable(node, pref);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Find node with given key.
     *
     * @param node parent node to find node with given key among parent node
     * children
     * @param key key to find node by
     * @param d number of char in key to currently watch for
     * @return node with given key if found successfully. Otherwise -
     * <code>null</code>
     */
    private Node get(Node node, String key, int d) {
        Node result;
        if (node == null) {
            result = null;
        } else if (d == key.length()) {
            result = node;
        } else {
            int c = key.charAt(d) - FIRST_ALPHABET_LETTER;
            result = get(node.next[c], key, d + 1);
        }
        return result;
    }

    /**
     * Put given tuple in given node or in one of it's children.
     * <p>
     * If given node is not correct for putting given tuple in - tries to put
     * given tuple in appropriate child of given node.
     * </p>
     *
     * @param node node to put given tuple in or parent (can be indirect) of
     * node to put given tuple in
     * @param tuple tuple to put
     * @param d number of char in word to currently watch for
     * @return
     */
    private Node put(Node node, Tuple tuple, int d) {
        if (node == null) {
            node = new Node();
        }
        String term = tuple.getTerm();
        if (d == term.length()) {
            node.value = tuple.getWeight();
            size++;
            return node;
        }
        int c = term.charAt(d) - FIRST_ALPHABET_LETTER;
        node.next[c] = put(node.next[c], tuple, d + 1);
        return node;
    }

    /**
     * Deletes given key from given node.
     *
     * @param node node to delete given key
     * @param key key to delete
     * @param d index of char in key that is currently used to be pointed at
     * @return <code>null</code> if given node and all of the links in given
     * node are null. Otherwise return given node
     */
    private Node delete(Node node, String key, int d) {
        if (node == null) {
            return null;
        }
        if (d == key.length()) {
            node.value = 0;
        } else {
            int c = key.charAt(d) - FIRST_ALPHABET_LETTER;
            node.next[c] = delete(node.next[c], key, d + 1);
        }

        if (node.value != 0) {
            return node;
        }

        for (char c = 0; c < ALPHABET_SIZE; c++) {
            if (node.next[c] != null) {
                return node;
            }
        }
        return null;
    }

    /**
     * Updates modification count of RWayTrie.
     */
    private void updateModificationsCount() {
        modCount++;
    }

    private class RWayTrieIterable implements Iterable<String> {

        private Iterator<String> iterator;

        public RWayTrieIterable(Node root, String prefix) {
            iterator = new RWayTrieIterator(root, prefix);
        }

        @Override
        public Iterator<String> iterator() {
            return iterator;
        }

    }

    /**
     * Local class used to associate node with prefix that is stored with given
     * node.
     */
    private static class NodePrefixTuple {

        /**
         * Node to store in tuple.
         */
        private final Node node;

        /**
         * Prefix that is associated with given node.
         */
        private final String prefix;

        /**
         * Create tuple of node and associated prefix.
         *
         * @param node node to store
         * @param prefix appropriate prefix
         */
        public NodePrefixTuple(Node node, String prefix) {
            this.node = node;
            this.prefix = prefix;
        }
    }

    private class RWayTrieIterator implements Iterator<String> {

        private Queue<NodePrefixTuple> nodePrefixQueue;

        private NodePrefixTuple next;

        private int expectedModCount = modCount;

        public RWayTrieIterator(Node root, String prefix) {
            if (root == null || prefix == null) {
                next = null;
                return;
            }
            nodePrefixQueue = new LinkedList<>();
            nodePrefixQueue.offer(new NodePrefixTuple(root, prefix));
            findNext();
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public String next() {
            checkForComodification();
            NodePrefixTuple toReturn = next;
            findNext();
            return toReturn.prefix;
        }

        private void findNext() {
            boolean found = false;
            while (!found) {
                if (nodePrefixQueue.isEmpty()) {
                    next = null;
                    return;
                }
                NodePrefixTuple tuple = nodePrefixQueue.remove();
                Node node = tuple.node;
                String prefix = tuple.prefix;
                if (node.value != 0) {
                    next = tuple;
                    found = true;
                }
                for (int c = 0; c < ALPHABET_SIZE; c++) {
                    if (node.next[c] != null) {
                        String newPref = new StringBuilder(prefix).append((char) (FIRST_ALPHABET_LETTER + c)).toString();
                        nodePrefixQueue.offer(new NodePrefixTuple(node.next[c], newPref));
                    }
                }
            }
        }

        private void checkForComodification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
