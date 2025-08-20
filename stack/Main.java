package stack;

public class Main {

	public static void main(String[] args) {
		MyStack stack = new MyStack();
		stack.push(5);
		stack.push(1);
		stack.push(17);
		stack.push(3);
		stack.push(8);
		stack.push(2);
		stack.push(5);
		stack.push(7);
		stack.push(12);
		stack.push(57);
		stack.push(13);
		stack.push(15);
		
		MyStack sorted1 = new MyStack();
		MyStack sorted2 = new MyStack();
		
		sorted1.push(31);
		sorted1.push(23);
		sorted1.push(17);
		sorted1.push(14);
		sorted1.push(12);
		sorted1.push(9);
		sorted1.push(7);
		sorted1.push(4);
		sorted1.push(3);
		sorted1.push(2);
		sorted1.push(-1);
		sorted1.push(-5);
		
		sorted2.push(51);
		sorted2.push(43);
		sorted2.push(35);
		sorted2.push(17);
		sorted2.push(14);
		sorted2.push(12);
		sorted2.push(6);
		sorted2.push(3);
		sorted2.push(1);
		
		System.out.println(stack.toString());
		stack.pop();
		System.out.println(stack);
		reverseStack(stack);
		System.out.println(stack);
		System.out.println(mergeSortedStacks(sorted1, sorted2));
		stack = sortStack(stack);
		System.out.println(stack);
		System.out.println(isElementExist(stack, 12));
		System.out.println(stack);
		System.out.println();
		
		MyStack stack1 = new MyStack();
		stack1.push(5);
		stack1.push(1);
		stack1.push(17);
		stack1.push(3);
		stack1.push(57);
		stack1.push(2);
		stack1.push(5);
		stack1.push(7);
		stack1.push(-12);
		stack1.push(8);
		stack1.push(13);
		stack1.push(15);
		stack1.push(11);
		stack1.push(57);
		stack1.push(13);
		stack1.push(15);
		
		MyStack stack2 = new MyStack();
		stack2.push(8);
		stack2.push(11);
		stack2.push(53);
		stack2.push(44);
		stack2.push(1);
		stack2.push(-13);
		stack2.push(59);
		stack2.push(7);
		stack2.push(3);
		stack2.push(-8);
		stack2.push(13);
		stack2.push(21);
		
		MyStack stack3 = new MyStack();
		stack3.push(4);
		stack3.push(2);
		stack3.push(100);
		stack3.push(4);
		
		HStack stacks = new HStack(stack1, stack2, stack3);
		for(int i = 0; i < 3; i++)
			System.out.println(stacks.getStacks()[i]);
		System.out.println();
		
		moveMaxFrom0To1(stacks);
		for(int i = 0; i < 3; i++)
			System.out.println(stacks.getStacks()[i]);
		System.out.println();
		
		sortAllToZero(stacks);
		for(int i = 0; i < 3; i++)
			System.out.println(stacks.getStacks()[i]);
		System.out.println();
	}
	
	/**
	 * Gets a stack and reverses the order of the stack, runs in O(n) time
	 * @param stack The stack to reverse
	 */
	public static void reverseStack(MyStack stack) {
		MyStack reversedStack = new MyStack();
		MyStack original = new MyStack();
		
		// Reverse the stack order
		while(!stack.isEmpty())
			reversedStack.push(stack.pop()); // Move the top element in the stack to the current top of the new stack
		
		// Put the numbers in a new stack in the original order
		while(!reversedStack.isEmpty())
			original.push(reversedStack.pop());
		
		// Put the numbers back to the original stack in a reversed order
		while(!original.isEmpty())
			stack.push(original.pop());
	}
	
	/**
	 * Gets 2 sorted stack and returns a one combined sorted stack, runs in O(n) time
	 * @param stack1 The first sorted stack
	 * @param stack2 The second sorted stack
	 * @return A merged sorted stack
	 */
	public static MyStack mergeSortedStacks(MyStack stack1, MyStack stack2) {
		MyStack mergedStack = new MyStack();
		
		// Move numbers from the stacks to the merging stack until one of the stacks is empty, making it a descending order
		while(!stack1.isEmpty() && !stack2.isEmpty()) {
			if(stack1.peek() <= stack2.peek())
				mergedStack.push(stack1.pop());
			
			else 
				mergedStack.push(stack2.pop());
		}
		
		// Moves the remaining numbers from the first stack (if any) to the merged stack
		while(!stack1.isEmpty())
			mergedStack.push(stack1.pop());
		
		// Moves the remaining numbers from the second stack (if any) to the merged stack
		while(!stack2.isEmpty())
			mergedStack.push(stack2.pop());
		
		reverseStack(mergedStack); // reverses the stack to make it ascending order
		return mergedStack;
	}
	
