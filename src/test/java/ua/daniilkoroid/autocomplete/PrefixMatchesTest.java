/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.daniilkoroid.autocomplete;

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
public class PrefixMatchesTest {
    
    public PrefixMatchesTest() {
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
     * Test of add method, of class PrefixMatches.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        String[] strings = null;
        PrefixMatches instance = new PrefixMatches();
        int expResult = 0;
        int result = instance.add(strings);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class PrefixMatches.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        String word = "";
        PrefixMatches instance = new PrefixMatches();
        boolean expResult = false;
        boolean result = instance.contains(word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PrefixMatches.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String word = "";
        PrefixMatches instance = new PrefixMatches();
        boolean expResult = false;
        boolean result = instance.delete(word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class PrefixMatches.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        PrefixMatches instance = new PrefixMatches();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of wordsWithPrefix method, of class PrefixMatches.
     */
    @Test
    public void testWordsWithPrefix_String_int() {
        System.out.println("wordsWithPrefix");
        String pref = "";
        int k = 0;
        PrefixMatches instance = new PrefixMatches();
        Iterable<String> expResult = null;
        Iterable<String> result = instance.wordsWithPrefix(pref, k);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of wordsWithPrefix method, of class PrefixMatches.
     */
    @Test
    public void testWordsWithPrefix_String() {
        System.out.println("wordsWithPrefix");
        String pref = "";
        PrefixMatches instance = new PrefixMatches();
        Iterable<String> expResult = null;
        Iterable<String> result = instance.wordsWithPrefix(pref);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
