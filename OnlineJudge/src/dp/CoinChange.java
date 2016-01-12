package dp;

import java.util.ArrayList;
import java.util.List;

/**
 * clean_coinChange version comes from
 * https://leetcode.com/discuss/76217/java-both-iterative-recursive-solutions-with-explanations
 * 
 * @author moqiguzhu
 * @date 2016-01-12
 * @version 1.0
 */
public class CoinChange {
  public int naive_coinChange(int[] coins, int amount) {
    if (coins == null || coins.length == 0 || amount < 0)
      return -1;
    if (amount == 0)
      return 0;

    double[][] nums = new double[amount + 1][coins.length + 1];

    for (int i = 0; i < amount + 1; i++) {
      nums[i][0] = Integer.MAX_VALUE;
    }

    for (int j = 0; j < coins.length; j++) {
      nums[0][j] = 0;
    }

    for (int i = 1; i < amount + 1; i++) {
      for (int j = 1; j < coins.length + 1; j++) {
        if (i < coins[j - 1])
          nums[i][j] = nums[i][j - 1];
        else {
          int count = 1;
          nums[i][j] = Math.min(nums[i - coins[j - 1]][j - 1] + count, nums[i][j - 1]);
          int remain = i - coins[j - 1];
          while (remain - coins[j - 1] >= 0) {
            nums[i][j] = Math.min(nums[i][j], nums[remain - coins[j - 1]][j - 1] + ++count);
            remain -= coins[j - 1];
          }
        }
      }
    }

    return nums[amount][coins.length] == Integer.MAX_VALUE ? -1 : (int) nums[amount][coins.length];
  }

  public int clean_coinChange(int[] coins, int amount) {
    if (amount < 1)
      return 0;
    return helper(coins, amount, new int[amount]);
  }

  private int helper(int[] coins, int rem, int[] count) { // rem: remaining coins after the last
                                                          // step; count[rem]: minimum number of
                                                          // coins to sum up to rem
    if (rem < 0)
      return -1; // not valid
    if (rem == 0)
      return 0; // completed
    if (count[rem - 1] != 0)
      return count[rem - 1]; // already computed, so reuse
    int min = Integer.MAX_VALUE;
    for (int coin : coins) {
      int res = helper(coins, rem - coin, count);
      if (res >= 0 && res < min)
        min = 1 + res;
    }
    count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
    return count[rem - 1];
  }

  public List<int[]> createTestcases() {
    List<int[]> testcases = new ArrayList<>();

    int[] coins1 = new int[] {2};
    testcases.add(coins1);

    return testcases;
  }

  public static void main(String[] args) {
    CoinChange cc = new CoinChange();
    List<int[]> testcases = cc.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(cc.naive_coinChange(testcases.get(i), 3));
    }
  }
}
