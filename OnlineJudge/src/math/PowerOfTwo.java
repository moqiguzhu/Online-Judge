package math;

/**
 * @author moqiguzhu
 * @date 2015-01-08
 * @version 1.0
 */
public class PowerOfTwo {
  public boolean isPowerOfTwo(int n) {
    return n > 0 && numberOfOneBits(n) == 1;
  }
  
  public int numberOfOneBits(int n) {
    int count = 0;
    while(n != 0) {
      count += (n & 1);
      n >>>= 1;
    }
    return count;
  }
}
