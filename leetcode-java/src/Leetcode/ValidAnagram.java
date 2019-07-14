package Leetcode;

import java.util.Arrays;

/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * 
 * For example, s = "anagram", t = "nagaram", return true. s = "rat", t = "car", return false.
 *
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-09
 */
public class ValidAnagram {
  public boolean isAnagram(String s, String t) {
    char[] ss = s.toCharArray();
    Arrays.sort(ss);
    char[] ts = t.toCharArray();
    Arrays.sort(ts);
   
    return new String(ss).equals(new String(ts));
  }
  
  public static void main(String[] args) {
    ValidAnagram va = new ValidAnagram();
    System.out.println(va.isAnagram("", ""));
  }
}
