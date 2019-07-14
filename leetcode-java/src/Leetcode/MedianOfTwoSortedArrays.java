package Leetcode;

import java.util.Arrays;

/**
 * 描述：There are two sorted arrays nums1 and nums2 of size m and n respectively. Find the median of
 * the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-21
 */

public class MedianOfTwoSortedArrays {
  /**
   * 
   * @param A 第一个数组
   * @param B 第二个数组
   * @return 两个数组的中位数
   */
  public double findMedianSortedArrays(int A[], int B[]) {
    return help(A, A.length, B, B.length);
  }

  /**
   * 
   * @param A 第一个排序好的数组
   * @param m 第一个数组的长度
   * @param B 第二个排序好的数组
   * @param n 第二个数组的长度
   * @return 两个数组的中位数
   */
  public double help(int A[], int m, int B[], int n) {
    int l = (m + n + 1) >> 1;
    int r = (m + n + 2) >> 1;
    return (getkth(A, m, B, n, l) + getkth(A, m, B, n, r)) / 2.0;
  }

  /**
   * 
   * @param s 较短数组
   * @param m 较短数组的长度
   * @param l 较长数组
   * @param n 较长数组的长度
   * @param k 第k大的数
   * @return 两个数组第k大的数
   */
  public int getkth(int s[], int m, int l[], int n, int k) {
    // let m <= n
    if (m > n)
      return getkth(l, n, s, m, k);
    if (m == 0)
      return l[k - 1];
    if (k == 1)
      return Math.min(s[0], l[0]);

    // n > k
    int i = Math.min(m, k / 2), j = k / 2;
    if (s[i - 1] > l[j - 1])
      return getkth(s, m, Arrays.copyOfRange(l, j, l.length), n - j, k - j);
    else
      return getkth(Arrays.copyOfRange(s, i, s.length), m - i, l, n, k - i);
  }
}
