package jobs;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Line up the captives
====================
As you ponder sneaky strategies for assisting with the great rabbit escape, you realize that you have an opportunity to fool Professor Booleans guards into thinking there are fewer rabbits total than there actually are.
By cleverly lining up the rabbits of different heights, you can obscure the sudden departure of some of the captives.
Beta Rabbits statisticians have asked you for some numerical analysis of how this could be done so that they can explore the best options.
Luckily, every rabbit has a slightly different height, and the guards are lazy and few in number. Only one guard is stationed at each end of the rabbit line-up as they survey their captive population. With a bit of misinformation added to the facility roster, you can make the guards think there are different numbers of rabbits in holding.
To help plan this caper you need to calculate how many ways the rabbits can be lined up such that a viewer on one end sees x rabbits, and a viewer on the other end sees y rabbits, because some taller rabbits block the view of the shorter ones.
For example, if the rabbits were arranged in line with heights 30 cm, 10 cm, 50 cm, 40 cm, and then 20 cm, a guard looking from the left side would see 2 rabbits (30 and 50 cm) while a guard looking from the right side would see 3 rabbits (20, 40 and 50 cm).
Write a method answer(x,y,n) which returns the number of possible ways to arrange n rabbits of unique heights along an east to west line, so that only x are visible from the west, and only y are visible from the east. The return value must be a string representing the number in base 10.
If there is no possible arrangement, return "0".
The number of rabbits (n) will be as small as 3 or as large as 40
The viewable rabbits from either side (x and y) will be as small as 1 and as large as the total number of rabbits (n).
Languages
=========
To provide a Python solution, edit solution.py
To provide a Java solution, edit solution.java
Test cases
==========
Inputs:
    (int) x = 2
    (int) y = 2
    (int) n = 3
Output:
    (string) "2"
Inputs:
    (int) x = 1
    (int) y = 2
    (int) n = 6
Output:
    (string) "24"
 * @author Debosmit
 *
 */

public class LineUpCaptives {
	public static Map<Integer, Map<Integer, BigInteger>> total_vis_num = new HashMap<>();
	public static Map<Integer, BigInteger> facs = new HashMap<>();
	
    public static String answer(int x, int y, int n) { 
    	
        BigInteger res = BigInteger.ZERO;
        // start from 1
        int begin = x;
        int end = n-y+1;
        
        // ������ӵ�λ��
        for(int pos = begin; pos <= end; pos++) {
        	// �����ӷֳ��������ж����ַַ�����ߵ�����λ���Ѿ�ȷ��
        	int left = pos - 1;
        	int right = n - pos;
        	BigInteger tmp = getCombine(n-1, left);
        	BigInteger subLeft = help(left, x-1);
        	BigInteger subRight = help(right, y-1);
        	
        	res = res.add(subLeft.multiply(subRight).multiply(tmp));
        }
        
        return res.toString(10);
    } 
    
    // total����Ҫ��visible���ɼ��������ж�����
    public static BigInteger help(int total, int visible) {
    	BigInteger res = BigInteger.ZERO;
    	if(total < visible) {
    		res = BigInteger.ZERO;
    	} else if(total == visible) {
    		res = BigInteger.ONE;
    	} else {
    		if(total_vis_num.containsKey(total) && total_vis_num.get(total).containsKey(visible)) {
    			res = total_vis_num.get(total).get(visible);
    		} else {
    			// ������ӵ�λ��
    			for(int pos = 1; pos <= total; pos++) {
    				int left = pos - 1;
    				int right = total - pos;
    				// �ж����ַַ������ӷֳ�������
    				BigInteger tmp1 = getCombine(total-1, left);
    				// �ұ��ж��������
    				BigInteger tmp2 = getFac(right);
    				// ����ж��������
    				BigInteger subProb = help(left, visible-1);
    				
    				res = res.add(subProb.multiply(tmp1.multiply(tmp2)));
    			}
    		}
    	}
    	if(total_vis_num.containsKey(total)) {
    		total_vis_num.get(total).put(visible, res);
    	} else {
    		Map<Integer, BigInteger> map = new HashMap<>();
    		map.put(visible, res);
    		total_vis_num.put(total, map);
    	}
    	return res;
    }
    
    public static BigInteger getFac(int num) {
    	if(facs.containsKey(num)) {
    		return facs.get(num);
    	} else {
    		BigInteger res = BigInteger.ONE;
    		if(num < 2) {
    			res = BigInteger.ONE;
    		} else {
    			for(int i = 2; i <= num; i++) {
    				res = res.multiply(new BigInteger(String.valueOf(i)));
    			}
    		}
    		facs.put(num, res);
    		
    		return res;
    	}
    }
    
    public static BigInteger getCombine(int total, int visible) {
    	BigInteger nume = getFac(total);
    	BigInteger deno1 = getFac(visible);
    	BigInteger deno2 = getFac(total - visible);
    	
    	return nume.divide(deno1).divide(deno2);
    }
    
    public static void main(String[] args) {
		System.out.println(answer(1, 2, 6));
	}
}
