/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.daniilkoroid.autocomplete;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
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
 * Tests for PrefixMatches class
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

	@Test
	public void testTrieAddsOnlyFilteredWords() {
		System.out.println("test trie adds only filtered words");
		String[] filteredWords = new String[] { "word", "river", "base", "demo", "house", "cover", "queen" };
		String[] nonFilteredWords = new String[] { "qw", "we", "u", "go", "az", "ez" };
		mockedPrefixMatches.add(filteredWords);
		mockedPrefixMatches.add(nonFilteredWords);
		verify(mockTrie, times(filteredWords.length)).add(any(Tuple.class));
	}

	@Test
	public void testNonFilteredWordsAreNeverAddedToTrie() {
		System.out.println("test non filtered words are never added to trie");
		String[] addedStrArr = new String[] { "word", "river", "base", "demo", "house", "cover", "queen" };
		String[] nonGrata = new String[] { "ab", "ac", "ad", "aw", "fg", "df" };
		mockedPrefixMatches.add(addedStrArr);
		mockedPrefixMatches.add(nonGrata);
		verify(mockTrie, times(addedStrArr.length)).add(any(Tuple.class));
	}

	@Test
	public void testAddReturnsNumberOfAddedWords() {
		System.out.println("test add returns number of added words");
		String[] addedStrArr = new String[] { "word", "river", "base", "demo", "house", "cover", "queen" };
		String[] duplicatesArr = new String[] { "cover", "cover", "cover", "cover", "cover" };
		PrefixMatches instance = new PrefixMatches();
		int expectedSize = addedStrArr.length;
		assertEquals(expectedSize, instance.add(addedStrArr));
		int zeroAdded = 0;
		assertEquals(zeroAdded, instance.add(duplicatesArr));
	}

	@Test
	public void testContainsAddedWords() {
		System.out.println("test contains added words");
		String[] addedStrArr = new String[] { "word", "river", "base", "demo", "house", "cover", "queen" };
		PrefixMatches instance = new PrefixMatches();
		instance.add(addedStrArr);
		int containedWordsCounter = 0;
		for (String word : addedStrArr) {
			assertTrue(instance.contains(word));
			containedWordsCounter++;
		}
		int expectedContainedWordsCounter = addedStrArr.length;
		assertEquals(expectedContainedWordsCounter, containedWordsCounter);
	}

	@Test
	public void testNotContainsWordsThatDontPassTheFilter() {
		System.out.println("test not contains words that dont pass the filter");
		String[] nonGrata = new String[] { "", "ab", "ac", "ad", "aw", "fg", "df" };
		mockedPrefixMatches.add(nonGrata);
		for (String nonGrataStr : nonGrata) {
			assertFalse(mockedPrefixMatches.contains(nonGrataStr));
			verify(mockTrie, never()).contains(nonGrataStr);
		}
	}

	@Test
	public void testDeleteReturnsFalseOnDeletingFromEmptyPrefixMatches() {
		System.out.println("test delete returns false on deleting from empty " + "prefix matches");
		String word = "";
		PrefixMatches instance = new PrefixMatches();
		boolean expResult = false;
		boolean result = instance.delete(word);
		assertEquals(expResult, result);
	}
	
	@Test
	public void testDeleteContainedWordsReturnsTrue() {
		System.out.println("test delete contained words returnes true");
		String[] words = new String[]{"word", "empty", "boss", "dobby", "crack"};
		PrefixMatches instance = new PrefixMatches();
		instance.add(words);
		for (String word : words) {
			assertTrue(instance.delete(word));
		}
	}
	
	@Test
	public void testDeleteReturnsFalseWhenDeletingNoncontainedWords() {
		System.out.println("test delete returns false when deleting noncontained words");
		String[] containedWords = new String[]{"word", "empty", "boss", "dobby", "crack"};
		String[] nonContainedWords = new String[]{"body", "zero", "eclipse", "robbo"};
		PrefixMatches instance = new PrefixMatches();
		instance.add(containedWords);
		for (String nonContainedWord : nonContainedWords) {
			assertFalse(instance.delete(nonContainedWord));
		}
	}
	
	@Test
	public void testDeleteOnTrieIsNeverCalledOnNonfilteredWords() {
		System.out.println("test delete on trie is never called on nonfiltered words");
		String[] nonFilteredWords = new String[] { "", "ab", "ac", "ad", "aw", "fg", "df" };
		mockedPrefixMatches.add(nonFilteredWords);
		for (String nonFilteredWord : nonFilteredWords) {
			mockedPrefixMatches.delete(nonFilteredWord);
		}
		verify(mockTrie, never()).delete(any(String.class));
	}

	@Test
	public void testSizeOnArrayWithoutDuplicates() {
		System.out.println("test size on array without duplicates");
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

	@Test
	public void testDuplicatesNotAdded() {
		System.out.println("test duplicates not added");
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

	@Test
	public void testWordsWithPrefixWithWordsOfTwoDifferentLengthes() {
		System.out.println("test wordsWithPrefix with words of two different lengthes");
		String basePrefix = "word";
		int difLengthsCount = 2;
		String[] wordsWithDifLengths = new String[] { "worde", "worder", "wordor", "wordag" };
		List<String> containedWords = new ArrayList<>(Arrays.asList(wordsWithDifLengths));
		String[] wordsWithBigLengths = new String[] { "qwertyq", "builder", "kongooq" };
		PrefixMatches instance = new PrefixMatches();
		instance.add(wordsWithDifLengths);
		instance.add(wordsWithBigLengths);

		Iterable<String> wordsWithPrefix = instance.wordsWithPrefix(basePrefix, difLengthsCount);
		for (String word : wordsWithPrefix) {
			assertTrue(containedWords.contains(word));
			containedWords.remove(word);
		}
		int zeroLength = 0;
		assertEquals(zeroLength, containedWords.size());
	}
	
	@Test
	public void testWordsWithPrefixReturnsProperWords() {
		System.out.println("test words with prefix returns proper words");
		String basePrefix = "qwe";
		String[] words = new String[]{basePrefix, basePrefix + "r",
				basePrefix + "rt", basePrefix + "rty", basePrefix + "rtyu",};
		PrefixMatches instance = new PrefixMatches();
		instance.add(words);
		Iterable<String> wordsWithPrefix = instance.wordsWithPrefix(basePrefix);
		List<String> suggestedWords = new ArrayList<String>() {
			{
				add(basePrefix);
				add(basePrefix + "r");
				add(basePrefix + "rt");
			}
		};
		for (String word : wordsWithPrefix) {
			assertTrue(suggestedWords.contains(word));
			suggestedWords.remove(word);
		}
		assertEquals(0, suggestedWords.size());
		
	}
}
