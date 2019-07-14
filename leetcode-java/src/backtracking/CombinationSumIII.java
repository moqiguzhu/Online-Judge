package backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Backtracking
 * Have no time to explain too much. Hurry to meet girlfriend!!!
 * 
 * @author moqiguzhu
 * @date 2015-12-14
 * @version 1.0
 * 
 */

public class CombinationSumIII {
  List<List<Integer>> result;
  int MAX = 9;
  int MAX_count;
  public List<List<Integer>> combinationSum3(int k, int n) {
    assert(k <= 9);
    
    result = new ArrayList<List<Integer>>();
    
    this.MAX_count = k;
    LinkedList<Integer> list = new LinkedList<>(); 
    
    generate(1, list, n, 0);
    
    return result;
  }
  
  public void generate(int cur, LinkedList<Integer> list, int remaining, int count) {
    if(count > MAX_count || MAX-cur+1 < MAX_count-count || sum_cur_to_MAX(cur) < remaining) {
      return;
    } else if(remaining == 0 && count == MAX_count) {
      List<Integer> temp = new LinkedList<>(list);
      result.add(temp);
    } else {
      list.addLast(cur);
      generate(cur+1, list, remaining-cur, count+1);
      list.removeLast();
      
      generate(cur+1, list, remaining, count);
    }
  }
  
  public double sum_cur_to_MAX(int cur) {
    return (cur + MAX) * (MAX - cur + 1) / 2.0;
  }
  
  public List<Integer> createTestcases() {
    List<Integer> testcases = new ArrayList<>();
    
    testcases.add(3);  
    testcases.add(7);
    
    testcases.add(3); 
    testcases.add(9);
    
    return testcases;
  }
  
  public static void main(String[] args) {
    CombinationSumIII cs3 = new CombinationSumIII();
    List<Integer> testcases = cs3.createTestcases();
    List<List<Integer>> result;
    
    for(int i = 0; i < testcases.size()/2; i++) {
      result = cs3.combinationSum3(testcases.get(i*2), testcases.get(i*2+1));
      System.out.println(result);
    }
  }
}
