package hiho.coordinates;

/**
 * 
 * @author moqiguzhu
 * @date 2016-03-20
 * @version 1.0
 *
 */
public class NextPermutation {
  public String nextPermutation(String str) {
    StringBuilder sb = new StringBuilder(str);
    int x1, x2;
    x1 = str.length() - 1;
    while(x1 > 0 && str.charAt(x1-1) > str.charAt(x1)) {
      x1--;
    }
    x1 = x1 - 1;
    // 已经是最后一个permutation
    if(x1 < 0) return null;
    
    x2 = str.length()-1;
    while(str.charAt(x2) < str.charAt(x1)) {
      x2--;
    }
    
    char t;
    t = sb.charAt(x1);
    sb.setCharAt(x1, str.charAt(x2));
    sb.setCharAt(x2, t);
    
    StringBuilder t_sb = new StringBuilder(sb.substring(x1+1, str.length()));
    t_sb.reverse();
    sb.replace(x1+1, sb.length(), t_sb.toString());
    
    return sb.toString();
  }
  
  public static void main(String[] args) {
    NextPermutation np = new NextPermutation();
    System.out.println(np.nextPermutation("dcba"));
  }
}
