package jobs;

import java.util.Scanner;

public class Three601 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[][] nums = new char[3][3];
		while(sc.hasNext()) {
			nums[0] = sc.nextLine().toCharArray();
			nums[1] = sc.nextLine().toCharArray();
			nums[2] = sc.nextLine().toCharArray();
			
			boolean flag = true;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(nums[i][j] == 'X') {
						if(nums[2-i][2-j] != 'X') {
							flag = false;
						}
					}
				}
			}
			if(!flag) {
				System.out.println("No");
			} else {
				System.out.println("Yes");
			}
		}
		
		sc.close();
	}
}
