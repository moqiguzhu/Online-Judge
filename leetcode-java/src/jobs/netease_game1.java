package jobs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Task {
	public int id;
	public int c;
	public int d;
	
	public Task(int id, int c, int d) {
		this.id = id;
		this.c = c;
		this.d = d;
	}
}


public class netease_game1 {
	public static Map<Integer, Integer> map = new HashMap<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		List<Task> tasks = new ArrayList<>();
		int c, d;
		int id = 1;
		for(int i = 0; i < n; i++) {
			c = sc.nextInt();
			d = sc.nextInt();
			tasks.add(new Task(id, c, d));
			id++;
		}
		int m = sc.nextInt();
		
		int f,s;
		for(int i = 0; i < m; i++) {
			f = sc.nextInt();
			s = sc.nextInt();
			map.put(f, s);
		}
		
		Collections.sort(tasks, new Comparator<Task>() {

			@Override
			public int compare(Task o1, Task o2) {
				if(map.containsKey(o1.id) && map.get(o1.id) == o2.id) {
					return -1;
				} 
				if(map.containsKey(o2.id) && map.get(o2.id) == o1.id) {
					return 1;
				}
				// 应该还需要考虑一种特殊情况 唾手可得
				return o1.c-o1.d < o2.c-o2.d ? 1 : -1;
			}
		});
		
		long time = 0;
		long res = 0;
		
		for(Task t : tasks) {
			time += t.c;
			res = Math.max(res, time - t.d);
		}
		
		System.out.println(res);
		sc.close(); 
	}
}
