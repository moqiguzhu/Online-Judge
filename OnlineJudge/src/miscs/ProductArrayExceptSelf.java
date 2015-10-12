package miscs;

/**
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is
 * equal to the product of all the elements of nums except nums[i].
 * 
 * Solve it without division and in O(n).
 * 
 * For example, given [1,2,3,4], return [24,12,8,6].
 * 
 * Follow up: Could you solve it with constant space complexity? (Note: The output array does not
 * count as extra space for the purpose of space complexity analysis.)
 * 
 * 
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-12
 */
public class ProductArrayExceptSelf {
  public int[] productExceptSelf(int[] nums) {
    int len = nums.length;

    if (len < 2) {
      return nums;
    }

    int[] result = new int[len];

    result[0] = nums[0];
    for (int i = 1; i < len; i++) {
      result[i] = result[i - 1] * nums[i];
    }

    int curSuffix = 1;
    for (int i = nums.length - 1; i > 0; i--) {
      result[i] = result[i - 1] * curSuffix;
      curSuffix *= nums[i];
    }
    result[0] = curSuffix;

    return result;
  }
}
