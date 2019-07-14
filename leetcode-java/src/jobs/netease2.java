package jobs;

import java.util.Scanner;

public class netease2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = Integer.parseInt(sc.nextLine());
		
		long count = 0;
		for(int i = 0; i < Math.sqrt(num); i++) {
			double x = Math.sqrt(num - i * i);
			if(x == (int)x) {
				count++;
			}
		}
		
		System.out.println(count * 4);
		
		sc.close();
	}
}