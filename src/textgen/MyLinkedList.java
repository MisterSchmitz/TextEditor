package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) throws NullPointerException
	{
		if (element == null) {
			throw new NullPointerException("Null elements are not allowed in the list.");
		}
		
		LLNode<E> elemToAdd = new LLNode<E>(element, tail.prev, tail);
		tail.prev.next = elemToAdd;
		tail.prev = elemToAdd;

		size++;
		return true;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) throws NullPointerException, IndexOutOfBoundsException
	{
		if (element == null) {
			throw new NullPointerException("Null elements are not allowed in the list.");
		}
		
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}
		
		// If given index falls somewhere within the current list:
		if (index >= 0 && index < size) {
			LLNode<E> curr = getNodeAtIndex(index);
			LLNode<E> elemToAdd = new LLNode<E>(element, curr.prev, curr);
			curr.prev.next = elemToAdd;
			curr.prev = elemToAdd;
			size++;
		}

		// If given index equals the size of our list, add to end of list.
		if (index == size) {
			add(element);
		}
	}

	/** Get the node at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public LLNode<E> getNodeAtIndex(int index) throws IndexOutOfBoundsException
	{
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bounds.");
		}

		// Traverse the list until we arrive at given index
		LLNode<E> curr = head;
		for(int i=0; i<=index; i++) {
			curr = curr.next;
		}
		return curr;
	}	
	
	/** Get the data element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		return getNodeAtIndex(index).data;
	}

	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException
	{	
		LLNode<E> elemToRemove = getNodeAtIndex(index);
		elemToRemove.next.prev = elemToRemove.prev;
		elemToRemove.prev.next = elemToRemove.next;

		size--;
		return elemToRemove.data;
	}
	
	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException
	{
		if (element == null) {
			throw new NullPointerException("Null elements are not allowed in the list."); 
		}
		
		LLNode<E> oldElement = getNodeAtIndex(index);
		LLNode<E> newElement = new LLNode<E>(element, oldElement.prev, oldElement.next);
		oldElement.prev.next = newElement;
		oldElement.next.prev = newElement;
		return oldElement.data;
	}
	
	public String toString() {
		return "List size: "+size+" ["+head.next.data+" ... "+tail.prev.data+"]";
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	public LLNode(E e, LLNode<E> prevNode, LLNode<E> nextNode) 
	{
		this.data = e;
		this.prev = prevNode;
		this.next = nextNode;
	}
	
	public String toString(){
		return (String) data;
	}
}
