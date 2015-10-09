package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string of numbers and operators, return all possible results from computing all the
 * different possible ways to group numbers and operators. The valid operators are +, - and *.
 * 
 * 和算24点问题本质上是一致的。
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-09
 */
public class DifferentWaysAddParentheses {
  public List<Integer> diffWaysToCompute(String input) {
    List<Integer> tmp = new ArrayList<>();
    String regex = "\\D";
    String[] elements = new String[input.split(regex).length * 2 - 1];
    int prev = 0, index = 0;

    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (c == '+' || c == '-' || c == '*') {
        elements[index++] = input.substring(prev, i);
        elements[index++] = c + "";
        prev = i + 1;
      }
    }
    elements[elements.length - 1] = input.substring(prev, input.length());

    help(elements, tmp, 0, elements.length);
    return tmp;
  }

  public void help(String[] elements, List<Integer> list, int begin, int end) {
    if (begin + 1 == end) {
      list.add(Integer.valueOf(elements[begin]));
    }

    for (int i = begin; i < end - 2; i += 2) {
      List<Integer> left = new ArrayList<>();
      List<Integer> right = new ArrayList<>();
      help(elements, left, begin, i + 1);
      help(elements, right, i + 2, end);

      for (int j = 0; j < left.size(); j++) {
        for (int k = 0; k < right.size(); k++) {
          switch (elements[i + 1]) {
            case "+":
              list.add(left.get(j) + right.get(k));
              break;
            case "-":
              list.add(left.get(j) - right.get(k));
              break;
            case "*":
              list.add(left.get(j) * right.get(k));
              break;
          }
        }
      }
    }
  }

  public static void main(String[] args) {
    DifferentWaysAddParentheses dwap = new DifferentWaysAddParentheses();
    System.out.println(dwap.diffWaysToCompute("2*3-4*5"));
  }
}
