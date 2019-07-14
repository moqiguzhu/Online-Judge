package dp;

/**
 * @author moqiguzhu
 * @date 2016-01-07
 * @version 1.0
 */
public class NumArray {
  private int[] nums;
  private double[] sumOfPrecussors;
  public NumArray(int[] nums) {
    this.nums = nums;
    sumOfPrecussors = new double[nums.length];
    
    for(int i = 1; i < nums.length; i++) {
      sumOfPrecussors[i] = sumOfPrecussors[i-1] + nums[i-1];
    }
  }

  public int sumRange(int i, int j) {
    return (int)(sumOfPrecussors[j] - sumOfPrecussors[i] + nums[j]);
  }
  
  public static void main(String[] args) {
    NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
    System.out.println(numArray.sumRange(0, 1));
    System.out.println(numArray.sumRange(1, 5));
  }
}
