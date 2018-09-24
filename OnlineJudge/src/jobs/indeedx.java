package jobs;

import java.util.Scanner;

/**
 * 
 * Description: 
 */
public class indeedx {
	static boolean good = false;
	public static void solve() {
		Scanner reader = new Scanner(System.in);
		while(reader.hasNext()) {
			int N = reader.nextInt();
			int M = reader.nextInt();
			int[][] array = new int[N][N];
			while(M > 0) {
				M --;
				int i = reader.nextInt() - 1;
				int j = reader.nextInt() - 1;
				array[i][j] = -1;
			}
			
			int[] row = new int[N];
			for(int i = 0; i < row.length; i ++) {
				row[i] = - 1;
			}
			int[] col = new int[N];
			for(int i = 0; i < col.length; i ++) {
				col[i] = - 1;
			}
			backtrack(row, col, 0, array);
		}
		reader.close();
	}
	
	public static void backtrack(int[] row, int[] col, int index, int[][] matrix) {
		if(good) {
			return;
		}
		if(index == row.length) {
			for(int i = 0; i < row.length; i ++) {
				System.out.println(row[i] + 1);
			}
			good = true;
			return;
		}
		
		for(int i = 0; i < col.length; i ++) {
			if(good) {
				return;
			}
			if(col[i] == -1 && matrix[index][i] != -1) {
				row[index] = i;
				col[i] = 1;
				backtrack(row, col, index + 1, matrix);
				row[index] = -1;
				col[i] = -1;
			}
		}
	}
	
	public static void main(String[] args) {
		indeedx.solve();
	}
}
