package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/discuss/48726/easy-java-code-of-backtracking-within-16-lines
 * 
 * @author moqiguzhu
 * @date 2015-12-16
 * @version 1.0
 *
 */

public class RestoreIPAddresses {
  public List<String> restoreIpAddresses(String s) {
    List<String> res = new ArrayList<>();
    helper(s, "", res, 0);
    return res;
  }

  public void helper(String s, String tmp, List<String> res, int n) {
    if (n == 4) {
      if (s.length() == 0)
        res.add(tmp.substring(0, tmp.length() - 1));
      // substring here to get rid of last '.'
      return;
    }
    for (int k = 1; k <= 3; k++) {
      if (s.length() < k) {
        continue;
      }
      int val = Integer.parseInt(s.substring(0, k));
      if (val > 255 || k != String.valueOf(val).length()) {
        continue;
      }
      /* in the case 010 the parseInt will return len=2 where val=10, but k=3, skip this. */
      helper(s.substring(k), tmp + s.substring(0, k) + ".", res, n + 1);
    }
  }

  public List<String> createTestCases() {
    List<String> testcases = new ArrayList<String>();

    String s1 = "25525511135";
    testcases.add(s1);

    String s2 = "2222";
    testcases.add(s2);

    String s3 = "222222";
    testcases.add(s3);

    String s4 = "010010";
    testcases.add(s4);

    return testcases;
  }

  public static void main(String[] args) {
    RestoreIPAddresses ripa = new RestoreIPAddresses();
    List<String> testcases = ripa.createTestCases();
    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(ripa.restoreIpAddresses(testcases.get(i).toString()));
    }
  }
}
