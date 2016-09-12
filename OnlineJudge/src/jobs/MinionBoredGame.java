package jobs;

/**
 * һ��������ߵ����⣬��n�����ӣ���ʼλ�ڵ�һ�����ӣ������Ҫ�������һ�����ӡ�
 * ÿ�ο������󡢲�����������һ���������ǰ��������ߵ�λ�ã����ܼ�����������Ѿ��������ұߵ�λ�ã�Ҳ���ܼ�������ʣ�µĻ���ÿ��ֻ��ѡ����ԭ�ء�
 * ������t��ѡ��Ļ��ᣬt�λ���պ������ʱ��������Ҫλ�����һ�������ϡ����ܹ��ж�����ѡ�񷽰���
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
    	// ��t==0ʱ����
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
