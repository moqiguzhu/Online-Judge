package miscs;

public class PerfectSquare {
    public boolean isPerfectSquare(int num) {
        if(num < 0) {
        	return false;
        }
        if(num <= 1) {
        	return true;
        }
        
        for(int i = 1; i < num / 2 + 1; i++) {
        	if(i * i == num) {
        		return true;
        	}
        	if(i * i > num) {
        		return false;
        	}
        }
        
        return false;
    }
    
    public static void main(String[] args) {
    	PerfectSquare ps = new PerfectSquare();
    	
    	System.out.println(ps.isPerfectSquare(100));
	}
}
