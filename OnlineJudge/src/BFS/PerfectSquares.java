package BFS;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4,
 * 9, 16, ...) which sum to n.
 * 
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4
 * + 9.
 * 
 * dp is faster than BFS
 * 
 * 一个总结：https://leetcode.com/discuss/58056/summary-of-different-solutions-bfs-static-and-mathematics
 * 
 * 数学解法：https://leetcode.com/discuss/57066/4ms-c-code-solve-it-mathematically
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-12
 */
public class PerfectSquares {
  public int bfs_numSquares(int n) {
    LinkedList<Integer> queue = new LinkedList<>();
    Set<Integer> visited = new HashSet<>();
    int levels = 0;
    queue.add(n);
    visited.add(n);
    levels++;

    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int j = 0; j < size; j++) {
        int cur_n = queue.removeFirst();
        int tmp = (int) (Math.sqrt(cur_n));
        if (cur_n - tmp * tmp == 0) {
          return levels;
        }
        for (int i = tmp; i >= 1; i--) {
          if (!visited.contains(cur_n - i * i)) {
            queue.add(cur_n - i * i);
          }
        }
      }
      levels++;
    }

    return levels;
  }

  public int dp_numSquares(int n) {
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i <= n; ++i) {
      int min = Integer.MAX_VALUE;
      int j = 1;
      while (i - j * j >= 0) {
        min = Math.min(min, dp[i - j * j] + 1);
        ++j;
      }
      dp[i] = min;
    }
    return dp[n];
  }

  public static void main(String[] args) {
    PerfectSquares ps = new PerfectSquares();
    System.out.println(ps.bfs_numSquares(1535));
  }
}
