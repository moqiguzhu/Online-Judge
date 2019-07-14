package jobs;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class codejame1 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("codejam1"));
		int T = Integer.parseInt(sc.nextLine());
		String[] strs;
		for(int i = 0; i < T; i++) {
			String S = sc.nextLine();
			strs = sc.nextLine().split(" ");
			long from = Long.parseLong(strs[0]);
			long to = Long.parseLong(strs[1]);
			
			int len = S.length();
			long t1 = from / len + 1;
			long t2 = to / len;
			
			long t3 = t1 * len;
			long t4 = t2 * len;
			long res = 0;
			
			res += (t2 - t1) * count(S);
			for(long j = from; j < t3+1; j++) {
				int t = (int)((j-1) % len);
				if(S.charAt(t) == 'B') {
					res++;
				}
			}
			for(long j = t4+1; j <= to; j++) {
				int t = (int)((j-1) % len);
				if(S.charAt(t) == 'B') {
					res++;
				}
			}
			fw.write("Case #" + (i+1) + ": " + res + "\n");
		}
		sc.close();
		fw.close();
	}
	
	public static int count(String S) {
		int count = 0;
		for(int i = 0; i < S.length(); i++) {
			if(S.charAt(i) == 'B') {
				count++;
			}
		}
		
		return count;
	}
}
