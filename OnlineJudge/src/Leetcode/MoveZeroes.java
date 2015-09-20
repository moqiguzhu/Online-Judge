package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用一个index变量记录当前非0的元素个数。
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-19
 */

public class MoveZeroes {
  public void moveZeroes(int[] nums) {
    int index = 0;
    for (int i = 0; i < nums.length; i++) {
      nums[index] = nums[i];
      if (nums[i] != 0) {
        index++;
      }
    }
    for (int i = index; i < nums.length; i++) {
      nums[i] = 0;
    }
  }

  public List<int[]> createTestcases() {
    List<int[]> testcases = new ArrayList<>();

    int[] nums1 = {0, 1, 0, 3, 12};
    testcases.add(nums1);

    return testcases;
  }

  public static void main(String[] args) {
    MoveZeroes mz = new MoveZeroes();
    List<int[]> testcases = mz.createTestcases();
    for (int i = 0; i < testcases.size(); i++) {
      mz.moveZeroes(testcases.get(i));
      System.out.println(Arrays.toString(testcases.get(i)));
    }
  }
}

