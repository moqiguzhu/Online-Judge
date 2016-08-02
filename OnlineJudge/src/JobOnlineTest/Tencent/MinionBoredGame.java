package xxx;

/**
 * 一个随机游走的问题，有n个格子，初始位于第一个格子，最后需要到达最后一个格子。
 * 每次可以向左、不动、向右走一步，如果当前处于最左边的位置，不能继续向左，如果已经处于最右边的位置，也不能继续向左，剩下的机会每次只能选择在原地。
 * 我们有t次选择的机会，t次机会刚好用完的时候，我们需要位于最后一个格子上。问总共有多少种选择方案。
 * @author Administrator
 *
 */
public class MinionBoredGame {
	private static int mod = 123454321; 
    public static int answer(int t, int n) { 
    	if(t+1 < n) { 
    		return 0; 
    	}

    	int[][] table = new int[t+1][n];
    	table[0][n-1] = 1;
    	int tmp1, tmp2, tmp3;
    	
    	// x = 0...n-1
    	// y = (n-1)-x
    	for(int i = 1; i <= t; i++) {
    		for(int x = 0; x < n; x++) {
    			if(x == n-1) { 
    				table[i][x] = 1; 
    				continue; 
    			}
    			
    			tmp1 = table[i-1][x];
    			tmp2 = x > 0 ? table[i-1][x-1] : 0;
    			tmp3 = x+1 < n ? table[i-1][x+1] : 0;
    			
    			table[i][x] = (tmp1 + tmp2 + tmp3) % mod;    		
    		}
    	}
//    	print(table);
    	return table[t][0];
    }
    
    public static int help(int t, int x, int n, int origin) {
    	int y = (n-1) - x;
    	// 当t==0时工作
    	if(x == n-1) {
    		return 1;
    	}
    	if(x < 0 || y < 0) {
    		return 0;
    	}
    	return origin;
    }
    
    public static void print(int[][] table) {
    	for(int i = 0; i < table.length; i++) {
    		for(int j = 0; j < table[0].length; j++) {
    			System.out.print(table[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
    
    public static void main(String[] args) {
		System.out.println(answer(100, 45));
	}
}
