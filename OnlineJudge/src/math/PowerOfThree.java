package math;

/**
 * @author moqiguzhu
 * @date 2015-01-08
 * @version 1.0
 */
public class PowerOfThree {
  public boolean isPowerOfThree(int n) {
    if(n <= 0)
      return false;
    
    while(n != 1) {
      if(n % 3 != 0)
        return false;
      n /= 3;
    }
    return true;
  }
}
