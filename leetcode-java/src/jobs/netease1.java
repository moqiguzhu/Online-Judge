package jobs;

import java.math.BigInteger;
import java.util.Scanner;

public class netease1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long num = Long.parseLong(sc.nextLine());
		
		System.out.println(help(num));
		
		sc.close();
	}
	
	public static BigInteger help(long num) {
		if(num == 1) { 
			return BigInteger.ONE;
		}
		if(num == 2) {
			return new BigInteger("2");
		}
		if(num % 2 == 1) {
			//如果num是int 越界
			long t = (1+num) * (1+num) / 4;
			BigInteger tt = new BigInteger(Long.toString(t));
			return tt.add(help((num-1) / 2));
		} else {
			long t = (num) * (num) / 4;
			BigInteger tt = new BigInteger(Long.toString(t));
			return tt.add(help(num / 2));
		}
	}
}
