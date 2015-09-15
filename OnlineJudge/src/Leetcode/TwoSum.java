package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 第二种写法不仅优雅，效率也更高。
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-15
 */
public class TwoSum {
  public int[] twoSum(int[] numbers, int target) {
    int[] result = new int[2];
    Set<Integer> set = new HashSet<>();
    int num1 = 0, num2 = 0;
    boolean flag = false;

    for (int i = 0; i < numbers.length; i++) {
      if (set.contains(numbers[i]) && 2 * numbers[i] == target) {
        flag = true;
        num1 = num2 = numbers[i];
      }
      set.add(numbers[i]);
    }

    if (!flag) {
      for (int i = 0; i < numbers.length; i++) {
        if (set.contains(target - numbers[i]) && 2 * numbers[i] != target) {
          num1 = numbers[i];
          num2 = target - numbers[i];
          break;
        }
      }
    }

    int temp = 0;
    for (int k = 0; k < numbers.length; k++) {
      if (numbers[k] == num1 || numbers[k] == num2) {
        result[temp++] = k + 1;
        if (temp >= 2) {
          break;
        }
      }
    }

    return result;
  }

  public int[] twoSum_better(int[] numbers, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < numbers.length; i++) {
      if (!map.containsKey(numbers[i]))
        map.put(target - numbers[i], i + 1);
      else
        return new int[] {map.get(numbers[i]), i + 1};
    }
    return new int[] {-1, -1};
  }

  public List<int[]> createTestcases() {
    List<int[]> testcases = new ArrayList<>();

    int[] test1 = {0, 3, 3, 0};
    testcases.add(test1);

    int[] test2 = {3, 2, 4};
    testcases.add(test2);

    return testcases;
  }

  public static void main(String[] args) {
    TwoSum ts = new TwoSum();
    List<int[]> testcases = ts.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(Arrays.toString(ts.twoSum(testcases.get(i), 6)));
    }
  }
}
