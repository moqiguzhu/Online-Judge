package jobs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class codejame3 {
	public static long res = 0;
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("codejam3"));
		
		int T = Integer.parseInt(sc.nextLine());
		
		for(int i = 0; i < T; i++) {
			int n = sc.nextInt();
			int d = sc.nextInt();
			res = 0;
			for(int j = 1; j*d <= n; j++) {
				if(n - j*d == 0) {
					res++;
				} else {
					help(j*d,j*d, n-j*d);	
				}
				
			}
			fw.write("Case #" + (i+1) + ": " + res + "\n");
		}
		
		sc.close();
		fw.close();
	}
	
	//cache can be added
	public static void help(int small, int prev, int remain) {
		for(int i = prev; i <= small+2; i++) {
			if(remain == i) {
				res++;
			} else if(remain > i) {
				help(small, i, remain-i);
			}
		}
	}
}
