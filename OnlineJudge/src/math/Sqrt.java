package math;

/**
 * @author moqiguzhu
 * @date 2015-12-31
 * @version 1.0
 */
public class Sqrt {
  public int sqrt(int x) {
    double eps = 10e-10;
    double val = x;
    double last;// 保存上一个计算的值
    do {
      last = val;
      val = (val + x / val) / 2;
    } while (Math.abs(val - last) > eps);
    return (int) val;
  }

  public static void main(String[] args) {
    Sqrt s = new Sqrt();
    System.out.println(s.sqrt(121));
  }
}
