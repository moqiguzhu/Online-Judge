package Leetcode;

public class AddDigits {
  public int addDigits(int num) {
    int tmp = 0;
    while(num != 0) {
      tmp += num % 10;
      num /= 10;
    }
    return (tmp - 1) % 9 + 1;
  }
}
