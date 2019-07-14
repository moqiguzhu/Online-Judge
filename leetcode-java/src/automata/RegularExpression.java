package automata;

import java.util.ArrayList;
import java.util.List;

/**
 * 这是一道臭名昭著的问题，因为其边界实在是太多了。 用c语言实现还好，用Java实现的话，因为Java的string需要检查有没有越界。导致最后写出来的代码异常臃肿丑陋。
 * 参考链接：http://articles.leetcode.com/2011/09/regular-expression-matching.html
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-11-16
 */

public class RegularExpression {

  public boolean isMatch(String s, String p) {
    return isMatch(s, p, 0, 0);
  }

  // Java语言的实现
  public boolean isMatch(String s, String p, int begin_s, int begin_p) {
    if (begin_p >= p.length()) {
      return begin_s >= s.length();
    }
    if (begin_s >= s.length()) {
      return begin_p + 1 < p.length() && p.charAt(begin_p + 1) == '*'
          && isMatch(s, p, begin_s, begin_p + 2);
    }

    if (begin_p + 1 == p.length()) {
      return begin_s + 1 == s.length()
          && (p.charAt(begin_p) == '.' || p.charAt(begin_p) == s.charAt(begin_s));
    } else {
      // next char is not '*': must match current character
      if (p.charAt(begin_p + 1) != '*') {
        return ((p.charAt(begin_p) == s.charAt(begin_s))
            || (p.charAt(begin_p) == '.' && begin_s < s.length()))
            && isMatch(s, p, begin_s + 1, begin_p + 1);
      } else {
        // next char is '*'
        while (begin_s < s.length() && (p.charAt(begin_p) == s.charAt(begin_s))
            || (p.charAt(begin_p) == '.' && begin_s < s.length())) {
          if (isMatch(s, p, begin_s, begin_p + 2)) {
            return true;
          }
          begin_s++;
        }

        return isMatch(s, p, begin_s, begin_p + 2);
      }
    }
  }

  // c语言的实现
  /**
  bool isMatch(const char *s, const char *p) {
    assert(s && p);
    if (*p == '\0') return *s == '\0';

    // next char is not '*': must match current character
    if (*(p+1) != '*') {
      assert(*p != '*');
      return ((*p == *s) || (*p == '.' && *s != '\0')) && isMatch(s+1, p+1);
    }
    // next char is '*'
    while ((*p == *s) || (*p == '.' && *s != '\0')) {
      if (isMatch(s, p+2)) return true;
      s++;
    }
    return isMatch(s, p+2);
  }
  */

  public List<String> createTestcases() {
    List<String> testcases = new ArrayList<>();

    testcases.add("aa");
    testcases.add("a");

    testcases.add("aa");
    testcases.add("aa");

    testcases.add("aaa");
    testcases.add("aa");

    testcases.add("aa");
    testcases.add("a*");

    testcases.add("aa");
    testcases.add(".*");

    testcases.add("ab");
    testcases.add(".*");

    testcases.add("aab");
    testcases.add("c*a*b");

    testcases.add("a");
    testcases.add("ab*");

    testcases.add("a");
    testcases.add(".*..a*");

    testcases.add("");
    testcases.add("c*c*");

    return testcases;
  }

  public static void main(String[] args) {
    RegularExpression re = new RegularExpression();
    List<String> testcases = re.createTestcases();

    for (int i = 0; i < testcases.size() / 2; i++) {
      System.out.println(re.isMatch(testcases.get(i * 2), testcases.get(i * 2 + 1)));
    }
  }
}
