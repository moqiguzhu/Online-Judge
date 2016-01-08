package stack;

import java.util.LinkedList;

/**
 * @author moqiguzhu
 * @date 2016-01-08
 * @version 1.0
 */
public class ImplementStackUsingQueues {
  LinkedList<Integer> queue1 = new LinkedList<>();
  LinkedList<Integer> queue2 = new LinkedList<>();
  
  // Push element x onto stack.
  public void push(int x) {
    queue1.addLast(x);
  }

  // Removes the element on top of the stack.
  public void pop() {
    while(queue1.size() != 1) {
      queue2.addLast(queue1.removeFirst());
    }
    queue1.clear();
    LinkedList<Integer> tmp;
    tmp = queue1;
    queue1 = queue2;
    queue2 = tmp;
  }

  // Get the top element.
  public int top() {
    int t = -1;
    while(queue1.size() > 0) {
      t = queue1.removeFirst();
      queue2.addLast(t);
    }
    LinkedList<Integer> tmp;
    tmp = queue1;
    queue1 = queue2;
    queue2 = tmp;
    
    return t;
  }

  // Return whether the stack is empty.
  public boolean empty() {
      return queue1.isEmpty();
  }
  
  public static void main(String[] args) {
    ImplementStackUsingQueues mystack = new ImplementStackUsingQueues();
    
    mystack.push(1);
    mystack.push(2);
    mystack.push(3);
    
    System.out.println(mystack.top());
    mystack.pop();
    System.out.println(mystack.top());
    mystack.pop();
    System.out.println(mystack.empty());
    mystack.pop();
    System.out.println(mystack.empty());
  }
}
