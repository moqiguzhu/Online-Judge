package Leetcode;

import java.util.Arrays;

public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] tmp = new int[nums2.length];
        int[] res = new int[nums1.length];
        
        for(int i = 0; i < nums2.length; i++) {
        	int j = i+1;
        	for(; j < nums2.length; j++) {
        		if(nums2[j] > nums2[i]) {
        			tmp[i] = nums2[j];
        			break;
        		}
        	}
        	if(j == nums2.length) {
        		tmp[i] = -1;
        	}
        }
        
        for(int i = 0; i < nums1.length; i++) {
        	for(int j = 0; j < nums2.length; j++) {
        		if(nums1[i] == nums2[j]) {
        			res[i] = tmp[j];
        		}
        	}
        }
        return res;
    }
    
    public static void main(String[] args) {
    	NextGreaterElementI nge = new NextGreaterElementI();
    	int[] nums1 = {4,1,2};
    	int[] nums2 = {1,3,4,2};
    	
    	System.out.println(Arrays.toString(nge.nextGreaterElement(nums1, nums2)));
	}
}
