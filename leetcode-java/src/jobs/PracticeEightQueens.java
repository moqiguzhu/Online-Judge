package jobs;

import java.util.Arrays;

public class PracticeEightQueens {
	public static int n = 8;
	public static int count = 0;
	public static void main(String[] args) {
		char[][] table = new char[n+1][n+1];

		for (int i = 1; i < n+1; i++) {
			Arrays.fill(table[i], '.');
		}
		help(table, 0);
		System.out.println("count is: " + count);
	}

	public static void help(char[][] table, int rowIndex) {
		if(rowIndex == n+1) {
			return;
		}
		for (int i = 1; i < n+1; i++) {
			if (check(rowIndex, i, table)) {
				table[rowIndex][i] = '*';
				if(rowIndex == n) {
					print(table);
					count++;
					return;
				}
				help(table, rowIndex + 1);
				table[rowIndex][i] = '.';
			}
			
		}
	}

	public static boolean check(int rowIndex, int colIndex, char[][] table) {
		for (int i = 1; i < n+1; i++) {
			if (i != colIndex && table[rowIndex][i] == '*') {
				return false;
			}
			if (i != rowIndex && table[i][colIndex] == '*') {
				return false;
			}
			if (rowIndex + i < n+1 && colIndex + i < n+1 && table[rowIndex + i][colIndex + i] == '*') {
				return false;
			}
			if (rowIndex - i > 0 && colIndex - i > 0 && table[rowIndex - i][colIndex - i] == '*') {
				return false;
			}
			if (rowIndex - i > 0 && colIndex + i < n+1 && table[rowIndex - i][colIndex + i] == '*') {
				return false;
			}
			if (rowIndex + i < n+1 && colIndex - i > 0 && table[rowIndex + i][colIndex - i] == '*') {
				return false;
			}
			
		}
		return true;
	}

	public static void print(char[][] table) {
		for (int i = 1; i < n+1; i++) {
			for (int j = 1; j < n+1; j++) {
				System.out.print(table[i][j]);
			}
			System.out.println();
		}
	}
}
