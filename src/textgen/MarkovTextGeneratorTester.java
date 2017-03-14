/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import document.DocumentBenchmarking;

/**
 * @author Michael Schmitz
 *
 */
public class MarkovTextGeneratorTester { 
	
	// feed the generator a fixed random value for repeatable behavior
	MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random());
	String textFile;
	String testSourceText;
	String testSourceTextFromFile;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		textFile = "data/trump6.txt";
		testSourceTextFromFile = DocumentBenchmarking.getStringFromFile(textFile, 8527);
		testSourceText = "Hi there. Hi Leo.";
	}
	
	
	
	/** Test if the train method is working correctly.
	 */
	@Test
	public void testTrain()
	{
//		System.out.println("--------------------------");
//		System.out.println(testSourceText);
//		System.out.println("--------------------------");
		gen.train(testSourceText);
//		gen.train(testSourceTextFromFile);
		System.out.println(gen);
//		System.out.println("--------------------------");
		System.out.println(gen.generateText(10));
	}
}