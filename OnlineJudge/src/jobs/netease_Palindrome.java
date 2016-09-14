package jobs;

import java.util.Scanner;

public class netease_Palindrome {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = Integer.parseInt(sc.nextLine());
		int[] nums = new int[n];
		String[] strs = sc.nextLine().split(" ");
		for(int i = 0; i < n; i++) {
			nums[i] = Integer.parseInt(strs[i]);
		}
		
		int count = 0;
		int i = 0, j = n-1;
		while(j > i) {
			if(nums[j] == nums[i]) {
				i++; j--;
				continue;
			} else {
				if(nums[i] > nums[j]) {
					nums[j-1] = nums[j] + nums[j-1];
					j--;
				} else {
					nums[i+1] = nums[i] + nums[i+1];
					i++;
				}
				count++;
			}
		}
		
		System.out.println(count);
		
		sc.close();
	}
}
