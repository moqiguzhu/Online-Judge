package math;

/**
 * @author moqiguzhu
 */
public class PalindromeNumber {
  // 负数不能是回文数
  public boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }

    int temp = 0;
    int tempx = x;
    temp = x % 10;
    x = x / 10;
    while (x != 0) {
      temp = temp * 10 + x % 10;
      x = x / 10;
    }
    
    return temp == tempx;
  }

  public int[] createTestCases() {
    int[] testcases = {Integer.MAX_VALUE, Integer.MIN_VALUE, 1, 123, 2, 9, 89, 1000000001, 10, 100};
    
    return testcases;
  }

  public static void main(String[] args) {
    PalindromeNumber pn = new PalindromeNumber();
    int[] testcases = pn.createTestCases();
    
    for (int i = 0; i < testcases.length; i++) {
      System.out.println(pn.isPalindrome(testcases[i]));
    }
  }
}
