package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 回溯法的好处相比于DFS和BFS的优点在于不需要记录那么多状态信息 。处理重复的时候 我们当然可以使用HashSet之类现成的工具。 But, that's not the best!
 * 
 * @author moqiguzhu
 * @date 2015-12-12
 * @version 1.0
 *
 */

public class CombinationSumII {

  private int[] num;
  private List<List<Integer>> result;
  int len;

  public List<List<Integer>> combinationSum2(int[] num, int target) {
    this.num = num;
    Arrays.sort(this.num);
    this.len = num.length;
    result = new ArrayList<List<Integer>>();
    generate(0, target, new ArrayList<Integer>());
    return result;
  }

  public void generate(int flag, int target, List<Integer> list) {
    // 退出条件
    if (flag == len || target < num[flag]) {
      return;
    }

    for (int i = flag; i < len; i++) {
      // 为了避免重复
      if (i - 1 >= flag && num[i] == num[i - 1]) {
        continue;
      }

      List<Integer> newList = new ArrayList<Integer>(list);
      if (target == num[i]) {
        newList.add(num[i]);
        result.add(newList);
      } else if (target > num[i]) {
        newList.add(num[i]);
        generate(i + 1, target - num[i], newList);
      } else {
        break;
      }
    }
  }

  public List<int[]> createTestCases() {
    List<int[]> testcases = new ArrayList<int[]>();

    int[] num1 = {10, 1, 2, 7, 6, 1, 5};
    testcases.add(num1);

    return testcases;
  }

  public static void main(String[] args) {
    CombinationSumII cs2 = new CombinationSumII();
    List<int[]> testcases = cs2.createTestCases();
    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(cs2.combinationSum2(testcases.get(i), 22).toString());
    }
  }
}

