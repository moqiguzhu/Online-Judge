package bitoperation;

/**
 * https://leetcode.com/discuss/55684/the-fastest-c-solution-o-log-n-time-o-1-space
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-13
 */
public class NumberofOneBits {
  // time complexity is 32
  public int naive_hammingWeight(int n) {
    int numOnebits = 0;
    int count = 0;
    while (count++ < 32) {
      numOnebits += (n & 1);
      n >>= 1;
    }

    return numOnebits;
  }

  // time complexity is number of 1 bits in the n and this number will always less than or equal 32
  public int elegant_hammingWeight(int n) {
    int res = 0;
    while (n != 0) {
      n = n & (n - 1);
      res++;
    }
    return res;
  }

}
