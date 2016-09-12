package jobs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class hiho_fibonacci {
	public static long mod = 1000000007L;
	public static long count = 0L;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		String line = sc.nextLine();
		int n = Integer.parseInt(line);
		line = sc.nextLine();
		String[] strs = line.split(" ");
		int[] nums = new int[n];
		int i = 0;
		for(String s : strs) {
			nums[i++] = Integer.parseInt(s);
		}
		
		{
			fibos.add(1);
			fibos.add(1);
		}
		
		Map<Integer, Long> preTimes = new HashMap<>();
		preTimes.put(-1, 0L);
		
		for(i = 0; i < nums.length; i++) {
			if(!preTimes.containsKey(nums[i])) {
				preTimes.put(nums[i], 0L);
			}
			
			if(nums[i] == 1) {
				preTimes.put(1, preTimes.get(1) + preTimes.get(-1));
				
				preTimes.put(-1, preTimes.get(-1)+1);
			} else {
				int pre = pre(nums[i]);
				if(pre == 0) {
					continue;
				} else {
					if(!preTimes.containsKey(pre)) {
						continue;
					}
					// 各种溢出  是在下输了
					preTimes.put(nums[i], (preTimes.get(nums[i]) + preTimes.get(pre)) % mod);
				}
			}
		}
		
		for(Integer x : preTimes.keySet()) {
			count += preTimes.get(x);
			count %= mod;
		}
	
		System.out.println(count);
		
		sc.close();
	}
	
	static List<Integer> fibos = new ArrayList<>();
	static Map<Integer, Integer> cur_pre = new HashMap<>();
	public static int pre(int cur) {
		if(cur_pre.containsKey(cur)) {
			return cur_pre.get(cur);
		}
		
		
		while(fibos.get(fibos.size()-1) < cur) {
			fibos.add(fibos.get(fibos.size()-1) + fibos.get(fibos.size()-2));
			cur_pre.put(fibos.get(fibos.size()-1), fibos.get(fibos.size()-2));
		}
		
		if(cur_pre.containsKey(cur)) {
			return cur_pre.get(cur);
		} else {
			return 0;
		}
	}
}
