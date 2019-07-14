package miscs;

import java.util.Arrays;
import java.util.Comparator;

public class ContainsDuplicate {
  class N {
    private int value;
    private int index;
    
    public N(int value, int index) {
      this.value = value;
      this.index = index;
    }
  }
  public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
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
    
    N cur;
    for(int i = 0; i < Ns.length; i++) {
      cur = Ns[i];
      int j = i + 1;
      while(j < Ns.length && (double)Ns[j].value - (double)cur.value <= t) {
        if(Math.abs(Ns[j].index - cur.index) <= k)
          return true;
        j++;
      }
    }
    
    return false;
  }
}
