package Leetcode;

/**
 * 题目描述： You are a product manager and currently leading a team to develop a new product.
 * Unfortunately, the latest version of your product fails the quality check. Since each version is
 * developed based on the previous version, all the versions after a bad version are also bad.
 * 
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which
 * causes all the following ones to be bad.
 * 
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to
 * the API.
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-09-20
 */
public class FirstBadVersion {
  /**
   * bad version API
   * 
   * @param version 版本编号
   * @return version的版本是不是bad
   */
  public boolean isBadVersion(int version) {
    return false;
  }
  
  public int firstBadVersion(int n) {
    return binarySearch(1, n);
  }

  /**
   * 
   * @param left 左边界
   * @param right 右边界
   * @return 第一个坏的版本的编号
   */
  public int binarySearch(int left, int right) {
    if (left == right) {
      return left;
    }
    int mid = left + ((right - left) >> 1);
    if (isBadVersion(mid)) {
      return binarySearch(left, mid);
    } else {
      return binarySearch(mid + 1, right);
    }
  }

}
