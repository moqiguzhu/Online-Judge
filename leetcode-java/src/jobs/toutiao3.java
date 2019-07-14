package jobs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class toutiao3 {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while(reader.hasNext()) {
			int n = reader.nextInt();
			int m = reader.nextInt();
			Set<Integer> set = new HashSet<>();
			for(int i = 0; i < n; i ++) {
				set.add(reader.nextInt());
			}
			int count = 0;
			Iterator<Integer> iterator = set.iterator();
			while(iterator.hasNext()) {
				int a = iterator.next();
				int cap = getCap(m);
				for(int i = m; i <= cap; i ++) {
					if(set.contains(i ^ a)) {
						count ++;
					}
				}
			}
			System.out.println(count / 2);
		}
		reader.close();
	}
	
	public static int getCap(int n) {
		int i = 1;
		while(i < n) {
			i *= 2;
		}
		return i - 1;
	}
	
	public static void main1(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int[] nums = new int[n];
		for(int i = 0; i < n; i++) {
			nums[i] = sc.nextInt();
		}
		
		long count = 0;
		for(int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++) {
				if((nums[i] ^ nums[j]) > m) {
					count++;
				}
			}
		}
		System.out.println(count);
		sc.close();
	}
}
