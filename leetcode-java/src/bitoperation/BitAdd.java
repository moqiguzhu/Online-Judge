package bitoperation;

/**
 * 
 * @author moqiguzhu
 * @date 2016-03-22
 * @version 1.0
 *
 */
public class BitAdd {
  public int bitAdd(int a, int b) {
    int res, carry;
    do {
      res = a ^ b;
      carry = (a & b) << 1;     // << 的优先级比 & 高
      a = res;
      b = carry;
    } while(carry != 0);
    
    return res;
  }
  
  public int bitSubtract(int a, int b) {
    // -b = ~b + 1
    return bitAdd(a, bitAdd(~b, 1));
  }
  
  public static void main(String[] args) {
    BitAdd ba = new BitAdd();
    System.out.println(ba.bitAdd(446, 123));
    
    System.out.println(ba.bitSubtract(123, 446));
  }
}
