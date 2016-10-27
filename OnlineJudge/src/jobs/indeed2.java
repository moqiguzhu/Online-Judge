package jobs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class indeed2 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		
		Map<Integer, List<String>> map1 = new HashMap<>();
		Map<String, Integer> map2 = new HashMap<>();
		
		String s;
		for(int i = 0; i < str.length()-1; i++) {
			s = str.substring(i, i+2);
			if(map2.containsKey(s)) {
				map2.put(s, map2.get(s)+1);
			} else {
				map2.put(s, 1);
			}
		}
		
		for(String t : map2.keySet()) {
			int x = map2.get(t);
			if(map1.containsKey(x)) {
				map1.get(x).add(t);
			} else {
				List<String> list = new ArrayList<>();
				list.add(t);
				map1.put(x, list);
			}
		}
		
		List<Integer> keys = new ArrayList<>();
		keys.addAll(map1.keySet());
		Collections.sort(keys, Collections.reverseOrder());
		
		for(int x : keys) {
			Collections.sort(map1.get(x));
			for(String t : map1.get(x)) {
				System.out.println(t);
			}
		}
		
		sc.close();
	}
}
