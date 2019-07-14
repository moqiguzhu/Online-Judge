package dp;

/**
 * 多用程序员的思维思考
 * 不要总想着搞个数学公式，不现实
 * @author Administrator
 * @date 2016-07-30
 */
public class GuessNumberII {
    public int getMoneyAmount(int n) {
    	if(n < 1) {
    		return 0;
    	}
    	int[][] table = new int[n+1][n+1];
    	
    	for(int len = 2; len <= n; len++) {
    		for(int i = 1; i+len-1 <= n; i++) {
    			int j = i+len-1;
    			if(i+1 == j) {
    				table[i][j] = i;
    				continue;
    			}
    			for(int k = i+1; k < j; k++) {
    				if(table[i][j] == 0) {
    					table[i][j] = k+Math.max(table[i][k-1], table[k+1][j]);
    				} else {
    					table[i][j] = 
        						Math.min(table[i][j], k+Math.max(table[i][k-1],table[k+1][j]));
    				}
    			}
    		}
    	}
    	
    	return table[1][n];
    }
    
    public static void main(String[] args) {
    	GuessNumberII gn = new GuessNumberII();
    	int n = 20;
    	System.out.println(gn.getMoneyAmount(n));
	}
}
