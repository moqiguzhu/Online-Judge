package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/discuss/58647/line-simple-java-bit-manipulate-solution-with-explaination
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-09
 */
public class MissingNumber {
  public int naive_missingNumber(int[] nums) {
    if (nums.length == 0) {
      return 0;
    }
    int len = nums.length;
    int left = 0, right = len;
    while (left < right) {
      int mid = left + ((right - left) >> 1);
      int tmp = count(nums, mid);
      
      if(tmp < mid+1) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    return left;
  }

  public int count(int[] nums, int num) {
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] <= num) {
        count++;
      }
    }

    return count;
  }

  /**
   * The basic idea is to use XOR operation. We all know that a^b^b =a, which means two xor
   * operations with the same number will eliminate the number and reveal the original number. In
   * this solution, I apply XOR operation to both the index and value of the array. In a complete
   * array with no missing numbers, the index and value should be perfectly corresponding(
   * nums[index] = index), so in a missing array, what left finally is the missing number.
   * 
   * @param nums
   * @return
   */
  public int elegant_missingNumber(int[] nums) {

    int xor = 0, i = 0;
    for (i = 0; i < nums.length; i++) {
      xor = xor ^ i ^ nums[i];
    }

    return xor ^ i;
  }

  // binary search
  public int bi_missingNumber(int[] nums) {
    Arrays.sort(nums);
    int left = 0, right = nums.length, mid = (left + right) / 2;
    while (left < right) {
      mid = (left + right) / 2;
      if (nums[mid] > mid)
        right = mid;
      else
        left = mid + 1;
    }
    return left;
  }
  
  // sum
  public int sum_missingNumber(int[] nums) { // sum
    int len = nums.length;
    int sum = (0 + len) * (len + 1) / 2;
    for (int i = 0; i < len; i++)
      sum -= nums[i];
    return sum;
  }

  public List<int[]> createTestcases() {
    List<int[]> testcases = new ArrayList<>();

    int[] nums1 = {0, 1, 3};
    testcases.add(nums1);

    int[] nums2 = {0};
    testcases.add(nums2);

    return testcases;
  }


  public static void main(String[] args) {
    MissingNumber mn = new MissingNumber();
    List<int[]> testcases = mn.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(mn.naive_missingNumber(testcases.get(i)));
    }
  }
}
