package jobs;

import java.util.Scanner;

public class Three602 {
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		String line;
//		while(sc.hasNext()) {
//			line = sc.nextLine();
//			int n = Integer.parseInt(line);
//			if(n == 0) {
//				System.out.println(0);
//			} else if(n <= 9) {
//				System.out.println(1);
//			} else {
//				System.out.println(help(line));	
//			}
//			
//		}
//		sc.close();
		
		print();
	}
	
	public static long help(String line) {
		long res = 0;
	
		if(line.charAt(0) == '1') {
			for(int i = 0; i < line.length()-1; i++) {
				res += Math.pow(2, i);
			}
			long t = 1;
			for(int i = 1; i < line.length(); i++) {
				if(line.charAt(i) != '0') {
					t *= 2;
				}
			}
			return res + t;
		} else {
			for(int i = 0; i <= line.length()-1; i++) {
				res += Math.pow(2, i);
			}
			return res;
		}
	}
	
	public static void print() {
		System.out.println(help(10101001 + ""));
	}
}
