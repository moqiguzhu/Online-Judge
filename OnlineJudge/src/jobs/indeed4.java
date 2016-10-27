package jobs;

import java.util.Scanner;

// 有错误
public class indeed4 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String line = sc.nextLine();
		int x = 0,y = 0;
		int ans = 1;
		for(int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if(i < 10) {
				if(c == '?') {
					x++;
				} else {
					y++;
				}
				if(i == 9) {
					for(int j = 1; j <= x; j++) {
						ans *= j;
					}
				} 
			} else {
				if(c == '?') {
					continue;
				} else {
					if(c == line.charAt(i-10)) {
						continue;
					} else {
						ans = 0;
						break;
					}
				}
			}
		}
		
		if(ans == 1) {
			while(x > 0) {
				ans *= (10-y);
				y++;
				x--;
			}
			
		}
		System.out.println(ans);
		
		sc.close();
	}
}