	/**
	 * Gets a stack and sorts it in ascending order (lowest number on the top) using divide and conquer algorithm, runs in O(nLogn) running time
	 * @param stack The stack to sort
	 * @return Sorted stack
	 */
	public static MyStack sortStack(MyStack stack) {
		// Returns the stack if there is only one element in the stack
		if(stack.getLast() == 0)
			return stack;
		
		// Creating 2 stacks, each for half of the numbers in the original stack, to make it easier to solve
		MyStack firstHalf = new MyStack();
		MyStack secondHalf = new MyStack();
		
		// Divides the number from the original stack between the new stacks
		while(!stack.isEmpty()) {
			firstHalf.push(stack.pop());
			if(!stack.isEmpty())
				secondHalf.push(stack.pop());
		}
		
		return mergeSortedStacks(sortStack(firstHalf), sortStack(secondHalf)); // The recursive call to sort the 2 stacks
	}
	
	/**
	 * Gets a stack and a number and returns true if the number exists in the stack and removes the number, runs in O(n) time
	 * @param stack The stack to search
	 * @param num The number to search for
	 * @return True if the number exists in the stack, false otherwise
	 */
	public static boolean isElementExist(MyStack stack, int num) {
		MyStack tempStack = new MyStack();
		
		// Removes to a temporary stack and compares the top number of the stack until the stack is empty or the number is found
		while(!stack.isEmpty()) {
			if(num == stack.peek()) {
				stack.pop(); // Removes the number from the stack
				// Returns the numbers from the temporary to the original stack
				while(!tempStack.isEmpty())
					stack.push(tempStack.pop());
				return true;
			}
			
			tempStack.push(stack.pop());
		}
		
		while(!tempStack.isEmpty())
			stack.push(tempStack.pop());
		return false;
	}
	
	/**
	 * Moves the max number from the stack in index 0 to the top of the stack in index 1
	 * @param stacks The HStack object to manipulate
	 */
	public static void moveMaxFrom0To1(HStack stacks) {
		int counter = 0; // To count the number of numbers in the stack in index 0
		int max = stacks.peek(0);
		
		// Searches for the max number in the stack in index 0 while moving the numbers to the stack in index 2 and counting them
		while(!stacks.isEmpty(0)) {
			if(stacks.peek(0) > max)
				max = stacks.peek(0);
			
			stacks.move(0);
			stacks.move(1);
			counter++;
		}
		
		boolean moved = false; // To keep track if the max number is already moved (for case where there are 2 equal max numbers)
		
		// Moves the numbers back to the stack in index 0 and the max number from there to the stack in index 1
		while(counter > 0) {
			if(stacks.peek(2) == max && !moved) {
				stacks.move(2);
				stacks.move(0);
				moved = true;
			}
			
			else
				stacks.move(2);
			
			counter--;
		}
	}
	
	/**
	 * Combines and sorts the stacks from HStack objects to the stack in index 0 (empties the other stacks), runs in O(n^2) time
	 * @param stacks The HStack object to manipulate
	 */
	public static void sortAllToZero(HStack stacks) {
		// Moves all the numbers from the second stack to the first
		while(!stacks.isEmpty(1)) {
			stacks.move(1);
			stacks.move(2);
		}
		
		// Moves all numbers from the third stack to the first
		while(!stacks.isEmpty(2))
			stacks.move(2);
		
		// Moves all numbers from the first stack to the second, in ascending order
		while(!stacks.isEmpty(0))
			moveMaxFrom0To1(stacks);
		
		// Moves all the numbers from the second stack to the first, making them in ascending order
		while(!stacks.isEmpty(1))
			stacks.move(1);
		
		while(!stacks.isEmpty(2))
			stacks.move(2);
	}
}
