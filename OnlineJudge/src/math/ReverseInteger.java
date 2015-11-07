package math;

public class ReverseInteger {
  public int reverse(int x) {
    boolean flag = false;
    if (x < 0) {
      flag = true;
      x = -x;
    }

    double temp = 0;
    temp = x % 10;
    x = x / 10;
    while (x != 0) {
      temp = temp * 10 + x % 10;
      x = x / 10;
    }
    
    // pay attention to that -Integer.MIN_VALUE = Integer.MIN_VALUE
    if (temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE) {
      return 0;
    } else if (flag) {
      return (int) -temp;
    } else {
      return (int) temp;
    }
  }

  public int[] createTestCases() {
    int[] testcases =
        {0, 1, 2, 3, -1, -2, -3, Integer.MAX_VALUE, Integer.MIN_VALUE, 1000000003, 10100};

    return testcases;
  }

  public static void main(String[] args) {
    ReverseInteger ri = new ReverseInteger();
    int[] testcases = ri.createTestCases();
    
    for (int i = 0; i < testcases.length; i++) {
      System.out.println(ri.reverse(testcases[i]));
    }
    
    // test -Integer.MIN_VALUE
    System.out.println(-Integer.MIN_VALUE);
  }

}
