/*
 * To cha	nge this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.daniilkoroid.autocomplete;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;

/**
 *
 * @author Administrator
 */
public class PrefixMatchesTest {

	@Mock
	private static PrefixMatches fullPrefixMatches = new PrefixMatches();

	public PrefixMatchesTest() {
	}

	@BeforeClass
	public static void setUpClass() throws IOException {
		String fileName = "src\\test\\resources\\words-333333.txt";
		File f = new File(fileName);
		try (BufferedReader reader = new BufferedReader(new FileReader(f));) {
			reader.lines().skip(1).forEach(new Consumer<String>() {

				private final String SPACE = "	";

				@Override
				public void accept(String str) {

					String[] arr = str.split(SPACE);
					String strToAdd = arr[arr.length - 1];
					fullPrefixMatches.add(strToAdd);
				}
			});
		}
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
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of contains method, of class PrefixMatches.
	 */
	@Test
	public void testContains() {
		System.out.println("contains");
		String[] surelyContainedWordsArr = new String[]{"stalwart", "seitz", "tomaszewski", "ministerien"};
		boolean expResult = true;
		for(String containedWord : surelyContainedWordsArr) {
			assertEquals(expResult, fullPrefixMatches.contains(containedWord));
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
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of size method on array without duplicates, of class PrefixMatches.
	 */
	@Test
	public void testSizeOnArrayWODuplicates() {
		System.out.println("size on array without duplicates");
		String[] arrWODuplicates = new String[]{"word", "worder", "wordest", "simple", "garage", "scout"};
		PrefixMatches instance = new PrefixMatches();
		int oneAdded = 1;
		for(String str : arrWODuplicates) {
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
		String[] arrWODuplicates = new String[]{"word", "worder", "wordest", "simple", "garage", "scout"};
		String[] arrDuplicates = new String[]{"scout", "word", "word", "word", "word", "word", "word"};
		PrefixMatches instance = new PrefixMatches();
		int oneAdded = 1;
		int zeroAdded = 0;
		for(String str : arrWODuplicates) {
			assertEquals(oneAdded, instance.add(str));
		}
		for(String str : arrDuplicates) {
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
		String pref = "goo";
		int k = 1;
		Iterable<String> result = fullPrefixMatches.wordsWithPrefix(pref, k);
		Map<Integer, Boolean> usedLengthsMap = new HashMap<>();
		for(String str : result) {
			usedLengthsMap.put(str.length(), true);
		}
		assertEquals(k, usedLengthsMap.size());
	}

	/**
	 * Test of wordsWithPrefix method, of class PrefixMatches, trying to test
	 * that all contained words are greater than minimum required length.
	 */
	@Test
	public void testWordsWithPrefix_String_areGreaterThanMinimumRequiredLength() {
		System.out.println("wordsWithPrefix are greater than minimum required length");
		String emptyPrefix = "";
		int minRequiredLength = 2;
		Iterable<String> result = fullPrefixMatches.wordsWithPrefix(emptyPrefix);
		for (String str : result) {
			assertTrue(str.length() > minRequiredLength);
		}
	}

	/**
	 * Test of wordsWithPrefix method, of class PrefixMatches, trying to test
	 * that there are no contained words that are shorter than or equal to
	 * minimum required length.
	 */
	@Test
	public void testWordsWithPrefix_String_noWordsShorterThanMinimumRequiredLength() {
		System.out.println("wordsWithPrefix no words that are shorter than minimum required length");
		String emptyPrefix = "";
		int minRequiredLength = 2;
		Iterable<String> result = fullPrefixMatches.wordsWithPrefix(emptyPrefix);
		for (String str : result) {
			assertFalse(str.length() <= minRequiredLength);
		}
	}

}
