package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		String wordLC = word.toLowerCase();
	    int wordLength = wordLC.length();
	    int charIndex = 0;
	    	    
	    TrieNode curr = root;
	    TrieNode next = null;
    
	    // Iterate through each character of word
	    while (charIndex < wordLength) {
		    // Try inserting character
	    	next = curr.insert(wordLC.charAt(charIndex));
	    	// If character already exists, get child
	    	if (next == null) {
	    		next = curr.getChild(wordLC.charAt(charIndex));
	    	}
	    	curr = next;
	    	charIndex++;
	    }
	    
	    // After finishing iteration, if our landing point is already a word
	    if (curr.endsWord()) {
	    	return false;
	    } else {
	    	curr.setEndsWord(true);
		    size++;
		    return true;
	    }
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{	
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
		if(s=="") {
			return false;
		}
		
		String wordLC = s.toLowerCase();
	    int wordLength = wordLC.length();
	    int charIndex = 0;
	    TrieNode curr = root;
   
	    // Iterate through each character of word
	    while (charIndex < wordLength) {
	    	curr = curr.getChild(wordLC.charAt(charIndex));
	    	if (curr == null) {
	    		return false;
	    	}
	    	charIndex++;
	    }
	    
	    if (curr.endsWord()) {
	    	return true;
	    }
	    return false;	    
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     { 		
    	String wordLC = prefix.toLowerCase();
	    int wordLength = wordLC.length();
	    int charIndex = 0;	    	    
	    TrieNode curr = root;
	    List<String> completions = new LinkedList<String>();
	    LinkedList<TrieNode> q = new LinkedList<TrieNode>();
    
	    // Iterate through each character of prefix
	    while (charIndex < wordLength) {
	    	curr = curr.getChild(wordLC.charAt(charIndex));
	    	if (curr == null) {
	    		return completions;
	    	}
	    	charIndex++;
	    }
	    
	    // Now we've reached the end of our stem. curr is the base
	    q.add(curr);
	    
	    while (!q.isEmpty() && completions.size()<numCompletions) {
	    	curr =  q.removeFirst();
	    	if(curr.endsWord()) {
	    		completions.add(curr.getText());
	    	}
	    	for (Character c : curr.getValidNextCharacters()) {
	    		q.addLast(curr.getChild(c));
	    	}
	    }
        return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}