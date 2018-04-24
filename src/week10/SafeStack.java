package week10;

import java.util.Stack;

public class SafeStack<E> {
	public Stack<E> stack = new Stack<E>(); // Stack is already thread-safe.
	
	// Stack synchronizes on the functions not the object.
	public synchronized void pushIfNotFull(E e){
//		synchronized(stack){
			if (stack.capacity() > stack.size())
				stack.push(e);
//		}
	}
	
	public synchronized void popIfNotEmpty(){
		// synchronized(stack){
			if (!stack.isEmpty()){
				stack.pop();
			}
		// }
	}
}
