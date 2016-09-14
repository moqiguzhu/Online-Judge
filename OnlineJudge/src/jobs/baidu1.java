package jobs;

import java.util.Scanner;

public class baidu1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = Integer.parseInt(sc.nextLine());
		
		for(int i = 0; i < T; i++) {
			int res = 0;
			
			int n = Integer.parseInt(sc.nextLine());
			
			for(int j = 0; j < n; j++) {
				res ^= sc.nextInt();
			}
			
			for(int j = 0; j < n-1; j++) {
				res ^= sc.nextInt();
			}
			System.out.println(res);
		}
		sc.close();
	}
}
