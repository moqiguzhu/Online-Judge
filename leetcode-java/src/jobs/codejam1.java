package jobs;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

// need to debug
public class codejam1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int n,m;
		MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
		for(int i = 0; i < T; i++) {
			n = sc.nextInt();
			m = sc.nextInt();
			BigDecimal t = help(n, m).divide(help1(n+m), mc);
			System.out.println("Case #" + (i+1) + ": " + t.toString());
		}
		sc.close();
	}
	
	public static BigDecimal help(int n, int m) {
		if(m >= n) {
			return BigDecimal.ZERO;
		} else if(m <= 0) {
			return help1(n);
		} else {
			BigDecimal t = new BigDecimal(n+"");
			BigDecimal ttt = new BigDecimal(n*m + "");
			return t.multiply(help(n-1, m)).add(ttt.multiply(help(n-1, m-1)));
		}
	}
	
	public static BigDecimal help1(int x) {
		BigDecimal res = BigDecimal.ONE;
		for(int i = 1; i <= x; i++) {
			res = res.multiply(new BigDecimal(i+""));
		}
		return res;
	}
}
