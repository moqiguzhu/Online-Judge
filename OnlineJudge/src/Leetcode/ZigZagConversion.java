package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like
 * this: (you may want to display this pattern in a fixed font for better legibility)
 * 
 * P A H N A P L S I I G Y I R And then read line by line: "PAHNAPLSIIGYIR" Write the code that will
 * take a string and make this conversion given a number of rows:
 * 
 * string convert(string text, int nRows); convert("PAYPALISHIRING", 3) should return
 * "PAHNAPLSIIGYIR".
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-24
 */
public class ZigZagConversion {
  /**
   * 程序员的思维。
   */
  public String convert(String s, int numRows) {
    if (numRows == 1) {
      return s;
    }

    StringBuffer[] sbs = new StringBuffer[numRows];
    for (int i = 0; i < numRows; i++) {
      sbs[i] = new StringBuffer();
    }
    int row_index = 0, flag = 0;
    for (int i = 0; i < s.length(); i++) {
      if (row_index == numRows - 1) {
        flag = -1;
      }
      if (row_index == 0) {
        flag = 1;
      }
      sbs[row_index].append(s.charAt(i));
      row_index += flag;
    }
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < sbs.length; i++) {
      result.append(sbs[i].toString());
    }

    return result.toString();
  }

  /**
   * 非程序员的思维。这种程序可读性比较差。
   */
  public String convert_naive(String s, int nRows) {
    if (nRows == 1)
      return s;
    int len = s.length();
    StringBuffer sb = new StringBuffer();
    int distance;
    boolean flag;
    for (int i = 0; i < nRows; i++) {
      flag = true;
      for (int j = i; j < len;) {
        if (i == 0 || i == nRows - 1)
          distance = nRows + nRows - 2;
        else if (flag)
          distance = nRows + nRows - (i + 1) * 2;
        else
          distance = i * 2;
        sb.append(s.charAt(j));
        j += distance;
        flag = !flag;
      }
    }
    return sb.toString();
  }

  public List<String> createTestCases() {
    List<String> testcases = new ArrayList<String>();

    testcases.add("PAYPALISHIRING");
    testcases.add("");
    testcases.add("A");
    testcases.add("ABCDE");
    testcases.add("abcdefghijk");

    return testcases;
  }

  public static void main(String[] args) {
    ZigZagConversion zzc = new ZigZagConversion();
    List<String> testcases = zzc.createTestCases();
    int nRows = 3;
    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(zzc.convert(testcases.get(i), nRows));
    }
  }
}
