package Leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionAddOperators {
  public List<String> addOperators(String num, int target) {
    return null;
  }
  
  public Map<String, Integer> help(String str) {
    Map<String, Integer> map = new HashMap<>();
    if(str.length() == 1) {
      map.put(str, Integer.parseInt(str));
      return map;
    }
    if(str.charAt(0) != '0') {
      map.put(str, Integer.parseInt(str));
    }
    for(int i = 1; i < str.length(); i++) {
      Map<String, Integer> temp = help(str.substring(i));
      String left = str.substring(0,i);
      int le = Integer.parseInt(left);
      for(String s : temp.keySet()) {
        map.put(left + "+" + s, le + temp.get(s));
        map.put(left + "-" + s, le - temp.get(s));
        map.put(left + "*" + s, le * temp.get(s));
      }
    }
    
    return map;
  }
  
  public static void main(String[] args) {
    ExpressionAddOperators eao = new ExpressionAddOperators();
    String s = "232";
    System.out.println(eao.help(s));
  }
}
