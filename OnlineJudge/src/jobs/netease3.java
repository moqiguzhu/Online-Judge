package jobs;

import java.util.Arrays;
import java.util.Scanner;

public class netease3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] strs = sc.nextLine().split(" ");
		int n = Integer.parseInt(strs[0]);
		int m = Integer.parseInt(strs[1]);
		int[] table = new int[m+1];
		
		Arrays.fill(table, -1);
		table[n] = 0;
		
		for(int i = n; i <= m; i++) {
			// sqrt(i)能过70% i/2超时
			if(i > n && table[i] == -1) 
				continue;
			for(int j = 2; j <= Math.sqrt(i); j++) {
				if(i % j == 0) {
					if(i+j <= m && table[i+j] != -1)
						table[i+j] = Math.min(table[i]+1, table[i+j]);
					else if(i+j <= m)
						table[i+j] = table[i]+1;
					if(i+i/j <= m && table[i+i/j] != -1)
						table[i+i/j] = Math.min(table[i]+1, table[i+i/j]);
					else if(i+i/j <= m)
						table[i+i/j] = table[i]+1;
				}
			}
		}
		
		System.out.println(table[m]);
		
		sc.close();
	}
}
