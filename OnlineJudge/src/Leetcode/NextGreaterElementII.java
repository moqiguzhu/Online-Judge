package Leetcode;

import java.util.Arrays;

public class NextGreaterElementII {
    public int[] nextGreaterElements(int[] nums) {
        int[] tmp = new int[nums.length * 2];
        for(int i = 0; i < tmp.length; i++) {
        	tmp[i] = (i >= nums.length ? nums[i-nums.length] : nums[i]);
        }
        
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
        	res[i] = help(tmp, i);
        }
        
        for(int i = 0; i < res.length; i++) {
        	if(res[i] != -1) {
            	if(res[i] >= nums.length) {
            		res[i] = nums[res[i]-nums.length];
            	} else {
            		res[i] = nums[res[i]];
            	}
        	}
        }
        
        return res;
    }
    
    public int help(int[] data, int begin) {
    	int index = begin+1;
    	while(true) {
    		if(index >= data.length) {
        		return -1;
        	}
    		if(data[index] > data[begin]) {
    			return index;
    		}
    		index++;
    	}
    }
    
    public static void main(String[] args) {
    	NextGreaterElementII nge = new NextGreaterElementII();
    	
    	int[] nums = {1,2,1};
    	
    	System.out.println(Arrays.toString(nge.nextGreaterElements(nums)));
	}
}
