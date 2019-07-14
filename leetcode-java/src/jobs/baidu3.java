package jobs;

import java.util.Scanner;

// 找到s到t中所有的最短路径中权值最大的那条边
public class baidu3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int n, m, s, t;
		
		for(int i = 0; i < T; i++) {
			n = sc.nextInt(); m = sc.nextInt(); s = sc.nextInt(); t = sc.nextInt();
			long[][] edges = new long[n+1][n+1];
			int u, w, v;
			for(int j = 0; j < m; j++) {
				u = sc.nextInt(); v = sc.nextInt(); w = sc.nextInt();
				edges[u][v] = w;
				edges[v][u] = w;
			}
			
			System.out.println(help(edges, s, t));
		}
		
		sc.close();
	}
	static int MAX = Integer.MAX_VALUE;
	static int MIN = Integer.MIN_VALUE;
	public static long help(long[][] edges, int s, int t) {
		// bellman-ford
		int n = edges.length;
		long[][] dp = new long[2][n];
		long[][] dp1 = new long[2][n];
		
		for(int i = 0; i < 2; i++) {
			for(int j = 1; j < n; j++) {
				if(i == 0 && j == s) {
					dp[i % 2][j] = 0;
					dp1[i % 2][j] = MIN;
				} else {
					dp[i % 2][j] = MAX;
					dp1[i % 2][j] = MIN;
				}
			}
			
		}
		
		for(int i = 1; i < n; i++) {
			for(int j = 1; j < n; j++) {
				for(int k = 1; k < n; k++) {
					// 这边其实可以稍微换个写法
					if(edges[k][j] > 0) {
						if(dp[(i-1)%2][k] + edges[k][j] < dp[i%2][j]) {
							dp[i%2][j] = dp[(i-1)%2][k] + edges[k][j];
							dp1[i%2][j] = Math.max(dp1[(i-1)%2][k], edges[k][j]);
						} else if(dp[(i-1)%2][k] + edges[k][j] == dp[i%2][j]) {
							dp1[i%2][j] = Math.max(edges[k][j], dp1[i%2][j]);
						}
					}
				}
				
			}
		}
		
		return dp1[s][t];
	}
}
