/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list0;
	MyLinkedList<Integer> list1;
	MyLinkedList<Integer> list2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		
		emptyList = new MyLinkedList<Integer>();
		
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		
		list0 = new MyLinkedList<Integer>();
		list0.add(1);
		
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		list2 = new MyLinkedList<Integer>();
		list2.add(65);
		list2.add(21);
		list2.add(42);
		
		
//		System.out.println(shortList);
//		System.out.println(emptyList);
//		System.out.println(longerList);
//		System.out.println(list1);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.remove(1);
			fail("Remove: Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		//test empty list, get should throw an exception
		try {
			list2.remove(-1);
			fail("Remove: Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		//test empty list, get should throw an exception
		try {
			list2.remove(5);
			fail("Remove: Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// Check if new 0 element's prev now correctly references head
		assertEquals("Remove: check element 0 has prev reference to head", list1.head, list1.head.next.prev);
		
		// Remove from a list of size 1
		list0.remove(0);
		assertEquals("Remove: check size is correct ", 0, list0.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		// Null elements are not allowed in the list so if someone tries to insert one you should throw a NullPointerException.
		try {
			list1.add(null);
			fail("Add: Check null elements");
		}
		catch (NullPointerException e) {
		}
		
		// Add element to an empty list
		emptyList.add(123);
		assertEquals("AddEnd: check data at index is correct ", (Integer)123, emptyList.get(0));
		assertEquals("AddEnd: check tail.prev.data is correct ", (Integer)123, emptyList.tail.prev.data);
		assertEquals("AddEnd: check head.next.data is correct ", (Integer)123, emptyList.head.next.data);
		assertEquals("AddEnd: check size is correct ", 1, emptyList.size());
		
		// Add element to end of a list with existing elements
		list1.add(3,789);
		assertEquals("AddEnd: check data at index is correct ", (Integer)789, list1.get(3));
		assertEquals("AddEnd: check tail.prev.data is correct ", (Integer)789, list1.tail.prev.data);
		assertEquals("AddEnd: check size is correct ", 4, list1.size());
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		emptyList.add(123);
		assertEquals("Size: check size is correct ", 1, emptyList.size());

		list1.add(123);
		assertEquals("Size: check size is correct ", 4, list1.size());
	
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		// Null elements are not allowed in the list so if someone tries to insert one you should throw a NullPointerException.
		try {
			list1.add(0, null);
			fail("AddAtIndex: Check null elements");
		}
		catch (NullPointerException e) {
		}

		//test empty list, get should throw an exception
		try {
			list2.add(5, 1);
			fail("AddAtIndex: Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		//test empty list, get should throw an exception
		try {
			list2.add(-1, 1);
			fail("AddAtIndex: Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		// Add element to beginning of an empty list
		emptyList.add(0, 123);
		assertEquals("AddAtIndex: check tail.prev.data is correct ", (Integer)123, emptyList.get(0));

		// Add element to beginning of a list with existing elements
		int oldSize = list1.size();
		list1.add(0, 123);
		int newSize = list1.size();
		assertEquals("AddAtIndex: check element at beginning of list is correct ", (Integer)123, list1.get(0));
		assertEquals("AddAtIndex: check size is correct ", oldSize+1, newSize);

		// Add element to end of a list with existing elements
		int oldSize2 = list2.size();
		list2.add(0, 123);
		int newSize2 = list2.size();
		list2.add(3, 123);
		assertEquals("AddAtIndex: check element at end of list is correct ", (Integer)123, list2.get(3));
		assertEquals("AddAtIndex: check size is correct ", oldSize2+1, newSize2);
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		// Null elements are not allowed in the list so if someone tries to insert one you should throw a NullPointerException.
		try {
			list1.set(0, null);
			fail("Set: Check null elements");
		}
		catch (NullPointerException e) {
		}
		
		//test empty list, get should throw an exception
		try {
			emptyList.set(-1, 1);
			fail("Set: Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
		//test empty list, get should throw an exception
		try {
			emptyList.set(1, 1);
			fail("Set: Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}

		int a = list1.set(0, 123);
	    assertEquals("Set: check element returned is correct ", 65, a);
	    assertEquals("Set: check that replaced element is correct ", (Integer)123, list1.get(0));

		int b = list1.set(1, 456);
	    assertEquals("Set: check element returned is correct ", 21, b);
	    assertEquals("Set: check that replaced element is correct ", (Integer)456, list1.get(1));
	}
	
}
