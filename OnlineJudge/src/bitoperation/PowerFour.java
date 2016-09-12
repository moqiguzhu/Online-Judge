package bitoperation;

public class PowerFour {
  public boolean isPowerOfFour(int num) {
    if (num <= 0)
      return false;

    if ((num & (num - 1)) == 0 && (num & 0x55555555) != 0)
      return true;

    return false;
  }

  public boolean ano_isPowerOfFour(int num) {
    return (num&(num-1))==0 && num>0 && (num-1)%3==0;
  }
}
