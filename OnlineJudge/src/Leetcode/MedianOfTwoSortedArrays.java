package Leetcode;

import java.util.Arrays;

public class MedianOfTwoSortedArrays {
  public double findMedianSortedArrays(int A[], int B[]) {
    return help(A, A.length, B, B.length);
  }

  public double help(int A[], int m, int B[], int n) {
    int l = (m + n + 1) >> 1;
    int r = (m + n + 2) >> 1;
    return (getkth(A, m, B, n, l) + getkth(A, m, B, n, r)) / 2.0;
  }

  public int getkth(int s[], int m, int l[], int n, int k) {
    // let m <= n
    if (m > n)
      return getkth(l, n, s, m, k);
    if (m == 0)
      return l[k - 1];
    if (k == 1)
      return Math.min(s[0], l[0]);

    int i = Math.min(m, k / 2), j = Math.min(n, k / 2);
    if (s[i - 1] > l[j - 1])
      return getkth(s, m, Arrays.copyOfRange(l, j, l.length), n - j, k - j);
    else
      return getkth(Arrays.copyOfRange(s, i, s.length), m - i, l, n, k - i);
  }
}
