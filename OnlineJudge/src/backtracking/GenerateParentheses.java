package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 同样是backtracking的技术，实现不同，效率千差万别。
 *  ref:https://leetcode.com/discuss/55826/java-dfs-way-solution
 * 
 * @author moqiguzhu
 * @date 2015-12-01
 * @version 1.0
 *
 */

public class GenerateParentheses {
  private int n;

  public List<String> naive_generateParenthesis(int n) {
    this.n = n;
    return bt("");
  }

  public List<String> bt(String s) {
    List<String> result = new ArrayList<String>();
    if (unsafe(s)) {
      return result;
    }

    if (s.length() == 2 * n) {
      if (accept(s)) {
        result.add(s);
      }
      return result;
    }

    result.addAll(bt(s + "("));
    result.addAll(bt(s + ")"));

    return result;
  }

  public boolean unsafe(String s) {
    int count1 = 0, count2 = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        count1++;
      } else {
        count2++;
      }
      if (count2 > count1) {
        return true;
      }
    }
    return false;
  }

  public boolean accept(String s) {
    int count1 = 0, count2 = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        count1++;
      } else {
        count2++;
      }
      if (count2 > count1) {
        return false;
      }
    }
    if (count1 == count2 && s.length() == 2 * n)
      return true;
    else
      return false;
  }


  public List<String> elegant_generateParenthesis(int n) {
    List<String> list = new ArrayList<String>();
    generateOneByOne("", list, n, n);
    return list;
  }

  public void generateOneByOne(String sublist, List<String> list, int left, int right) {
    if (left > right) { // unsafe state
      return;
    }
    if (left > 0) { // generate next state
      generateOneByOne(sublist + "(", list, left - 1, right);
    }
    if (right > 0) {
      generateOneByOne(sublist + ")", list, left, right - 1);
    }
    if (left == 0 && right == 0) { // accept state
      list.add(sublist);
      return;
    }
  }

  public int[] createTestCases() {
    int[] testcases = new int[] {1, 2, 3};

    return testcases;
  }

  public static void main(String[] args) {
    GenerateParentheses gp = new GenerateParentheses();
    int[] testcases = gp.createTestCases();
    for (int i = 0; i < testcases.length; i++) {
      System.out.println(gp.elegant_generateParenthesis(testcases[i]));
    }
  }
}
