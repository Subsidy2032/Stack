package stack;

import java.util.EmptyStackException;

/**
 * A class with an array of 3 stacks, uses the methods from the MyStack class so runs in O(1) time as well
 *
 */
public class HStack {
	/**
	 * The array to store the stacks
	 */
	private MyStack[] stacks;
	
	/**
	 * Constructor to put 3 stacks in the array
	 * @param stack1 The first stack
	 * @param stack2 The second stack
	 * @param stack3 The third stack
	 */
	public HStack(MyStack stack1, MyStack stack2, MyStack stack3) {
		stacks = new MyStack[3];
		stacks[0] = stack1;
		stacks[1] = stack2;
		stacks[2] = stack3;
	}
	
	/**
	 * 
	 * @return The stacks' array
	 */
	public MyStack[] getStacks() {
		return stacks;
	}
	
	/**
	 * Checks if the stack in the given index is empty
	 * @param s The index of the stack to check
	 * @return True if the stack is empty, false otherwise
	 * @throws IllegalArgumentException If a index that isn't between 0-2 has been entered
	 */
	public boolean isEmpty(int s) throws IllegalArgumentException {
		if(s < 0 || s > 2)
			throw new IllegalArgumentException("Index must be between 0-2");
		
		return stacks[s].isEmpty();
	}
	
	/**
	 * Returns the top element of the stack in a given index
	 * @param s The index of the stack
	 * @return The top element in the stack
	 * @throws EmptyStackException If the stack is empty
	 * @throws IllegalArgumentException If a index that isn't between 0-2 has been entered
	 */
	public int peek(int s) throws EmptyStackException, IllegalArgumentException {
		if(s < 0 || s > 2)
			throw new IllegalArgumentException("Index must be between 0-2");
		
		return stacks[s].peek();
	}
	
	
	/**
	 * Moves the top number of a stack in a given index to the top of the stack in the next index (to index 0 if the given index is 2)
	 * @param s The index of the stack
	 * @throws EmptyStackException If the stack is empty
	 * @throws IllegalArgumentException If a index that isn't between 0-2 has been entered
	 */
	public void move(int s) throws EmptyStackException, IllegalArgumentException {
		if(s < 0 || s > 2)
			throw new IllegalArgumentException("Index must be between 0-2");
		
		int toMove = stacks[s].pop();
		stacks[(s + 1) % 3].push(toMove);
	}
}
