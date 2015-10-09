package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordPattern {
  public boolean naive_wordPattern(String pattern, String str) {
    String regex = "\\s";
    String[] words = str.split(regex);

    // ======================边界============================
    if (pattern.length() != words.length) {
      return false;
    }

    Map<Character, String> pattern_word = new HashMap<>();
    Map<String, Character> word_pattern = new HashMap<>();
    for (int i = 0; i < pattern.length(); i++) {
      char c = pattern.charAt(i);
      if (!pattern_word.containsKey(c) && !word_pattern.containsKey(words[i])) {
        pattern_word.put(c, words[i]);
        word_pattern.put(words[i], c);
      } else if (pattern_word.containsKey(c) && word_pattern.containsKey(words[i])) {
        if (pattern_word.get(c).equals(words[i]) && word_pattern.get(words[i]) == c) {
          continue;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }

    return true;
  }

  public boolean elegant_wordPattern(String pattern, String str) {
    String[] arr = str.split(" ");
    HashMap<Character, String> map = new HashMap<Character, String>();

    if (arr.length != pattern.length()) {
      return false;
    }

    for (int i = 0; i < arr.length; i++) {
      char c = pattern.charAt(i);
      if (map.containsKey(c)) {
        if (!map.get(c).equals(arr[i])) {
          return false;
        }
      } else {
        if (map.containsValue(arr[i])) {
          return false;
        }
        map.put(c, arr[i]);
      }
    }
    return true;
  }

  public List<String> createTestcases() {
    List<String> testcases = new ArrayList<>();

    String pattern1 = "abba";
    String str1 = "dog dog cat dog";
    testcases.add(pattern1);
    testcases.add(str1);

    return testcases;
  }

  public static void main(String[] args) {
    WordPattern wp = new WordPattern();
    List<String> testcases = wp.createTestcases();

    for (int i = 0; i < testcases.size() / 2; i++) {
      System.out.println(wp.elegant_wordPattern(testcases.get(i * 2), testcases.get(i * 2 + 1)));
    }
  }
}
