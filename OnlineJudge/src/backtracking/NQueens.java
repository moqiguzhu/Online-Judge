package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ref:https://en.wikipedia.org/wiki/Eight_queens_puzzle
 * 
 * @author moqiguzhu
 * @date 2015-12-02
 * @version 1.0
 */

public class NQueens {
  private int n;
  private int[] b;

  public void init(int size) {
    n = size;
    b = new int[size];
  }

  // b[y]的语义是第y行的第b[y]个元素放一个皇后
  public boolean unsafe(int y) {
    int x = b[y];
    for (int i = 1; i <= y; i++) {
      int t = b[y - i];
      if (t == x || t == x - i || t == x + i) {
        return true;
      }
    }

    return false;
  }

  public String[] putboard() {
    String[] strArr = new String[n];
    for (int y = 0; y < n; y++) {
      String temp = new String();
      for (int x = 0; x < n; x++) {
        if (b[y] == x) {
          temp += "Q";
        } else {
          temp += ".";
        }
      }
      strArr[y] = temp;
    }

    return strArr;
  }

  // 一行一行来放
  public List<String[]> solveNQueens(int n) {
    init(n);
    List<String[]> result = new ArrayList<>();
    int y = 0;
    b[0] = -1;
    while (y >= 0) {
      do { // 寻找当前合适的位置
        b[y]++;
      } while ((b[y] < n) && unsafe(y));

      if (b[y] < n) {
        if (y < n - 1) {
          b[++y] = -1; // 准备开始填充下一行
        } else {
          result.add(putboard()); // 找到一个可行方案
        }
      } else { // 需要回溯
        y--;
      }
    }

    return result;
  }

  public static void main(String[] args) {
    NQueens nq = new NQueens();
    int[] testcases = {4};
    List<String[]> result = new ArrayList<>();
    for (int i = 0; i < testcases.length; i++) {
      result = nq.solveNQueens(testcases[i]);
      for (String[] strs : result) {
        System.out.println(Arrays.toString(strs));
      }
    }
  }
}
