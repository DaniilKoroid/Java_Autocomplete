/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.daniilkoroid.autocomplete.trie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Daniil_Koroid
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
     * Test of add method with empty RWayTrie, of class RWayTrie.
     */
    @Test
    public void testAddToEmptyRWayTrie() {
        System.out.println("test add to empty RWayTrie");
        RWayTrie instance = new RWayTrie();
        int instanceInitialSize = instance.size();
        int expectedEmptySize = 0;
        assertEquals(expectedEmptySize, instanceInitialSize);
        String term = "term";
        Tuple tuple = new Tuple(term);
        instance.add(tuple);
        int expectedSize = 1;
        assertEquals(expectedSize, instance.size());
    }
    
    /**
     * Test of add method with not empty RWayTrie, of class RWayTrie.
     */
    @Test
    public void testAddToNotEmptyRWayTrie() {
    	System.out.println("test add to not empty RWayTrie");
    	RWayTrie instance = new RWayTrie();
    	String firstTerm = "term";
    	String secondTerm = "termer";
    	instance.add(new Tuple(firstTerm));
    	int expectedSize = 1;
    	assertEquals(expectedSize, instance.size());
    	instance.add(new Tuple(secondTerm));
    	expectedSize = 2;
    	assertEquals(expectedSize, instance.size());
    }

    /**
     * Test of contains method, of class RWayTrie.
     */
    @Test
    public void testContains() {
        System.out.println("test contains");
        String containedWord = "wordcontained";
        RWayTrie instance = new RWayTrie();
        boolean expResult = true;
        String[] words = new String[]{containedWord, "qwerty", "asdf", "rhvadio", "radio", "tvmaster"};
        for(String word : words) {
        	instance.add(new Tuple(word));
        }
        boolean result = instance.contains(containedWord);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of contains method - trying to check if not contained word is contained.
     */
    @Test
    public void testNotContains() {
    	System.out.println("test not contains");
    	String notContainedWord = "wordnotcontained";
    	RWayTrie instance = new RWayTrie();
    	boolean expResult = false;
    	String[] words = new String[]{"containedword", "qwerty", "asdf", "rhvadio", "radio", "tvmaster"};
        for(String word : words) {
        	instance.add(new Tuple(word));
        }
        boolean result = instance.contains(notContainedWord);
        assertEquals(expResult, result);
    }

    /**
     * Test of delete method - trying to delete contained word, of class RWayTrie.
     */
    @Test
    public void testDeleteContained() {
        System.out.println("test delete");
        String word = "word";
        RWayTrie instance = new RWayTrie();
        String[] words = new String[]{word, "qwerty", "asdf", "rhvadio", "radio", "tvmaster"};
        for(String wordToAdd : words) {
        	instance.add(new Tuple(wordToAdd));
        }
        boolean expResult = true;
        int initialSize = instance.size();
        boolean result = instance.delete(word);
        assertEquals(initialSize - 1, instance.size());
        assertEquals(expResult, result);
    }
    
    /**
     * Test of delete method - trying to delete not contained word, of class RWayTrie.
     */
    @Test
    public void testDeleteNotContained() {
    	System.out.println("test delete not contained");
    	String word = "word";
        RWayTrie instance = new RWayTrie();
        String[] words = new String[]{"wword", "qwerty", "asdf", "rhvadio", "radio", "tvmaster"};
        for(String wordToAdd : words) {
        	instance.add(new Tuple(wordToAdd));
        }
        boolean expResult = false;
        int initialSize = instance.size();
        boolean result = instance.delete(word);
        assertEquals(initialSize, instance.size());
        assertEquals(expResult, result);
    }

    /**
     * Test of words method, of class RWayTrie.
     */
    @Test
    public void testWordsOfNotEmptyRWayTrie() {
        System.out.println("test words method of not empty RWayTrie");
        RWayTrie instance = new RWayTrie();
        String[] wordsArr = new String[]{"word", "qwerty", "asdf", "rhvadio", "radio", "tvmaster"};
        List<String> wordsList = new ArrayList<String>(Arrays.asList(wordsArr));
        for(String wordToAdd : wordsArr) {
        	instance.add(new Tuple(wordToAdd));
        }
        
        Iterable<String> words = instance.words();
        
        for(String word : words) {
        	assertTrue(wordsList.contains(word));
        	wordsList.remove(word);
        }
        int emptySize = 0;
        assertEquals(emptySize, wordsList.size());
    }

    /**
     * Test of wordsWithPrefix method, of class RWayTrie.
     */
    @Test
    public void testWordsWithPrefix() {
        System.out.println("test wordsWithPrefix");
        String pref = "pref";
        String[] wordsWithPrefix = new String[]{pref + "q", pref + "w", pref + "e", pref + "z"};
        String[] wordsWOPrefix = new String[]{"word", "qwerty", "asdf", "rhvadio", "radio", "tvmaster"};
        List<String> wordsWithPrefixList = new ArrayList<String>(Arrays.asList(wordsWithPrefix));
        List<String> allWordsList = new ArrayList<String>(Arrays.asList(wordsWOPrefix));
        allWordsList.addAll(Arrays.asList(wordsWithPrefix));
        RWayTrie instance = new RWayTrie();
        
        for(String wordToAdd : allWordsList) {
        	instance.add(new Tuple(wordToAdd));
        }
        
        Iterable<String> result = instance.wordsWithPrefix(pref);
        
        for(String wordWithPrefix : result) {
        	assertTrue(wordsWithPrefixList.contains(wordWithPrefix));
        	wordsWithPrefixList.remove(wordWithPrefix);
        }
        
        int emptySize = 0;
        assertEquals(emptySize, wordsWithPrefixList.size());
    }

    /**
     * Test of size method with empty RWayTrie.
     */
    @Test
    public void testSizeEmpty() {
        System.out.println("size empty");
        RWayTrie instance = new RWayTrie();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of size method with not empty RWayTrie
     */
    @Test
    public void testSizeNotEmpty() {
    	RWayTrie instance = new RWayTrie();
    	String[] words = new String[]{"word", "qwerty", "asdf", "rhvadio", "radio", "tvmaster"};
    	for(String wordToAdd : words) {
        	instance.add(new Tuple(wordToAdd));
        }
    	int expectedSize = words.length;
    	int resultSize = instance.size();
    	assertEquals(expectedSize, resultSize);
    }
    
}
