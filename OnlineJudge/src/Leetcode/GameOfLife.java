package Leetcode;

import Utils.Utils;

/**
 * 状态: 前一位表示下一代的状态,后一位表示当前的状态 
 * 00: 死->死 
 * 10: 死->活 
 * 01: 活->死 
 * 11: 活->活
 * 
 * https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 * https://leetcode.com/discuss/62038/c-ac-code-o-1-space-o-mn-time
 * 
 * @author moqiguzhu
 * @version 1.0
 * @date 2015-10-08
 */
public class GameOfLife {

  public void gameOfLife(int[][] board) {
    int[] ys = {-1, 0, 1, -1, 1, -1, 0, 1};
    int[] xs = {-1, -1, -1, 0, 0, 1, 1, 1};

    int num_lives = 0;
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        num_lives = 0;
        for (int k = 0; k < xs.length; k++) {
          int x = i + xs[k];
          int y = j + ys[k];
          if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            continue;
          }
          if ((board[x][y] & 1) == 1) {
            num_lives++;
          }
        }
        if (board[i][j] == 0) {
          if (num_lives == 3) {
            board[i][j] = 2;
          }
        } else {
          if (num_lives > 3 || num_lives < 2) {
            board[i][j] = 1;
          } else {
            board[i][j] = 3;
          }
        }
      }
    }
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        board[i][j] >>= 1;
      }
    }
  }
  
  public static void main(String[] args) {
    GameOfLife g = new GameOfLife();
    int[][] b = {{1,1},{1,0}};
    g.gameOfLife(b);
    
    Utils.printArray(b);
  }
}
