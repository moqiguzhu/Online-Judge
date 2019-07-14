package dp;

import java.util.Arrays;

public class CombinationSumIV {
    public int combinationSum4(int[] nums, int target) {
        if(target <= 0) {
        	return 0;
        }
        
        Arrays.sort(nums);
        
        int[] count = new int[target+1];
        count[0] = 1;
        for(int i = 1; i <= target; i++) {
        	for(int x : nums) {
        		if(x <= i) {
        			count[i] += count[i-x];
        		} else {
        			break;
        		}
        	}
        }
        
        return count[target];
    }
    
    public static void main(String[] args) {
    	CombinationSumIV cs = new CombinationSumIV();
    	int[] nums = {1,2,3};
    	int target = 4;
    	System.out.println(cs.combinationSum4(nums, target));
	}
}
