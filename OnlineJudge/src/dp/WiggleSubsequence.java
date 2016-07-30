package test;

/**
 * 
 * @author Administrator
 * @date 2016-07-30
 */
public class WiggleSubsequence {
    public int wiggleMaxLength(int[] nums) {
        if(nums == null) {
        	return 0;
        }
        if(nums.length < 2) {
        	return nums.length;
        }
        
        int last = nums[0];
        int count = 1;
        int flag = 0;
        for(int i = 1; i < nums.length; i++) {
        	if(nums[i] == last) {
        		continue;
        	} else {
        		if(flag == 0) {
        			if(nums[i] > last) {
        				// next time descending
        				flag = 1;
        			} else {
        				// next time ascending
        				flag = 2;
        			}
        			count++;
        			last = nums[i];
        		} else {
        			if(flag == 1) {
        				if(nums[i] < last) {
        					last = nums[i];
        					count++;
        					flag = 2;
        				} else {
        					last = nums[i];
        				}
        			}
        			if(flag == 2) {
        				if(nums[i] > last) {
        					last = nums[i];
        					count++;
        					flag = 1;
        				} else {
        					last = nums[i];
        				}
        			}
        		}
        	}
        }
        
        return count;
    }
    
    public static void main(String[] args) {
    	WiggleSubsequence ws = new WiggleSubsequence();
    	int[] nums = {0,0};
    	System.out.println(ws.wiggleMaxLength(nums));
	}
}
