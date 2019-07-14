package jobs;

import java.util.Scanner;

public class indeed44 {
	static int n;
	static int m;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		// 1 keyi 0 bukeyi 2 tianshengbukeyi
		int[][] table = new int[n+1][n+1];
		for(int i = 0; i < m; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			table[x][y] = 2;
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(table[i][j] == 2) {
					continue;
				} 
				if(check(table, i, j)) {
					continue;
				} else {
					if(table[i][j] == 0) {
						table[i][j] = 1;
						if(check(table, i, j)) {
							continue;
						} else {
							boolean flag = false;
							for(int x = i; x >= 1; x--) {
								if(flag) {
									break;
								}
								for(int y = j-1; y >= 1; y--) {
									if(table[x][y] == 2) {
										continue;
									} else if(table[x][y] == 1) {
										table[x][y] = 0;
									} else {
										boolean flag1 = true;
										table[x][y] = 1;
										for(int k = 1; k <= n; k++) {
											if(!check(table, x, k) || !check(table, k, y)) {
												flag1 = false;
												break;
											}
										}
										if(flag1) {
											if(y == 1) {
												i = x-1;
												j = n;
											} else {
												i = x;
												j = y-1;
											}
											flag = true;
											break;
										} else {
											table[x][y] = 0;
										}
										
									}
								}
							}
							
						}
					}
				}
			}
		}
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(table[i][j] == 1) {
					System.out.println(j);
				}
			}
		}
 		sc.close();
	}
	
	public static boolean check(int[][] table, int x, int y) {
		int count1 = 0, count2 = 0;
		for(int i = 1; i <= n; i++) {
			if(table[x][i] == 1) {
				count1++;
			}
			if(table[i][y] == 1) {
				count2++;
			}
		}
		if(x == n && count2 != 1) {
			return false;
		}
		if(y == n && count1 != 1) {
			return false;
		}
		return count1 <= 1 && count2 <= 1;
	}
}
