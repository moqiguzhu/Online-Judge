package twopointers;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author moqiguzhu
 * @date 2015-11-25
 * @version 1.0
 */

public class ContainerWithMostWater {
  public int maxArea(int[] height) {
    int max = 0;
    int front = 0, tail = height.length - 1;
    int temp;
    
    while (tail > front) {
      temp = (tail - front) * Math.min(height[front], height[tail]);
      if (temp > max) {
        max = temp;
      }
      if (height[front] > height[tail]) {
        tail--;
      } else {
        front++;
      }
    }
    return max;
  }

  public List<int[]> createTestCases() {
    List<int[]> testcases = new ArrayList<int[]>();

    int[] height1 = {};
    testcases.add(height1);

    int[] height2 = {1};
    testcases.add(height2);

    int[] height3 = {1, 2};
    testcases.add(height3);

    int[] height4 = {2, 1, 3, 6, 3, 9};
    testcases.add(height4);

    return testcases;
  }

  public static void main(String[] args) {
    ContainerWithMostWater cwm = new ContainerWithMostWater();
    List<int[]> testcases = cwm.createTestCases();
    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(cwm.maxArea(testcases.get(i)));
    }
  }
}
