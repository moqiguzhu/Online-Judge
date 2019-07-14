package stack;

import java.util.LinkedList;

/**
 * 
 * @author moqiguzhu
 * @date 2015-12004
 * @version 1.0
 */
public class MinStack {
  private LinkedList<Integer> elements = new LinkedList<>();
  private LinkedList<Integer> minOfPrecursors = new LinkedList<>();

  public void push(int x) {
    elements.addLast(x);
    if (minOfPrecursors.isEmpty() || minOfPrecursors.getLast() > x) {
      minOfPrecursors.addLast(x);
    } else {
      minOfPrecursors.addLast(minOfPrecursors.getLast());
    }
  }

  public void pop() {
    elements.removeLast();
    minOfPrecursors.removeLast();
  }

  public int top() {
    return elements.getLast();
  }

  public int getMin() {
    return minOfPrecursors.getLast();
  }
}
