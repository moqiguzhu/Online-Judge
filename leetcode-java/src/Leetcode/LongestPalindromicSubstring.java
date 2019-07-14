package Leetcode;

import java.util.Arrays;

/**
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-23
 */
public class LongestPalindromicSubstring {

  /**
   * 描述：Given a string S, find the longest palindromic substring in S. You may assume that the
   * maximum length of S is 1000, and there exists one unique longest palindromic substring.
   * DP.时间复杂度是n^2
   * 
   * @param s 原字符串
   * @return 最长回文子串
   */
  public String longestPalindrome(String s) {
    int n = s.length();
    int longestBegin = 0;
    int maxLen = 1;
    boolean[][] table = new boolean[n][n];
    for (int i = 0; i < n; i++) {
      Arrays.fill(table[i], false);
    }
    for (int i = 0; i < n; i++) {
      table[i][i] = true;
    }

    for (int i = 0; i < n - 1; i++) {
      if (s.charAt(i) == s.charAt(i + 1)) {
        table[i][i + 1] = true;
        longestBegin = i;
        maxLen = 2;
      }
    }
    for (int len = 3; len <= n; len++) {
      for (int i = 0; i < n - len + 1; i++) {
        int j = i + len - 1;
        if (s.charAt(i) == s.charAt(j) && table[i + 1][j - 1]) {
          table[i][j] = true;
          longestBegin = i;
          maxLen = len;
        }
      }
    }

    return s.substring(longestBegin, longestBegin + maxLen);
  }

  String expandAroundCenter(String s, int c1, int c2) {
    int l = c1, r = c2;
    int n = s.length();
    while (l >= 0 && r <= n - 1 && s.charAt(l) == s.charAt(r)) {
      l--;
      r++;
    }
    return s.substring(l + 1, r);
  }

  /**
   * 
   * @param s 原字符串
   * @return 最长回文串
   */
  String longestPalindromeSimple(String s) {
    int n = s.length();
    if (n == 0)
      return "";
    String longest = s.substring(0, 1); // a single char itself is a palindrome
    for (int i = 0; i < n - 1; i++) {
      String p1 = expandAroundCenter(s, i, i);
      if (p1.length() > longest.length())
        longest = p1;

      String p2 = expandAroundCenter(s, i, i + 1);
      if (p2.length() > longest.length())
        longest = p2;
    }
    return longest;
  }

  /**
   * Manacher's algorithm http://www.felix021.com/blog/read.php?2040 时间复杂度是n
   * 
   * @param s 原字符串
   * @return 最长回文子串
   */
  public String manacher(String s) {
    if (s.length() < 2)
      return s;

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
      sb.append('#');
      sb.append(s.charAt(i));
    }
    sb.append('#');
    int mx = 0, id = 0;
    int[] p = new int[sb.length()];
    int index = 0, maxLen = 1;
    for (int i = 1; i < sb.length(); i++) {
      p[i] = mx > i ? Math.min(p[2 * id - i], mx - i) : 1;
      while (i - p[i] >= 0 && i + p[i] < sb.length() && sb.charAt(i + p[i]) == sb.charAt(i - p[i])) {
        p[i]++;
      }
      if (p[i] > maxLen) {
        index = i;
        maxLen = p[i];
      }
      if (i + p[i] > mx) {
        mx = i + p[i];
        id = i;
      }
    }

    StringBuffer result = new StringBuffer();
    for (int i = index - maxLen + 1; i < index + maxLen - 1; i++) {
      if (sb.charAt(i) != '#') {
        result.append(sb.charAt(i));
      }
    }

    return result.toString();
  }

  public static void main(String[] args) {
    LongestPalindromicSubstring lps = new LongestPalindromicSubstring();
    String string = "aaabaaa";
    System.out.println(lps.longestPalindromeSimple(string));
  }
}
