package Leetcode;

import java.util.Arrays;

public class DiagonalTraverse {
    public int[] findDiagonalOrder(int[][] matrix) {
    	if(matrix.length == 0 || matrix[0].length == 0) {
    		return new int[0];
    	}
    	
        int m = matrix.length;
        int n = matrix[0].length;
    	int[] res = new int[m*n];
    	
    	int i = 0, j = 0;
    	int flag = 2; // 1左下  2右上
    	
    	for(int index = 0; index < m*n; index++) {
    		if(i >= m || j >= n) {
    			break;
    		}
    		res[index] = matrix[i][j];
    		if(flag == 2) {
    			if(i-1 < 0 || j+1>= n) {
    				if(j+1< n) {
    					j=j+1;
    				} else {
    					i=i+1;
    				}
    				flag = 1;
    			} else {
    				i = i-1;
    				j = j+1;
    			}
    			continue;
    		}
    		if(flag == 1) {
    			if(i+1 >= m || j-1 <0) {
    				if(i+1 < m) {
    					i=i+1;
    				} else {
    					j = j+1;
    				}
    				flag = 2;
    			} else {
    				i = i+1;
    				j = j-1;
    			}
    		}
    	}
    	
    	return res;
    }
    
    public static void main(String[] args) {
		DiagonalTraverse dt = new DiagonalTraverse();
		
		int[][] matrix = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
		};
		
		System.out.println(Arrays.toString(dt.findDiagonalOrder(matrix)));
	}
}
