package math;

/**
 * 
 * elegant solution comes from: https://leetcode.com/problems/integer-to-english-words/
 * 
 * @author moqiguzhu
 * @date 2016-02-24
 * @version 1.0
 *
 */
public class IntegertoEnglishWords {
  private String[] dict = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
      "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
      "Eighteen", "Nineteen"};

  public String numberToWords(int num) {
    String res = "";

    if (num == 0) {
      return "Zero";
    }

    String numStr = num + "";
    int len = numStr.length();

    if (len <= 3) {
      res = help(numStr, 0, len);
    } else if (len <= 6) {
      res = help2(numStr, 0, len - 3, "Thousand") + help(numStr, len - 3, len);
    } else if (len <= 9) {
      res = help2(numStr, 0, len - 6, "Million") + help2(numStr, len - 6, len - 3, "Thousand")
          + help(numStr, len - 3, len);
    } else {
      res = help2(numStr, 0, len - 9, "Billion") + help2(numStr, len - 9, len - 6, "Million")
          + help2(numStr, len - 6, len - 3, "Thousand") + help(numStr, len - 3, len);
    }

    return res.replaceAll("\\s+", " ").trim();
  }

  /**
   * 
   * @param num string form of num
   * @param left inclusive
   * @param right exclusive
   * @return English words of digits from left to right
   */
  public String help(String num, int left, int right) {
    String subNum = num.substring(left, right);
    int s = Integer.valueOf(subNum);
    String res = "";

    if (s >= 100) {
      res = dict[s / 100] + " Hundred " + help1(s % 100);
    } else {
      res = help1(s);
    }

    return res;
  }

  public String help1(int num) {
    assert(num < 100);
    String res = "";

    if (num == 0) {
      return res;
    }

    if (num < 20) {
      res = dict[num];
    } else {
      int a = num / 10;
      int b = num % 10;
      if (a == 2) {
        if(b == 0) {
          res = "Twenty";
        } else {
          res = "Twenty " + dict[b];
        }
      } else {
        String tmp = dict[10 + a];
        if (tmp.equals("Fourteen")) {
          if(b == 0) {
            res = "Forty";
          } else {
            res = "Forty " + dict[b];
          }
        } else {
          if(b == 0) {
            res = tmp.substring(0, tmp.length() - 3) + "y";
          } else {
            res = tmp.substring(0, tmp.length() - 3) + "y " + dict[b];
          }
        }
      }
    }

    return res;
  }

  public String help2(String num, int left, int right, String s) {
    String res = help(num, left, right);
    if (res == "") {
      res = "";
    } else {
      res += " " + s + " ";
    }

    return res;
  }

  public int[] createTestCases() {
    int[] testcases = {Integer.MAX_VALUE, 123, 123000, 1000010, 1000, 0, 10010, 20, 40, 50, 60, 99, 101, 100};

    return testcases;
  }
  
  // first "" is useful
  // last two "" meaningless except for sentinel
  private final String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
  private final String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
  private final String[] belowHundred = new String[] {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

  public String elegant_numberToWords(int num) {
      if (num == 0) return "Zero";
      return helper(num); 
  }

  private String helper(int num) {
      String result = new String();
      if (num < 10) result = belowTen[num];
      else if (num < 20) result = belowTwenty[num -10];
      else if (num < 100) result = belowHundred[num/10] + " " + helper(num % 10);
      else if (num < 1000) result = helper(num/100) + " Hundred " +  helper(num % 100);
      else if (num < 1000000) result = helper(num/1000) + " Thousand " +  helper(num % 1000);
      else if (num < 1000000000) result = helper(num/1000000) + " Million " +  helper(num % 1000000);
      else result = helper(num/1000000000) + " Billion " + helper(num % 1000000000);
      return result.trim();
  }

  public static void main(String[] args) {
    IntegertoEnglishWords iew = new IntegertoEnglishWords();
    int[] testcases = iew.createTestCases();

    for (int x : testcases) {
      System.out.println(iew.elegant_numberToWords(x));
    }
  }
}
