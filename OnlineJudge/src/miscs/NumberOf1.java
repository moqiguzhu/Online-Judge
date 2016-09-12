package miscs;

public class NumberOf1 {
  public int numberOf1(int n) {
    int count = 0;
    while(n != 0) {
      count++;
      n = n & (n-1);
    }
    return count;
  }
  
  public static void main(String[] args) {
    NumberOf1 no = new NumberOf1();
    
    System.out.println(no.numberOf1(0));
    System.out.println(no.numberOf1(Integer.MAX_VALUE));
    // only one 1 for 2^-31
    System.out.println(no.numberOf1(Integer.MIN_VALUE));
  }
}
