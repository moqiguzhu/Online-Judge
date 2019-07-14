package bitoperation;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class MaximumProductWordLengths {
  public int maxProduct(String[] words) {
    if (words == null || words.length == 0) {
      return 0;
    }
    List<BitSet> wds = new ArrayList<>();
    for (String str : words) {
      BitSet tmp = new BitSet(26);

      char c;
      for (int i = 0; i < str.length(); i++) {
        c = str.charAt(i);
        tmp.set((int) c - 97);
      }

      wds.add(tmp);
    }

    int res = 0;
    for (int i = 0; i < wds.size(); i++) {
      for (int j = i + 1; j < wds.size(); j++) {
        // 使用get方法来完成copy操作
        BitSet tmp = wds.get(i).get(0, 26);
        tmp.and(wds.get(j));
        if (tmp.cardinality() == 0) {
          res = Math.max(words[i].length() * words[j].length(), res);
        }
      }
    }

    return res;
  }

  public int elegant_maxProduct(String[] words) {
    int[] mask = new int[words.length];
    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j < words[i].length(); j++) {
        mask[i] |= 1 << (words[i].charAt(j) - 'a');
      }
    }
    int max = 0;
    for (int i = 0; i < words.length; i++) {
      for (int j = i + 1; j < words.length; j++) {
        if ((mask[i] & mask[j]) == 0) {
          max = Math.max(words[i].length() * words[j].length(), max);
        }
      }
    }
    return max;
  }

  public List<String[]> createTestcases() {
    List<String[]> testcases = new ArrayList<>();

    String[] words1 = new String[] {"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
    testcases.add(words1);

    String[] words2 = new String[] {"a", "ab", "abc", "d", "cd", "bcd", "abcd"};
    testcases.add(words2);

    String[] words3 = new String[] {"a", "aa", "aaa", "aaaa"};
    testcases.add(words3);

    return testcases;
  }

  public static void main(String[] args) {
    MaximumProductWordLengths mpwl = new MaximumProductWordLengths();
    List<String[]> testcases = mpwl.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(mpwl.maxProduct(testcases.get(i)));
    }
  }
}
