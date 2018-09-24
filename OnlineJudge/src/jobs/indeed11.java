package jobs;

import java.util.Scanner;

public class indeed11 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		
		int x;
		for(int i = 3; i <= 10; i++) {
			x = A + B;
			A = B;
			B = x;
		}
		System.out.println(B);
		sc.close();
	}
}
