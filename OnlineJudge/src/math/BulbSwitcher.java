package math;

/**
 * 当我写出numFactors函数的时候，离真相就已经很接近了。 elegant solution comes from
 * https://leetcode.com/discuss/76107/one-line-java-with-explanation.
 * 注意这个题有一个标签叫brainteaser.
 * 
 * @author moqiguzhu
 * @date 2016-01-15
 * @version 1.0
 */
public class BulbSwitcher {
  public int naive_bulbSwitch(int n) {
    if (n <= 0)
      return 0;

    int count = 0;
    for (int i = 1; i <= n; i++) {
      if (numFactors(i) % 2 == 1)
        count++;
    }
    return count;
  }

  private int numFactors(int x) {
    assert(x >= 1);
    int count = 0;
    for (int i = 1; i <= Math.sqrt((double) x); i++) {
      if (x % i == 0)
        count++;
    }
    if (Math.sqrt((double) x) == (int)Math.sqrt((double) x)) {
      return count * 2 - 1;
    } else {
      return count * 2;
    }
  }

  public int elegant_bulbSwitch(int n) {
    return (int) Math.sqrt(n);
  }

  public void testNumFactors() {
    System.out.println(numFactors(1));
    System.out.println(numFactors(2));
    System.out.println(numFactors(3));
    System.out.println(numFactors(4));
    System.out.println(numFactors(5));
    System.out.println(numFactors(6));
  }

  public static void main(String[] args) {
    BulbSwitcher bs = new BulbSwitcher();

    for (int i = 1; i < 100; i++) {
      System.out.print(bs.naive_bulbSwitch(i) + " ");
    }
    System.out.println();
  }
}
