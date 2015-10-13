package bitoperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 不要使用加法，全部位操作。
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-13
 */

public class ReverseBits {
  public int reverseBits(int n) {
    int result = 0;
    int count = 0;
    // n & 1取出最低位
    while(count++ < 32) {
      result = (result<<1) | (n & 1);
      n = n >> 1;
    }
    return result;
  }

  public List<Integer> createTestcases() {
    List<Integer> testcases = new ArrayList<>();

    testcases.add(-2147483648);

    return testcases;
  }

  public static void main(String[] args) {
    ReverseBits rb = new ReverseBits();
    List<Integer> testcases = rb.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(rb.reverseBits(testcases.get(i)));
    }
  }
}
