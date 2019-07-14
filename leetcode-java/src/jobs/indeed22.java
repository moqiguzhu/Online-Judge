package jobs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class indeed22 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		int k = Integer.parseInt(sc.nextLine());
		
//		List<String> list = new ArrayList<>();
//		
//		for(int i = k; i <= s.length(); i++) {
//			list.add(help(s, i));
//		}
//		
//		Collections.sort(list);
//	
//		System.out.println(list.get(0));
		
		System.out.println(help(s, k));
		
		sc.close();
	}
	
	public static String help(String s, int k) {
		int[] count = new int[10];
		char c;
		for(int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			count[c - 'a']++;
		}
		int[] count1 = new int[10];
		for(int i = 0; i < count.length; i++) {
			if(k > 0) {
				count1[i] = count[i];
			}
			k = k - count1[i];
		}
		int j;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			c = s.charAt(i);
			j = c - 'a';
			if(count1[j] > 0) {
				sb.append(c);
				count1[j]--;
			}
		}
		
		return sb.toString();
	}
}
