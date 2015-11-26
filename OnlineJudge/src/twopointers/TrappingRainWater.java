package twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author moqiguzhu
 * @date 2015-11-26
 * @version 1.0
 * 
 * 
 */

public class TrappingRainWater {
  /**
   * 
   * @param height
   * @return height数组中能够贮存的水的数量
   */
  public int naive_trap(int[] height) {
    int waterSum = 0;

    // ========边界条件=========
    if (height == null || height.length < 3) {
      return waterSum;
    }

    // =========最大高度========
    int maxHeight = height[0];
    int maxHeightIndex = 0;
    for (int i = 0; i < height.length; i++) {
      if (height[i] > maxHeight) {
        maxHeight = height[i];
        maxHeightIndex = i;
      }
    }

    // ==========处理左边部分=======
    int begin = 0;
    for (int i = 0; i <= maxHeightIndex; i++) {
      if (height[i] >= height[begin]) {
        waterSum += getTrappingWater(height, begin, i);
        begin = i;
      }
    }

    // =========处理右边部分=========
    int end = height.length - 1;
    for (int i = height.length - 1; i >= maxHeightIndex; i--) {
      if (height[i] >= height[end]) {
        waterSum += getTrappingWater(height, i, end);
        end = i;
      }
    }

    return waterSum;
  }

  /**
   * 
   * @param height
   * @param begin 开始下标(inclusive)
   * @param end 结束下标(inclusive)
   * @return height数组中从下标begin到下标end这段能够贮存的水的数量
   */
  public int getTrappingWater(int[] height, int begin, int end) {
    int sum = 0, boundHeight = Math.min(height[begin], height[end]);

    for (int i = begin + 1; i < end; i++) {
      sum += boundHeight - height[i];
    }

    return sum;
  }

  /**
   * https://leetcode.com/discuss/63606/very-concise-java-solution-no-stack-with-explanations
   */
  public int nice_trap(int[] height) {
    int secHight = 0;
    int left = 0;
    int right = height.length - 1;
    int area = 0;
    while (left < right) {
      if (height[left] < height[right]) {
        secHight = Math.max(height[left], secHight);
        area += secHight - height[left];
        left++;
      } else {
        secHight = Math.max(height[right], secHight);
        area += secHight - height[right];
        right--;
      }
    }
    return area;
  }

  /**
   * 
   * @return 测试案列
   */
  public List<int[]> createTestCases() {
    List<int[]> testcases = new ArrayList<int[]>();

    int[] A1 = {};
    testcases.add(A1);

    int[] A2 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
    testcases.add(A2);

    int[] A3 = {0, 1, 0, 2};
    testcases.add(A3);

    int[] A4 = {1, 4, 2, 6, 8, 4, 0, 3, 1};
    testcases.add(A4);

    int[] A5 = {1, 2, 3, 4, 5};
    testcases.add(A5);

    int[] A6 = {5, 4, 3, 2, 1};
    testcases.add(A6);

    int[] A7 = {5, 2, 1, 2, 1, 5};
    testcases.add(A7);

    int[] A8 = {6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3};
    testcases.add(A8);

    return testcases;
  }

  public static void main(String[] args) {
    TrappingRainWater trw = new TrappingRainWater();
    List<int[]> testcases = trw.createTestCases();
    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(trw.naive_trap(testcases.get(i)));
    }
  }
}
