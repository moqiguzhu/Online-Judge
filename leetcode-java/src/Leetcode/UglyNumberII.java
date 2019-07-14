package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3,
 * 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
 * 
 * Note that 1 is typically treated as an ugly number.
 * 
 * DP
 * 
 * https://leetcode.com/discuss/58186/elegant-c-solution-o-n-space-time-with-detailed-explanation
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-09
 */
public class UglyNumberII {
  public int nthUglyNumber(int n) {
    int a = 0, b = 0, c = 0;
    List<Integer> table = new ArrayList<Integer>();
    table.add(1);
    while (table.size() < n) {
      int next_val = Math.min(table.get(a) * 2, Math.min(table.get(b) * 3, table.get(c) * 5));
      table.add(next_val);
      if (table.get(a) * 2 == next_val)
        a++;
      if (table.get(b) * 3 == next_val)
        b++;
      if (table.get(c) * 5 == next_val)
        c++;
    }
    return table.get(table.size() - 1);
  }
}
