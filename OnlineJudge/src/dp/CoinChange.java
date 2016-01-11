package dp;

public class CoinChange {
  //!!! need to debug
  public int coinChange(int[] coins, int amount) {
    if(coins == null || coins.length == 0 || amount < 0)
      return -1;
    if(amount == 0)
      return 0;
    
    int[][] nums = new int[amount+1][coins.length+1];
    
    for(int j = 0; j < coins.length; j++) {
      nums[0][j] = 0;
    }
    
    //!!!
    for(int i = 0; i < amount+1; i++) {
      nums[i][0] = Integer.MAX_VALUE;
    }
    
    for(int i = 1; i < amount+1; i++) {
      for(int j = 1; j < coins.length + 1; j++) {
        if(i < coins[j-1]) 
          nums[i][j] = nums[i][j-1];
        else {
          nums[i][j] = nums[i-coins[j-1]][j-1] + 1;
          int remain = i - coins[j-1];
          while(remain - coins[j-1] >= 0) {
            nums[i][j] = Math.min(nums[i][j], nums[remain-coins[j-1]][j-1] + 1);
            remain -= coins[j-1];
          }
        }
      }
    }
    
    return nums[amount][coins.length] == Integer.MAX_VALUE ? -1 : nums[amount][coins.length];
  }
}
