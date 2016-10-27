package jobs;

import java.util.Arrays;
import java.util.Scanner;

public class toutiao2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] nums = new int[n];
		for(int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}
		
		Arrays.sort(nums);
		
		long res = 0;
		
		for(int i = 1; i < n; i++) {
			if(nums[i] - nums[i-1] <= 10) {
				continue;
			} else if(nums[i] - nums[i-1] <= 20) {
                if((res+i) % 3 == 0) {
                	i--;
                } else {
                	res++;
                }
			} else {
				res += 3-((res+i)%3);
                i--;
			}
		}
		if((res + n) % 3 == 0) { 
			System.out.println(res);
		} else {
			System.out.println(res + 3 - (res+n)%3);
		}
		
		sc.close();
	}
}