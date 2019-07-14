package stack;

import java.util.LinkedList;
import java.util.Stack;

public class ValidParentheses {
  public boolean old_isValid(String s) {
    // 1代表( 
    //2代表[ 
    //3代表{ 
    //4代表) 
    //5代表] 
    //6代表}
    // 第一种边界情况
    if (s.length() == 0)
      return true;
    
    Stack<Integer> stack = new Stack<Integer>();
    char c;
    int temp;
    for (int i = 0; i < s.length(); i++) {
      c = s.charAt(i);
      if (c == '(')
        stack.add(1);
      else if (c == '[')
        stack.add(2);
      else if (c == '{')
        stack.add(3);
      else {
        // 第二种边界情况
        if (stack.empty())
          return false;
        if (c == ')')
          temp = 4;
        else if (c == ']')
          temp = 5;
        else
          temp = 6;
        if (stack.peek() != temp - 3)
          return false;
        else
          stack.pop();
      }
    }
    if (stack.isEmpty())
      return true;
    else
      return false;
  }
  
  public boolean new_isValid(String s) {
    if(s == null || s.length() == 0) {
      return true;
    }
    
    char c;
    int index = -1, len = s.length();
    LinkedList<Character> parentheses = new LinkedList<>();
    while(index++ < len-1) {
      c = s.charAt(index);
      if(c == '(' || c == '[' || c == '{') {
        parentheses.addLast(c);
      } else {
        if(parentheses.isEmpty()) {
          return false;
        }
        switch (c) {
          case ')':
            if(parentheses.getLast() != '(') {
              return false;
            } else {
              parentheses.removeLast();
            }
            break;
          case ']':
            if(parentheses.getLast() != '[') {
              return false;
            } else {
              parentheses.removeLast();
            }
            break;
          case '}':
            if(parentheses.getLast() != '{') {
              return false;
            } else {
              parentheses.removeLast();
            }
            break;
        }
      }
    }
    
    return parentheses.isEmpty();
  }

  public static void main(String[] args) {
    ValidParentheses vp = new ValidParentheses();
    String test = "][";
    System.out.print(vp.new_isValid(test));
  }
}
