/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.daniilkoroid.autocomplete;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ua.daniilkoroid.autocomplete.trie.Trie;
import ua.daniilkoroid.autocomplete.trie.Tuple;

/**
 *
 * @author Daniil_Koroid
 */
@RunWith(MockitoJUnitRunner.class)
public class PrefixMatchesTest {

	@Mock
	private Trie mockTrie;

	@InjectMocks
	private PrefixMatches mockedPrefixMatches;

	public PrefixMatchesTest() {
	}

	@BeforeClass
	public static void setUpClass() throws IOException {
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
		String[] addedStrArr = new String[] { "word", "river", "base", "demo", "house", "cover", "queen" };
		String[] duplicatesArr = new String[] { "cover", "cover", "cover", "cover", "cover" };
		PrefixMatches instance = new PrefixMatches();
		int expectedSize = addedStrArr.length;
		assertEquals(expectedSize, instance.add(addedStrArr));
		int zeroAdded = 0;
		assertEquals(zeroAdded, instance.add(duplicatesArr));
	}

	/**
	 * Test that non filtered words are never added to trie.
	 */
	@Test
	public void testNonFilteredWordsNeverAddToTrie() {
		String[] addedStrArr = new String[] { "word", "river", "base", "demo", "house", "cover", "queen" };
		String[] nonGrata = new String[] { "ab", "ac", "ad", "aw", "fg", "df" };
		mockedPrefixMatches.add(addedStrArr);
		mockedPrefixMatches.add(nonGrata);
		verify(mockTrie, times(addedStrArr.length)).add(any(Tuple.class));
	}

	/**
	 * Test of contains method, of class PrefixMatches.
	 */
	@Test
	public void testContains() {
		System.out.println("contains");
                fail("test not implemented");
	}

	/**
	 * Test of contains method - test that PrefixMatches do not contains words
	 * that don't pass the filter and verify that trie structure's method
	 * contains is never used.
	 */
	@Test
	public void testContainsWithWordsThatDontPassTheFilter() {
		System.out.println("contains with words that dont pass the filter");
		String[] nonGrata = new String[] { "ab", "ac", "ad", "aw", "fg", "df" };
		mockedPrefixMatches.add(nonGrata);
		for (String nonGrataStr : nonGrata) {
			assertFalse(mockedPrefixMatches.contains(nonGrataStr));
			verify(mockTrie, never()).contains(nonGrataStr);
		}
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
	}

	/**
	 * Test of size method on array without duplicates, of class PrefixMatches.
	 */
	@Test
	public void testSizeOnArrayWODuplicates() {
		System.out.println("size on array without duplicates");
		String[] arrWODuplicates = new String[] { "word", "worder", "wordest", "simple", "garage", "scout" };
		PrefixMatches instance = new PrefixMatches();
		int oneAdded = 1;
		for (String str : arrWODuplicates) {
			assertEquals(oneAdded, instance.add(str));
		}
		int expResult = arrWODuplicates.length;
		int result = instance.size();
		assertEquals(expResult, result);
	}

	/**
	 * Test of size method on array with duplicates, of class PrefixMatches.
	 */
	@Test
	public void testSizeOnArrayWithDuplciates() {
		System.out.println("size on array with duplciates");
		String[] arrWODuplicates = new String[] { "word", "worder", "wordest", "simple", "garage", "scout" };
		String[] arrDuplicates = new String[] { "scout", "word", "word", "word", "word", "word", "word" };
		PrefixMatches instance = new PrefixMatches();
		int oneAdded = 1;
		int zeroAdded = 0;
		for (String str : arrWODuplicates) {
			assertEquals(oneAdded, instance.add(str));
		}
		for (String str : arrDuplicates) {
			assertEquals(zeroAdded, instance.add(str));
		}
		int expResult = arrWODuplicates.length;
		int result = instance.size();
		assertEquals(expResult, result);
	}

	/**
	 * Test of wordsWithPrefix method, of class PrefixMatches.
	 */
	@Test
	public void testWordsWithPrefix_String_int() {
		System.out.println("wordsWithPrefix with words of different lengthes");
		fail("Test not implemented");
	}

	/**
	 * Test of wordsWithPrefix method, of class PrefixMatches, trying to test
	 * that all contained words are greater than minimum required length.
	 */
	@Test
	public void testWordsWithPrefix_String_areGreaterThanMinimumRequiredLength() {
		System.out.println("wordsWithPrefix are greater than minimum required length");
		fail("Test not implemented");
	}

	/**
	 * Test of wordsWithPrefix method, of class PrefixMatches, trying to test
	 * that there are no contained words that are shorter than or equal to
	 * minimum required length.
	 */
	@Test
	public void testWordsWithPrefix_String_noWordsShorterThanMinimumRequiredLength() {
		System.out.println("wordsWithPrefix no words that are shorter than minimum required length");
		fail("Test not implemented");
	}

}
