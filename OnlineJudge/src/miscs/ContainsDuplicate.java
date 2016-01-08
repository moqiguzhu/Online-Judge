package miscs;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {
  public boolean containsDuplicate(int[] nums) {
    Set<Integer> set = new HashSet<>();
    
    int curSize;
    for(int x : nums) {
      curSize = set.size();
      set.add(x);
      if(curSize == set.size())
        return true;
    }
    
    return false;
  }
}
