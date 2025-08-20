package stack;

import java.util.EmptyStackException;

/**
 * Stack class with an array that follows LIFO (Last In First Out)
 *
 */
public class MyStack {
	/**
	 * The stack
	 */
	private int[] stack;
	/**
	 * The location of the last object in the stack
	 */
	private int last;
	
	/**
	 * A constructor to build the array, with initial size of 10
	 */
	public MyStack() {
		stack = new int[10];
		last = -1;
	}
	
	/**
	 * 
	 * @return The location of the last object in the stack
	 */
	public int getLast() {
		return last;
	}
	
	/**
	 * Checks if the stack is empty
	 * @return True if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		return last == -1;
	}
	
	/**
	 * Adds a new number to the top of the stack
	 * @param num
	 */
	public void push(int num) {
		last++; // To point to the new number in the stack
		
		// Doubles the size of the stack if the stack is full
		if(last == stack.length) {
			int[] tempStack = new int[last * 2];
			
			for(int i = 0; i < last; i++)
				tempStack[i] = stack[i];
			stack = tempStack;
		}
		
		stack[last] = num;
	}
	
	/**
	 * Removes the last entered number from the stack, and returns it
	 * @return The removed number
	 * @throws EmptyStackException If the stack is empty
	 */
	public int pop() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException();
		
		int element = stack[last]; // Save the number to return it
		stack[last] = 0; // Remove the number
		last--; // Point to the number before
		return element;
	}
	
	/**
	 * 
	 * @return Returns the last entered number, without removing it
	 * @throws EmptyStackException If the stack is empty
	 */
	public int peek() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException();
		
		return stack[last];
	}
	
	/**
	 * @return String that represents the stack
	 */
	 public String toString() {
	        if(isEmpty())
	            return "[]";

	        StringBuilder result = new StringBuilder("[");
	        for (int i = 0; i < last; i++) {
	            result.append(stack[i] + " ,");
	        }
	        
	        result.append(stack[last] + "]");
	        return result.toString();
    }
}
