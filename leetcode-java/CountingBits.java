package test;

public class CountingBits {
    public int[] countBits(int num) {
        if(num < 0) {
        	return null;
        }
        
        int[] bitsNum = new int[num+1];
        for(int i = 1; i < bitsNum.length; i++) {
        	bitsNum[i] = bitsNum[(i & i-1)] + 1;
        }
        
        return bitsNum;
    }
    
    public static void main(String[] args) {
		CountingBits cb = new CountingBits();
		int[] res = cb.countBits(5);
		for(int x : res) {
			System.out.print(x + " ");
		}
	}
}
