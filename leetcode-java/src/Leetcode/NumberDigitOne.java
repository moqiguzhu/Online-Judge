package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 每次迭代将数据分成三部分，cur表示当前位的数字，pre表示当前位前面的数，suf表示当前位后面的数。
 * 
 * @author moqiguzhu
 * @date 2015-10-29
 * @version 1.0
 */
public class NumberDigitOne {
  public int countDigitOne(int n) {
    int numOneBits = 0;
    long cur = -1, suf = -1, cnt = 1, tmp = n;
    long n_copy = n;
    long pre = n > 0 ? -1 : 0;
    while (pre != 0) {
      cnt *= 10;
      suf = n_copy % (cnt / 10);
      pre = n_copy / cnt;
      cur = tmp % 10;
      tmp /= 10;
      numOneBits += (pre * cnt / 10);
      if (cur == 1)
        numOneBits += (suf + 1);
      if (cur > 1)
        numOneBits += cnt / 10;

    }

    return numOneBits;
  }

  public List<Integer> createTestcases() {
    List<Integer> testcases = new ArrayList<>();

    testcases.add(0);
    testcases.add(1);
    testcases.add(10);
    testcases.add(13);
    testcases.add(99);
    testcases.add(100);
    testcases.add(19);
    testcases.add(20);
    testcases.add(1410065408);

    return testcases;
  }

  public static void main(String[] args) {
    NumberDigitOne ndo = new NumberDigitOne();
    List<Integer> testcases = ndo.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(ndo.countDigitOne(testcases.get(i)));
    }
  }
}
