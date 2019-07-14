package bitoperation;

import java.util.ArrayList;
import java.util.List;

public class SingleNumber {
  public int singleNumber(int[] A) {
      int result = 0;
      for(int i = 0; i < A.length; i++) 
          result ^= A[i];
      return result;
  }
  
  public List<int[]> createTestCases() {
      List<int[]> testcases = new ArrayList<int[]>();
      
      int[] A1 = {1};
      testcases.add(A1);
      
      int[] A2 = {1,2,2,1,3};
      testcases.add(A2);
      
      int[] A3 = {1,1,2};
      testcases.add(A3);
      
      return testcases;
  }
  
  public static void main(String[] args) {
      SingleNumber sn = new SingleNumber();
      List<int[]> testcases = sn.createTestCases();
      for(int i = 0; i < testcases.size(); i++) {
          System.out.println(sn.singleNumber(testcases.get(i)));
      }
  }
}

