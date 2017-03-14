package textgen;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.lang.StringBuilder;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		String[] allWords = sourceText.split("[\\s-—]+");
			
		starter = allWords[0];				//	set "starter" to be the first word in the text
		String prevWord = starter;			//	set "prevWord" to be starter
		String w;
		
		// Do for all words
		for (int i=1; i<allWords.length; i++)  {

			w = allWords[i];
			//	if "prevWord" is already a node in the list
			boolean matchFound = false;
			int j=0;
			while (!matchFound && j<wordList.size()) {
				ListNode nodeToCheck = wordList.get(j); 
				if(nodeToCheck.getWord().equals(prevWord)) {
					nodeToCheck.addNextWord(w);
					matchFound = true;
					break;
				}
				j++;
			}
//			ListIterator<ListNode> itr = wordList.listIterator();
//			while (!matchFound && itr.hasNext()) {
//				ListNode nodeToCheck = itr.next();
//				if(nodeToCheck.getWord().equals(prevWord)) {
//					nodeToCheck.addNextWord(w);
//					matchFound = true;
//				}
//			}
			
			// if "prevWord" is a 'new' word
			if (!matchFound) {
				ListNode newWordNode = new ListNode(prevWord);
				newWordNode.addNextWord(w);
				wordList.add(newWordNode);
			}
			// Set prevWord equal to current word
			prevWord = w;
		}
		
//		Add starter as a NextWord for the last word in the source text.
		String lastWord = allWords[allWords.length-1];
//		System.out.println("Adding starter as a NextWord for the last word...");
		// Find the node corresponding to lastWord
		boolean matchFound = false;
		int j=0;
		while (!matchFound && j<wordList.size()) {
			ListNode nodeToCheck = wordList.get(j); 
			if(nodeToCheck.getWord().equals(lastWord)) {
				nodeToCheck.addNextWord(starter);
				matchFound = true;
				break;
			}
			j++;
		}
		if (!matchFound) {
			ListNode newWordNode = new ListNode(lastWord);
			newWordNode.addNextWord(starter);
			wordList.add(newWordNode);
		}
		
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		StringBuilder outputSB = new StringBuilder(numWords);
		String currWord;
		String nextWord;
		
		if (numWords==0 || wordList.size()==0) {
			return "";
		}
		
		currWord = wordList.get(0).getWord();
		
		List<String> outputList = new LinkedList<String>();
		outputList.add(currWord);
		
		int wordCount = 0;
		while(wordCount < numWords-1) {
			boolean matchFound = false;
			int j=0;
			while (!matchFound && j<=wordList.size()) {
				ListNode nodeToCheck = wordList.get(j); 
				if(nodeToCheck.getWord().equals(currWord)) {
					// Get random word from this node's NextWords
					nextWord = nodeToCheck.getRandomNextWord(rnGenerator);
					// Add nextWord to outputList
					outputList.add(nextWord);
					currWord = nextWord;
					matchFound = true;
					break;
				}
				j++;
			}
			
			wordCount++;
		}
		
		outputSB.append(wordList.get(0).getWord());
		
		for (int i=1; i<outputList.size(); i++){
			outputSB.append(" ");
			outputSB.append(outputList.get(i));
		}
		
		return outputSB.toString();
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList.clear(); 
		starter="";
		train(sourceText);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int index = generator.nextInt(nextWords.size());
	    return nextWords.get(index);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


