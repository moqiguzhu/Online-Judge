package bitoperation;

import java.util.ArrayList;
import java.util.List;

public class PowerofTwo {
  public boolean naive_isPowerOfTwo(int n) {
    if(n <= 0) {
      return false;
    }
    while(n != 1) {
      if(n % 2 != 0) {
        return false;
      }
      n /= 2;
    }
    return true;
  }
  
  public boolean elegant_isPowerOfTwo(int n) {
    int count = 0;
    int numOnebits = 0;
    int n_copy = n;
    while(count++ < 31) {
      numOnebits += (n & 1);
      n >>= 1;
    }
    return n_copy > 0 && numOnebits == 1;
  }
  
  public boolean awesome_isPowerofTwo(int n) {
    return n > 0 && (n & (n-1)) == 0;
  }
  
  public List<Integer> createTestcases() {
    List<Integer> testcases = new ArrayList<>();
    
    testcases.add(0);
    testcases.add(1);
    testcases.add(2);
    testcases.add(3);
    testcases.add(8);
    testcases.add(9);
    testcases.add(1024);
    testcases.add(-1);
    
    return testcases;
  }
  
  public static void main(String[] args) {
    PowerofTwo pt = new PowerofTwo();
    List<Integer> testcases = pt.createTestcases();
    
    for(int i = 0; i < testcases.size(); i++) {
      System.out.println(pt.elegant_isPowerOfTwo(testcases.get(i)));
    }
  }
}
