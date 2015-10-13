package bitoperation;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/discuss/53646/simple-and-easy-to-understand-java-solution
 * 
 * https://leetcode.com/discuss/34918/one-line-c-solution
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-13
 */

public class BitwiseANDNumbersRange {
  public int naive_rangeBitwiseAnd(int m, int n) {
    int result = 0, cur = 0;
    int shiftBits1 = 0, shiftBits2 = 0;
    while (m != 0) {
      shiftBits1 = findHighestSignificantBit(m);
      shiftBits2 = findHighestSignificantBit(n);
      if (shiftBits1 != shiftBits2) {
        break;
      } else {
        cur = (1 << shiftBits1);
        result += cur;
        m -= cur;
        n -= cur;
      }
    }

    return result;
  }

  public int findHighestSignificantBit(int num) {
    for (int i = 0;; ++i) {
      num = num >> 1;
      if (num == 0) {
        return i;
      }
    }
  }

  // It's a problem that can be reduced to find the same prefix of the numbers in this range.
  public int elegant_rangeBitwiseAnd(int m, int n) {
    int diffBits = 0;
    while (m != n) {
      m >>= 1;
      n >>= 1;
      diffBits++;
    }
    return n << diffBits;
  }

  // 本质上和elegant版本的写法是相同的
  public int awesome_rangeBitwiseAnd(int m, int n) {
    return (n > m) ? (awesome_rangeBitwiseAnd(m / 2, n / 2) << 1) : m;
  }

  public List<Integer> createTestcases() {
    List<Integer> testcases = new ArrayList<>();

    testcases.add(5);
    testcases.add(7);

    testcases.add(100);
    testcases.add(103);

    testcases.add(3);
    testcases.add(4);

    testcases.add(0);
    testcases.add(0);

    return testcases;
  }

  public static void main(String[] args) {
    BitwiseANDNumbersRange bnr = new BitwiseANDNumbersRange();
    List<Integer> testcases = bnr.createTestcases();

    for (int i = 0; i < testcases.size() / 2; i++) {
      System.out
          .println(bnr.awesome_rangeBitwiseAnd(testcases.get(i * 2), testcases.get(i * 2 + 1)));
    }
  }
}
