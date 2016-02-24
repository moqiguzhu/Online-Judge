package divideconquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * divide and conquer + bst
 * 注意：根据主定律，T(N) = 2T(N/2) + nlogn的时间复杂度依然是nlogn
 * @author moqiguzhu
 * @date 2016-02-24
 * @version 1.0
 *
 */
public class CountOfRangeSum {
  public int countRangeSum(int[] nums, int lower, int upper) {
    if(nums== null || nums.length == 0) {
      return 0;
    }
    
    return help(nums, 0, nums.length, lower, upper);
  }

  // left: inclusive
  // right: exclusive
  public int help(int[] nums, int left, int right, int lower, int upper) {
    int sum = 0;
    if (left >= right) {
      return 0;
    } 
    // base case
    if(right - left == 1) {
      return (nums[left] <= upper && nums[left] >= lower) ? 1 : 0;
    }

    int mid = left + ((right - left) / 2);
    sum += help(nums, left, mid, lower, upper); 
    sum += help(nums, mid, right, lower, upper);
    
    if(mid > left && right > mid) {
      int[] rightSub = new int[right - mid];
      rightSub = Arrays.copyOfRange(nums, mid, right);
      for(int i = 1; i < rightSub.length; i++) {
        rightSub[i] += rightSub[i-1];
      }
      Arrays.sort(rightSub);
      
      int[] leftSub = new int[mid - left];
      leftSub = Arrays.copyOfRange(nums, left, mid);
      for(int i = leftSub.length-2; i >= 0; i--) {
        leftSub[i] += leftSub[i+1]; 
      }
      
      for(int i = 0; i < leftSub.length; i++) {
        sum += numOfElementsInRange(rightSub, (double)lower-leftSub[i], (double)upper-leftSub[i]);
      }
    }
    
    return sum;
  }

  public int IndexMostLessOrEqual(int[] sortedArr, int left, int right, double target) {
    assert(right > left);
    while (left < right) {
      int mid = ((right - left) >> 1) + left;
      if (target < sortedArr[mid]) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return --left;
  }
  
  public int IndexLeastGreatOrEqual(int[] sortedArr, int left, int right, double target) {
    assert(right > left);
    while (right > left) {
      int mid = ((right - left) >> 1) + left;
      if (target <= sortedArr[mid]) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    return left;
  }
  
  int numOfElementsInRange(int[] sortedArr, double low, double high) {
    int low_index = IndexLeastGreatOrEqual(sortedArr, 0, sortedArr.length, low);
    int high_index = IndexMostLessOrEqual(sortedArr, 0, sortedArr.length, high);
    
    return high_index - low_index + 1;
  }
  
  public List<int[]> createTestcases() {
    List<int[]> testcases = new ArrayList<>();

    int[] arr1 = new int[] {1,4,-2,3,-4,3,0,-4,4};
    testcases.add(arr1);

    return testcases;
  }
  
  public static void main(String[] args) {
    CountOfRangeSum crs = new CountOfRangeSum();
    List<int[]> testcases = crs.createTestcases();
    
    for(int i = 0; i < testcases.size(); i++) {
      System.out.println(crs.countRangeSum(testcases.get(i), 3, 6));
    }
  }
}
