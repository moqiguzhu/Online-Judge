package string;

/**
Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.

For example:

Given "aacecaaa", return "aaacecaaa".

Given "abcd", return "dcbabcd".

Credits:
Special thanks to @ifanchu for adding this problem and creating all test cases. Thanks to @Freezen for additional test cases.

Subscribe to see which companies asked this question
 */
import java.util.Arrays;

public class ShortestPalindrome {
	// memory out
	public String shortestPalindrome(String s) {
		StringBuilder sb = new StringBuilder(s);
		if (sb.reverse().toString().equals(s)) {
			return s;
		}
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
		int index = 0;
		for (int i = table.length - 1; i > -1; i--) {
			if (table[0][i]) {
				index = i;
				break;
			}
		}

		sb = new StringBuilder(s.substring(index + 1));

		return sb.reverse().toString() + s;
	}

	public String shortestPalindrome_elegant(String s) {
		int i = 0, end = s.length() - 1, j = end;
		char chs[] = s.toCharArray();
		while (i < j) {
			if (chs[i] == chs[j]) {
				i++;
				j--;
			} else {
				i = 0;
				end--;
				j = end;
			}
		}
		return new StringBuilder(s.substring(end + 1)).reverse().toString() + s;
	}

	public static void main(String[] args) {
		ShortestPalindrome sp = new ShortestPalindrome();
		System.out.println(sp.shortestPalindrome(""));
	}
}
