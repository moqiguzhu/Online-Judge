package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestDivisibleSubsets {
    public List<Integer> largestDivisibleSubset(int[] nums) {
    	//边界
    	if(nums == null) {
    		return null;
    	}
    	if(nums.length == 0) {
    		return new ArrayList<>();
    	}
    	Arrays.sort(nums);
    	int[] count = new int[nums.length];
    	int[] prefixes = new int[nums.length];
    	
    	count[0] = 1;
    	prefixes[0] = 0;
    	for(int i = 1; i < nums.length; i++) {
    		int j = i;
    		int maxIndex = i;
    		int maxCount = 1;
    		while(--j >= 0) {
    			if(nums[i] % nums[j] == 0) {
    				if(1+count[j] > maxCount) {
    					maxIndex = j;
    					maxCount = 1 + count[j];
    				}
    			}
    		}
    		count[i] = maxCount;
    		prefixes[i] = maxIndex;
    	}
    
        List<Integer> res = new ArrayList<>();
        
        int globalIndex = -1;
        int curCount = 0;
        for(int i = 0; i < nums.length; i++) {
        	if(count[i] > curCount) {
        		globalIndex = i;
        		curCount = count[i];
        	}
        }
        
        while(prefixes[globalIndex] != globalIndex) {
        	res.add(nums[globalIndex]);
        	globalIndex = prefixes[globalIndex];
        }
        res.add(nums[globalIndex]);
        
        Collections.reverse(res);
        return res;
    }
    
    public static void main(String[] args) {
    	LargestDivisibleSubsets ld = new LargestDivisibleSubsets();
    	int[] nums = {3,4,16,8};
    	System.out.println(ld.largestDivisibleSubset(nums));
	}
}
