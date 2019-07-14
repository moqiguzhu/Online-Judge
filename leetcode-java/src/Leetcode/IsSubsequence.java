package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IsSubsequence {
	static Map<Character, List<Integer>> letter_indexes = new HashMap<>();
    public boolean isSubsequence(String s, String t) {
    	for(char c = 'a'; c <= 'z'; c++) {
    		letter_indexes.put(c, new ArrayList<Integer>());
    	}
    	for (int i = 0; i < t.length(); i++) {
    		char c = t.charAt(i);
    		letter_indexes.get(c).add(i);
		}
    	
    	int index = -1;
    	for(int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		index = bs(letter_indexes.get(c), index, 0, letter_indexes.get(c).size());
    		if(index == -1) {
    			return false;
    		}
    	}
        return true;
    }
    
    public int bs(List<Integer> arr, int index, int begin, int end) {
    	if(begin >= end) {
    		return -1;
    	}
    	
    	if(end == begin + 1) {
    		if(arr.get(begin) > index) {
    			return arr.get(begin);
    		} else {
    			return -1;
    		}
    	}
    	
    	int mid = (begin + end) / 2;
    	if(arr.get(mid) > index) {
    		if(arr.get(mid-1) > index) {
    			return bs(arr, index, begin, mid);	
    		} else {
    			return arr.get(mid);
    		}
    	} else {
    		return bs(arr, index, mid+1, end);
    	}
    }
	public static void main(String[] args) {
		String s = "acb";
		String t = "ahbgdc";
		IsSubsequence is = new IsSubsequence();
		
		System.out.println(is.isSubsequence(s, t));
	}
}
