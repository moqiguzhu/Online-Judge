package jobs;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// 30%
public class netease_game2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		int n = Integer.parseInt(sc.nextLine());
		int len = (int)Math.ceil(Math.log(n) / Math.log(2));
		
		Map<String, String> map = new HashMap<>();
		String[] strs;
		for(int i = 0; i < n-2; i++) {
			strs = sc.nextLine().split(" ");
			map.put(strs[1], strs[0]);
		}
		String s1, s2, t1 = "", t2 = "";
		strs = sc.nextLine().split(" ");
		s1 = strs[0];
		strs = sc.nextLine().split(" ");
		s2 = strs[0];
		
		String t;
		boolean flag = false;
		StringBuilder line1 = new StringBuilder();
		StringBuilder line2 = new StringBuilder();
		
		for(int i = 0; i < line.length(); i++) {
			for(int j = 1; j <= len; j++) {
				if(i+j > line.length()) {
					break;
				}
				t = line.substring(i, i+j);
				if(map.containsKey(t)) {
					if(t.equals(t1)) {
						line1.append(s1);
						line2.append(s2);
					} else if(t.equals(t2)) { 
						line1.append(s2);
						line2.append(s1);
					} else {
						line1.append(map.get(t));
						line2.append(map.get(t));	
					}
					
					i = i + j - 1;
					break;
				} else if(j == len) {
					if(!flag) {
						t1 = t;
						map.put(t, s1);	
						flag = true;
						line1.append(s1);
						line2.append(s2);
					} else {
						t2 = t;
						map.put(t, s2);
						line1.append(s2);
						line2.append(s1);
					}
					i = i + j - 1;
					break;
				}
			}
		}
		if(line1.equals(line2)) {
			System.out.println(line1);
		} else {
			System.out.println(line1);
			System.out.println(line2);
		}
		
		sc.close();
	}
}
