/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.daniilkoroid.autocomplete.trie;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Administrator
 */
public class RWayTrieTest {
    
    public RWayTrieTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class RWayTrie.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Tuple tuple = null;
        RWayTrie instance = new RWayTrie();
        instance.add(tuple);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class RWayTrie.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        String word = "";
        RWayTrie instance = new RWayTrie();
        boolean expResult = false;
        boolean result = instance.contains(word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class RWayTrie.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String word = "";
        RWayTrie instance = new RWayTrie();
        boolean expResult = false;
        boolean result = instance.delete(word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of words method, of class RWayTrie.
     */
    @Test
    public void testWords() {
        System.out.println("words");
        RWayTrie instance = new RWayTrie();
        Iterable<String> expResult = null;
        Iterable<String> result = instance.words();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of wordsWithPrefix method, of class RWayTrie.
     */
    @Test
    public void testWordsWithPrefix() {
        System.out.println("wordsWithPrefix");
        String pref = "";
        RWayTrie instance = new RWayTrie();
        Iterable<String> expResult = null;
        Iterable<String> result = instance.wordsWithPrefix(pref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class RWayTrie.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        RWayTrie instance = new RWayTrie();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
