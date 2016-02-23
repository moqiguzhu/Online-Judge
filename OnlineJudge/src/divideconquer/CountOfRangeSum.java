package divideconquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// divide and conquer + bst
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

  // return largest index which p[index] <= e 
  int binsearch(int[] sortedArr, int lo, int hi, double e) {
    assert(hi > lo);
    while (lo < hi) {
      int mi = ((hi - lo) >> 1) + lo;
      if (e < sortedArr[mi]) {
        hi = mi;
      } else {
        lo = mi + 1;
      }
    }
    return --lo;
  }
  
  int numOfElementsInRange(int[] sortedArr, double low, double high) {
    int low_index = binsearch(sortedArr, 0, sortedArr.length, low);
    int high_index = binsearch(sortedArr, 0, sortedArr.length, high);
    while(low_index >= 0 && sortedArr[low_index] == low) low_index--;
    
    return high_index - low_index;
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
