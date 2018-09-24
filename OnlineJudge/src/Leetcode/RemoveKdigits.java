package Leetcode;

public class RemoveKdigits {
    public String removeKdigits(String num, int k) {
    	// 边界
    	if(num.length() <= k) {
    		return "0";
    	}
    	
    	while(k > 0) {
    		int index = 0;
    		while(index + 1 < num.length() && num.charAt(index) <= num.charAt(index+1)) {
    			index++;
    		}
    		if(index + 1 >= num.length()) {
    			num = num.substring(0, index);
    		} else {
    			num = num.substring(0, index) + num.substring(index+1);
    		}
    		k--;
    	}
    	
    	int index = 0;
    	while(index < num.length() - 1 && num.charAt(index) == '0') {
    		index++;
    	}
    	
    	if(index >= num.length()) {
    		num = "";
    	} else {
    		num = num.substring(index);
    	}
    	
    	return num == "" ? "0" : num;
    }
    
    public static void main(String[] args) {
    	RemoveKdigits rk = new RemoveKdigits();
    	
    	String num = "10";
    	int k = 1;
    	
    	System.out.println(rk.removeKdigits(num, k));
	}
}
