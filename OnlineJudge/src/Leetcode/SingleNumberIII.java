package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 题目描述： Given an array of numbers nums, in which exactly two elements appear only once and all the
 * other elements appear exactly twice. Find the two elements that appear only once.
 * 
 * For example:
 * 
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-18
 */
public class SingleNumberIII {
  /**
   * 
   * @param nums 输入数组
   * @return 只出现一次的两个数
   */
  public int[] singleNumber(int[] nums) {
    int[] result = new int[2];
    Set<Integer> set = new HashSet<>();
    for (int num : nums) {
      if (set.contains(num)) {
        set.remove(num);
      } else {
        set.add(num);
      }
    }
    int i = 0;
    for (int num : set) {
      result[i++] = num;
    }

    return result;
  }

  /**
   * 因为只出现一次的那两个数肯定是不同的，也就是说在它们的二进制表述中至少有一位是不同的，设为x。
   * 我们可以把输入数组中的所有数分成两组，一组x为1，一组x为0。然后对这两组中的元素分别进行异或运算。
   * 
   * @param nums 输入数组
   * @return 只出现一次的两个数
   */
  public int[] sophisticated_singleNumber(int[] nums) {
    // Pass 1 :
    // Get the XOR of the two numbers we need to find
    int diff = 0;
    for (int num : nums) {
      diff ^= num;
    }
    // Get its last set bit
    // diff &= -diff;
    int temp = 1;
    while (diff % 2 == 0) {
      diff /= 2;
      temp <<= 1;
    }
    diff = temp;

    // Pass 2 :
    int[] rets = {0, 0}; // this array stores the two numbers we will return
    for (int num : nums) {
      if ((num & diff) == 0) // the bit is not set
      {
        rets[0] ^= num;
      } else // the bit is set
      {
        rets[1] ^= num;
      }
    }
    return rets;
  }

  public List<int[]> createTestCases() {
    List<int[]> testcases = new ArrayList<>();

    int[] num1 = {1, 2, 1, 2, 3, 5};
    testcases.add(num1);
    
    int[] num2 = {1,1,2,3};
    testcases.add(num2);
    
    int[] num3 = {5,5,7,111};
    testcases.add(num3);

    return testcases;
  }

  public static void main(String[] args) {
    SingleNumberIII ssn = new SingleNumberIII();
    List<int[]> testcases = ssn.createTestCases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(Arrays.toString(ssn.sophisticated_singleNumber(testcases.get(i))));
    }
  }
}
