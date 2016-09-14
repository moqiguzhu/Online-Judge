package jobs;

import java.util.Scanner;

public class netease_darknumber {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		if(num == 1) {
			System.out.println(3);
		} 
		if(num == 2) {
			System.out.println(9);
		}
		long[] dp1 = new long[num+1];
		long[] dp2 = new long[num+1];
		dp1[1] = 0; dp2[1] = 3;
		dp1[2] = 3; dp2[2] = 6; 
		
		for(int i = 3; i <= num; i++) {
			dp1[i] = dp1[i-1] + dp2[i-1];
			dp2[i] = 2 * dp1[i-1] + dp2[i-1];
		}
		
		System.out.println(dp1[num] + dp2[num]);
		sc.close();
	}
}
