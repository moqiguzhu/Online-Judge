package stack;

import java.util.LinkedList;

/**
 * 描述：Implement the following operations of a queue using stacks.
 * 
 * push(x) -- Push element x to the back of queue. pop() -- Removes the element from in front of
 * queue. peek() -- Get the front element. empty() -- Return whether the queue is empty. Notes: You
 * must use only standard operations of a stack -- which means only push to top, peek/pop from top,
 * size, and is empty operations are valid. Depending on your language, stack may not be supported
 * natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you
 * use only standard operations of a stack. You may assume that all operations are valid (for
 * example, no pop or peek operations will be called on an empty queue).
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-25
 */
public class ImplementQueueUsingStacks {
  private LinkedList<Integer> stack1 = new LinkedList<>();
  private LinkedList<Integer> stack2 = new LinkedList<>();

  // Push element x to the back of queue.
  public void push(int x) {
    stack1.addLast(x);
  }

  // Removes the element from in front of queue.
  public void pop() {
    peek();
    stack2.removeLast();
  }

  // Get the front element.
  public int peek() {
    if (stack2.size() == 0) {
      while (!stack1.isEmpty()) {
        stack2.addLast(stack1.removeLast());
      }
    }
    return stack2.peekLast();
  }

  // Return whether the queue is empty.
  public boolean empty() {
    return stack1.isEmpty() && stack2.isEmpty();
  }
}
