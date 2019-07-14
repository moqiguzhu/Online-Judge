package jobs;

import java.util.*;

public class baidu2 {
	static int[][] cache;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for(int i = 0; i < T; i++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			
			int[][] table = new int[n][m];
			
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < m; k++) {
					table[j][k] = sc.nextInt();
				}
			}
			
			cache = new int[n][m];
			
			for(int j = 0; j < n; j++) {
				for(int k = 0; k < m; k++) {
					if(j == 0) {
						cache[j][k] = table[j][k]; 
					} else {
						cache[j][k] = cache[j-1][k] + table[j][k];
					}
				}
			}
			System.out.println(maxSubMatrix(table));
		}
		sc.close();
	}
	
	public static int maxSubArray(int[] A) {
		int max = A[0];
		int maxEndingHere = A[0];
		for(int i = 1; i < A.length; i++) {
			if(maxEndingHere < 0) maxEndingHere = A[i];
			else maxEndingHere = maxEndingHere + A[i];
			if(max < maxEndingHere) max = maxEndingHere;
		}
		return max;
	}
	
	public static long maxSubMatrix(int[][] table) {
		long maxSum = Long.MIN_VALUE;
		for(int i = 0; i < table.length; i++) {
			for(int j = i; j < table.length; j++) {
				int[] t = new int[table[0].length];
				for(int p = 0; p < t.length; p++) {
					if(i == 0) {
						t[p] = cache[j][p];
					} else {
						t[p] = cache[j][p] - cache[i-1][p];
					}
				}
				maxSum = Math.max(maxSum, maxSubArray(t));
			}
		}
		
		return maxSum;
	}
}
