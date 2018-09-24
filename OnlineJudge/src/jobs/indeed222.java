package jobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class indeed222 {

	public static PriorityQueue<String> pq = new PriorityQueue<>();
	public static String S;
	public static int K;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			S = sc.nextLine();
			K = Integer.parseInt(sc.nextLine());

			char[] chars = S.toCharArray();

			Set<Character> set = new HashSet<>();
			for (char c : chars) {
				set.add(c);
			}

			List<Character> remains = new ArrayList<>(set);
			
			Set<Character> cur = new HashSet<>();
			
			help(cur, remains, 0);
			System.out.println(pq.poll());
		}

		sc.close();
	}

	public static void dfs(List<String> list, List<String> remain) {
		if (remain.size() == 0)
			return;
		for (int i = 0; i < remain.size(); i++) {
			String c = remain.get(i);

			List<String> newCur = new ArrayList<>(list);
			newCur.add(c);

			List<String> newRemain = new ArrayList<>(remain.subList(i + 1, remain.size()));

			StringBuilder sb = new StringBuilder();

			for (char cs : S.toCharArray()) {
				if (newCur.contains(cs + "")) {
					sb.append(cs);
				}
			}

			if (sb.length() >= K) {
				pq.add(sb.toString());
			}
			dfs(newCur, newRemain);
		}
	}
	
	public static void help(Set<Character> cur, List<Character> remain, int index) {
		if(index == remain.size()) {
			return;
		} else {
			Set<Character> cur1 = new HashSet<>(cur);
			
			Set<Character> cur2 = new HashSet<>(cur);
			cur1.add(remain.get(index));
			
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();

			for (char cs : S.toCharArray()) {
				if (cur1.contains(cs)) {
					sb1.append(cs);
				}
				if(cur2.contains(cs)) {
					sb2.append(cs);
				}
			}
			if(sb1.toString().length() >= K) {
				pq.add(sb1.toString());
			}
			if(sb2.toString().length() >= K) {
				pq.add(sb2.toString());
			}
			
			help(cur1, remain, index+1);
			help(cur2, remain, index+1);
		}
	}
}