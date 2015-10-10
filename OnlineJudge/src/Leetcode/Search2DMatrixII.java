package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一种解法是二维的二分搜索。
 * 
 * 第二种解法是DP。子问题和原问题有相同的性质。
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-09
 */

public class Search2DMatrixII {
  private boolean flag = false;

  public boolean searchMatrix(int[][] matrix, int target) {
    if (target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1]) {
      return false;
    }
    return help(matrix, 0, matrix.length - 1, 0, matrix[0].length - 1, target);
  }

  public boolean help(int[][] matrix, int row_begin, int row_end, int col_begin, int col_end,
      int target) {
    int new_row_end = 0, new_row_begin = 0;
    int new_col_end = 0, new_col_begin = 0;
    while (row_end > row_begin || col_end > col_begin) {
      if (flag) {
        return flag;
      }
      if (row_end > row_begin) {
        new_row_end = firstBigger(matrix, col_begin, row_begin, row_end, 1, target) - 1;
        new_row_begin = firstBigger(matrix, col_end, row_begin, row_end, 1, target);
      }
      if (col_end > col_begin) {
        new_col_end = firstBigger(matrix, row_begin, col_begin, col_end, 2, target) - 1;
        new_col_begin = firstBigger(matrix, row_end, col_begin, col_end, 2, target);
      }
      if (new_row_begin > new_row_end || new_col_begin > new_col_end) {
        return flag || false;
      }
      row_end = new_row_end;
      row_begin = new_row_begin;
      col_end = new_col_end;
      col_begin = new_col_begin;
    }

    return flag || matrix[row_begin][col_begin] == target;
  }

  // target > x 使得x的下标最大
  public int firstBigger(int[][] matrix, int row_col, int begin, int end, int tag, int target) {
    if (flag) {
      return 0;
    }
    int mid = begin + ((end - begin) >> 1);
    int tmp = 0;
    if (tag == 1) {
      tmp = matrix[mid][row_col];
    } else {
      tmp = matrix[row_col][mid];
    }

    if (begin == end) {
      if (tmp == target) {
        flag = true;
      }
      if (target > tmp) {
        return mid + 1;
      } else {
        return mid;
      }
    }

    if (tmp == target) {
      flag = true;
      return mid;
    } else if (tmp > target) {
      return firstBigger(matrix, row_col, begin, mid, tag, target);
    } else {
      return firstBigger(matrix, row_col, mid + 1, end, tag, target);
    }
  }
  
  public List<int[][]> createTestcases() {
    List<int[][]> testcases = new ArrayList<>();

    int[][] matrix1 = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22},
        {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};

    // testcases.add(matrix1);

    int[][] matrix2 = {{1, 4}, {2, 5}};

    // testcases.add(matrix2);

    int[][] matrix3 = {{1, 1}};
    testcases.add(matrix3);

    return testcases;
  }

  public boolean naive_searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
      return false;
    }
    int col = matrix[0].length - 1;
    int row = 0;
    while (col >= 0 && row <= matrix.length - 1) {
      if (target == matrix[row][col]) {
        return true;
      } else if (target < matrix[row][col]) {
        col--;
      } else if (target > matrix[row][col]) {
        row++;
      }
    }
    return false;
  }

  public static void main(String[] args) {
    Search2DMatrixII sd2 = new Search2DMatrixII();
    List<int[][]> testcases = sd2.createTestcases();

    for (int i = 0; i < testcases.size(); i++) {
      System.out.println(sd2.searchMatrix(testcases.get(i), 1));
    }
  }
}
