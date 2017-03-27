/**
 * 
 */
package spelling;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Michael Schmitz
 *
 */
public class SpellingSuggestTester {

	private String dictFile = "data/dict.txt"; 
	Dictionary d;
	String word;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, dictFile);
	}
	
	/** Test the isWord method */
	@Test
	public void testSubstitutions()
	{
		word = "word";
		NearbyWords w = new NearbyWords(d);
		List<String> l = w.distanceOne(word, true);
		System.out.println("One away word Strings for \""+word+"\" are:");
		System.out.println(l+"\n");
		
/*
 * Some tests failed. Please check the following: 

** Test #1: distanceOne list size...FAILED. distanceOne returned 4 words when it should have returned 5. 
** Test #2: distanceOne words returned...FAILED. distanceOne does not return all expected words. 
** Test #3: distanceOne list size (allowing non-words)...FAILED. distanceOne with non-words returned 205 words when it should have returned 230. 
** Test #4: deletions list size...PASSED. 
** Test #5: deletions words returned...PASSED. 
** Test #6: insertions list size...FAILED. insertions returned 2 words when it should have returned 3. 
** Test #7: insertions words returned...FAILED. The list returned by insertions does not contain the expected set of words. 

** Test #1: 2 suggestions...FAILED. 1 suggestions returned instead. 
** Test #2: Checking suggestion correctness...FAILED. Suggestions don't contain expected set of words. 
** Test #3: 3 suggestions...FAILED. 2 suggestions returned instead. 
** Test #4: Checking suggestion correctness...FAILED. Suggestions don't contain expected set of words. 
 */
		
//		assertEquals("Testing isWord on empty: Hello", false, emptyDict.isWord("Hello"));	
		
	}
	
	/** Test the addWord method */
	@Test
	public void testInsertions()
	{
	
		
		
	}	
	
	
	
}
