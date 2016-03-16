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
public class TupleTest {
    
    public TupleTest() {
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
     * Test of getTerm method, of class Tuple.
     */
    @Test
    public void testGetTerm() {
        System.out.println("getTerm");
        String term = "term";
        int weight = term.length();
        Tuple tupleByTermOnly = new Tuple(term);
        assertEquals(term, tupleByTermOnly.getTerm());
        assertEquals(weight, tupleByTermOnly.getWeight());
        Tuple tupleByTermAndWeight = new Tuple(term, weight);
        assertEquals(term, tupleByTermAndWeight.getTerm());
        assertEquals(weight, tupleByTermAndWeight.getWeight());
    }

    /**
     * Test of getWeight method, of class Tuple.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        String term = "term";
        int weight = term.length();
        Tuple tupleByTermOnly = new Tuple(term);
        assertEquals(weight, tupleByTermOnly.getWeight());
        Tuple tupleByTermAndWeight = new Tuple(term, weight);
        assertEquals(weight, tupleByTermAndWeight.getWeight());
    }
    
}
