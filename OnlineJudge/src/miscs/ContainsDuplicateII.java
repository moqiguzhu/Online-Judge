package miscs;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author moqiguzhu
 * @date 2016-01-08
 * @version 1.0
 */
public class ContainsDuplicateII {
  public boolean naive_containsNearbyDuplicate(int[] nums, int k) {
    Map<Integer, TreeSet<Integer>> value_indexes = new HashMap<>();
    
    for(int i = 0; i < nums.length; i++) {
      if(!value_indexes.containsKey(nums[i])) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(i);
        value_indexes.put(nums[i], set);
      } else {
        value_indexes.get(nums[i]).add(i);
      }
    }
    
    for(int value : value_indexes.keySet()) {
      if(value_indexes.get(value).size() < 2) {
        continue;
      } else {
        int pre = value_indexes.get(value).first();
        for(int x : value_indexes.get(value)) {
          if(Math.abs(x-pre) <= k && x != pre)
              return true;
          pre = x;
        }
      }
    }
    
    return false;
  }
  
  class N {
    private int value;
    private int index;
    
    public N(int value, int index) {
      this.value = value;
      this.index = index;
    }
  }
  public boolean containsNearbyDuplicate(int[] nums, int k) {
    if(nums == null || nums.length < 2) {
      return false;
    }
    
    N[] Ns = new N[nums.length];
    for(int i = 0; i < nums.length; i++) {
      Ns[i] = new N(nums[i], i);
    }
    
    Arrays.sort(Ns, new Comparator<N>() {

      @Override
      public int compare(N o1, N o2) {
        // TODO Auto-generated method stub
        if(o1.value > o2.value) {
          return 1;
        } else if(o1.value == o2.value) {
          if(o1.index > o2.index) return 1;
          else if(o1.index == o2.index) return 0;
          else return -1;
        } else {
          return -1;
        }
      }
      
    });
    
    N last = Ns[0];
    for(int i = 1; i < Ns.length; i++) {
      if(Ns[i].value == last.value && Math.abs(last.index-Ns[i].index) <= k)
        return true;
      last = Ns[i];
    }
    
    return false;
  }
}
