package miscs;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Pay attention to: 如果begin是null，下面这行语句会报空指针异常 begin == last
 * 
 * @author moqiguzhu
 * @date 2016-01-08
 * @version 1.0
 */
public class SummaryRanges {

  public List<String> summaryRanges(int[] nums) {
    List<String> res = new ArrayList<>();

    Integer begin = null;
    int last = -1;

    for (int x : nums) {
      if (begin == null) {
        begin = x;
        last = x;
      } else {
        if (x == last + 1) {
          last = x;
        } else {
          String tmp;
          if (last > begin)
            tmp = begin + "->" + last;
          else
            tmp = "" + begin;
          res.add(tmp);
          begin = x;
          last = x;
        }
      }
    }
    if (begin != null) {
      if (last > begin)
        res.add(begin + "->" + last);
      else
        res.add("" + begin);
    }

    return res;
  }

  public static void main(String[] args) {
    SummaryRanges sr = new SummaryRanges();
    int[] nums = {0, 1};
    List<String> res = sr.summaryRanges(nums);

    System.out.println(res);
  }
}
