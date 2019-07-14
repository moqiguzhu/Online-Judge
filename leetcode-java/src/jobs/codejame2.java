package jobs;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class Pair {
	long n;
	long d;
	
	public Pair(long n, long d) {
		this.n = n;
		this.d = d;
	}
	
	@Override 
	public int hashCode() {
		return (int)(n * d);
	}
	
	@Override
	public boolean equals(Object o) {
		Pair p = (Pair) o;
		return p.n == this.n && p.d == this.d;
	}
}
public class codejame2 {
	public static Map<Pair, Boolean> cache = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("codejam2"));
		
		int T = Integer.parseInt(sc.nextLine());
		
		for(int i = 0; i < T; i++) {
			long n = sc.nextLong();
			boolean flag = false;
			for(long j = 2; j * j < n-1; j++) {
				if((n-1) % j == 0) {
					if(help((n-1)/j, j)) {
						fw.write("Case #" + (i+1) + ": " + j + "\n");
						flag = true;
						Pair p = new Pair((n-1)/j, j);
						cache.put(p, true);
						break;
					} else {
						Pair p = new Pair((n-1)/j, j);
						cache.put(p, false);
					}
				}
			}
			if(!flag) {
				fw.write("Case #" + (i+1) + ": " + (n-1) + "\n");
				Pair p = new Pair(n, n-1);
				cache.put(p, true);
			}
		}
		sc.close();
		fw.close();
	}
	
	public static boolean help(long x, long y) {
		Pair p = new Pair(x, y);
		if(cache.containsKey(p)) {
			return cache.get(p);
		}
		while(x != 0l) {
			if(x % y != 1l) {
				return false;
			}
			x = x / y;
		}
		return true;
	}
}
