package jobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

class Edge {
	int v;
	int w;
	int c;

	public Edge(int v, int w, int c) {
		this.v = v;
		this.w = w;
		this.c = c;
	}
}

public class ford_fulkson {
	public static long maxFlow = 0l;
	public static Map<Integer, HashMap<Integer, Integer>> info = new HashMap<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(), m = sc.nextInt();

		int v, w, c;
		for (int i = 0; i < m; i++) {
			v = sc.nextInt();
			w = sc.nextInt();
			c = sc.nextInt();
			if (!info.containsKey(v)) {
				info.put(v, new HashMap<Integer, Integer>());
			}
			if (!info.get(v).containsKey(w)) {
				info.get(v).put(w, c);
			} else {
				info.get(v).put(w, info.get(v).get(w) + c);
			}

			// reverse
			if (!info.containsKey(w)) {
				info.put(w, new HashMap<Integer, Integer>());
			}
			if (!info.get(w).containsKey(v)) {
				info.get(w).put(v, 0);
			}
		}
		int min;
		while ((min = augPath(1, n, n)) != Integer.MAX_VALUE) {
			maxFlow += min;
		}

		System.out.println(maxFlow);

		sc.close();
	}

	public static int augPath(int s, int t, int n) {
		// DFS
		boolean[] table = new boolean[n + 1];

		Stack<Integer> stack = new Stack<>();
		stack.add(s);
		table[s] = true;

		while (!stack.isEmpty()) {
			int cur = stack.peek();
			for (int x : info.get(cur).keySet()) {
				if (!table[x] && info.get(cur).get(x) > 0) {
					table[x] = true;
					stack.push(x);
					break;
				}
			}

			if (cur == stack.peek()) {
				stack.pop();
			}
			if (!stack.isEmpty() && stack.peek() == t) {
				break;
			}
		}

		// path in stack
		int min = Integer.MAX_VALUE;
		List<Integer> tmp = new ArrayList<>(stack);
		for (int i = 0; i < tmp.size() - 1; i++) {
			int x1 = tmp.get(i);
			int x2 = tmp.get(i + 1);
			min = Math.min(min, info.get(x1).get(x2));
		}

		for (int i = 0; i < tmp.size() - 1; i++) {
			int x1 = tmp.get(i);
			int x2 = tmp.get(i + 1);
			info.get(x1).put(x2, info.get(x1).get(x2) - min);
			info.get(x2).put(x1, info.get(x2).get(x1) + min);
		}
 
		return min;
	}
}
