package string;

public class PalindromeNumber {
    public boolean isPalindrome(int x) {
    	StringBuilder sb = new StringBuilder(String.valueOf(x));
    	return sb.reverse().toString().equals(sb.toString());
    }

}
