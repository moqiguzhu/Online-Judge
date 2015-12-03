package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author moqiguzhu
 * @date 2015-12-03
 * @version 1.0
 */

public class Combinations {
  private List<List<Integer>> result = new ArrayList<List<Integer>>();
  private int n, k;

  public List<List<Integer>> combine(int n, int k) {
    result.clear();
    this.n = n;
    this.k = k;
    generate(new ArrayList<Integer>(), 1);
    return result;
  }

  public void generate(List<Integer> list, int flag) {
    if (list.size() == k) {
      result.add(list);                                 // safe state
      return;
    }
    for (int i = flag; i <= n; i++) {
      List<Integer> newList = new ArrayList<Integer>(list);  // generate next states
      newList.add(i);
      generate(newList, i + 1);
    }
  }

  public List<Integer> createTestCases() {
    List<Integer> testcases = new ArrayList<Integer>();

    testcases.add(4);
    testcases.add(2);

    testcases.add(4);
    testcases.add(4);

    return testcases;
  }

  public static void main(String[] args) {
    Combinations c = new Combinations();
    List<Integer> testcases = c.createTestCases();
    for (int i = 0; i < testcases.size() / 2; i++) {
      System.out.println(c.combine(testcases.get(2 * i), testcases.get(2 * i + 1)));
    }
  }
}
