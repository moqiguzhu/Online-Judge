package jobs;

import java.util.Scanner;

public class indeed33 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int[] count = new int[10];
		int[] count1 = new int[10];
		
		count = help1(n);
		int ans = 0;
		for(int i = 2; i < 10; i++) {
			count1 = help1(n * i);
			if(help(count, count1)) {
				ans++;
			}
		}
		
		System.out.println(ans);
		
		sc.close();
		
	}
	
	public static boolean help(int[] count, int[] count1) {
		for(int i = 0; i < count.length; i++) {
			if(count[i] != count1[i]) {
				return false;
			}
		}
		
		return true;
	}
	
	public static int[] help1(int x) {
		int[] count1 = new int[10];
		int t;
		while(x != 0) {
			t = x % 10;
			x = x / 10;
			count1[t]++;
		}
		
		return count1;
	}
}
