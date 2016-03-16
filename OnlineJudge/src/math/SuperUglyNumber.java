
/**
 * answer comes from https://leetcode.com/discuss/81411/java-three-methods-23ms-58ms-with-heap-performance-explained
 * 过早的优化是万恶之源，先找到一个naive solution
 * 
 * @author moqiguzhu
 * @date 2016-03-16
 * @version 1.0
 *
 */

public class SuperUglyNumber {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int[] ugly = new int[n];
        // idx记录已经生成的ugly number中当前需要考虑的连续primes.length个
        int[] idx = new int[primes.length];

        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            //find next
            ugly[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++)
                ugly[i] = Math.min(ugly[i], primes[j] * ugly[idx[j]]);

            //slip duplicate
            for (int j = 0; j < primes.length; j++) {
                while (primes[j] * ugly[idx[j]] <= ugly[i]) idx[j]++;
            }
        }

        return ugly[n - 1];
    }
}
