package dp;

/**
 * 多用程序员的思维去思考问题
 * 逻辑思维不行，想不到
 * @author Administrator
 *
 */
public class BurstBalloons {
    // dp solution
	// different kinds of dp
	public int maxCoins(int[] nums) {
		int n = nums.length;
        int[] new_nums = new int[nums.length+2];
        
        new_nums[0] = 1;
        for(int i = 1; i < new_nums.length-1; i++) {
        	new_nums[i] = nums[i-1];
        }
        new_nums[new_nums.length-1] = 1;
        
		int[][] table = new int[n+2][n+2];
        
        
        for(int len = 0; len < n; len++) {
        	for(int i = 1; i < n+1; i++) {
        		int j = Math.min(i+len, n);
        		for(int k = i; k <= j; k++) {
        			table[i][j] = Math.max(table[i][j], table[i][k-1] + 
        					new_nums[i-1]*new_nums[k]*new_nums[j+1] + table[k+1][j]);
        		}
        	}
        }
        
        return table[1][n];
    }
	
	
	public static void main(String[] args) {
		BurstBalloons bb = new BurstBalloons();
		int[] nums = {3,1,5,8};
		System.out.println(bb.maxCoins(nums));
	}
}
