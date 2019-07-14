package jobs;

import java.util.Arrays;
import java.util.Scanner;

public class toutiao1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n];
		for(int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}
		
		Arrays.sort(nums);
		
		int[] table = new int[n];
		// 初始化
		table[0] = 2;
		if(nums[1] - nums[0] <= 20) {
			table[1] = 1;
		} else {
			table[1] = 4;
		}
		
		int t1 = 0;
		for(int i = 2; i < n; i++) {
			if(nums[i] - nums[i-1] <= 10) {
				if(nums[i-1] - nums[i-2] <= 10) {
					if(i >= 3) {
						t1 = table[i-3];
					} else {
						t1 = 0;
					}
				} else {
					t1 = 1 + table[i-2];
				}
			} else {
				if(nums[i] - nums[i-1] <= 20) {
					t1 = 1 + table[i-2];
				} else {
					t1 = 2 + table[i-1];
				}
			}
			table[i] = t1;
		}
		System.out.println(table[n-1]);
		sc.close();
	}
}
