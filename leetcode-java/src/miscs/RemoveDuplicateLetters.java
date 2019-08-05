package miscs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.com/discuss/75738/java-solution-using-stack-with-comments
 * 
 * @author moqiguzhu
 * @date 2016-02-24
 * @version 1.0
 */
public class RemoveDuplicateLetters {
  public String removeDuplicateLetters(String sr) {

    int[] res = new int[26]; // will contain number of occurences of character (i+'a')
    boolean[] visited = new boolean[26]; // will contain if character (i+'a') is present in current
                                         // result Stack
    char[] ch = sr.toCharArray();
    for (char c : ch) { // count number of occurences of character
      res[c - 'a']++;
    }
    Stack<Character> st = new Stack<>(); // answer stack
    int index;
    for (char s : ch) {
      index = s - 'a';
      res[index]--; // decrement number of characters remaining in the string to be analysed
      if (visited[index]) // if character is already present in stack, dont bother
        continue;
      // if current character is smaller than last character in stack which occurs later in the
      // string again
      // it can be removed and added later e.g stack = bc remaining string abc then a can pop b and
      // then c
      while (!st.isEmpty() && s < st.peek() && res[st.peek() - 'a'] != 0) {
        visited[st.pop() - 'a'] = false;
      }
      st.push(s); // add current character and mark it as visited
      visited[index] = true;
    }

    StringBuilder sb = new StringBuilder();
    // pop character from stack and build answer string from back
    while (!st.isEmpty()) {
      sb.insert(0, st.pop());
    }
    return sb.toString();
  }


  public List<String> createTestcases() {
    List<String> testcases = new ArrayList<>();

    // testcases.add("bcabc");
    testcases.add("cbacdcbc");

    return testcases;
  }

  public static void main(String[] args) {
    RemoveDuplicateLetters rdl = new RemoveDuplicateLetters();
    List<String> testcases = rdl.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(rdl.removeDuplicateLetters(testcases.get(i)));
    }
  }
}