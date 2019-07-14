package miscs;

import java.util.ArrayList;
import java.util.List;

/**
 * 基本的分类讨论。关键是写代码的时候脑子要清楚。不要犯逻辑错误。
 * 
 * @author moqiguzhu
 * @date 2016-02-22
 * @version 1.0
 */

public class IncreasingTripletSubsequence {
  public boolean increasingTriplet(int[] nums) {
    if (nums == null || nums.length < 3) {
      return false;
    }
    boolean flag = false;

    int len1 = 0, len2 = 0;
    int[] arr1 = new int[3];
    int[] arr2 = new int[3];

    arr1[len1++] = nums[0];

    for (int i = 1; i < nums.length; i++) {
      if (len2 == 0) {
        if (len1 == 1) {
          if (nums[i] <= arr1[len1 - 1]) {
            arr1[0] = nums[i];
          } else {
            arr1[len1++] = nums[i];
          }
        } else {
          if (nums[i] > arr1[len1 - 1]) {
            flag = true;
            break;
          } else {
            if (nums[i] > arr1[len1 - 2]) {
              arr1[len1 - 1] = nums[i];
            } else {
              arr2[len2++] = nums[i];
            }
          }
        }
      } else {
        assert(len2 == 1);
        if (nums[i] > arr1[len1 - 1]) {
          flag = true;
          break;
        } else if (nums[i] > arr2[len2 - 1]) {
          arr2[len2++] = nums[i];
          if (arr2[len2 - 1] < arr1[len1 - 1]) {
            arr1[0] = arr2[0];
            arr1[1] = arr2[1];
          }
          len2 = 0;
        } else {
          arr2[len2 - 1] = nums[i];
        }
      }
    }

    return flag;
  }

  public List<int[]> createTestcases() {
    List<int[]> testcases = new ArrayList<>();

    int[] arr1 = new int[] {1, 2, 3, 4, 5};
    testcases.add(arr1);

    int[] arr2 = new int[] {5, 4, 3, 2, 1};
    testcases.add(arr2);

    int[] arr3 = new int[] {1, 5, 1, 2, 3};
    testcases.add(arr3);

    int[] arr4 = new int[] {1, 5, 2, 2, 6};
    testcases.add(arr4);

    int[] arr5 = new int[] {1, 2, 1, 2, 1, 2, 1, 2, 1, 2};
    testcases.add(arr5);

    return testcases;
  }

  public static void main(String[] args) {
    IncreasingTripletSubsequence its = new IncreasingTripletSubsequence();
    List<int[]> testcases = its.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(its.increasingTriplet(testcases.get(i)));
    }
  }
}
