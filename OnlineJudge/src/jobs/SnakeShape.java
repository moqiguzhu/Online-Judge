package jobs;

/**
 * 腾讯2016校招实习生笔试编程题最后一道
 * 蛇形输出
 * @author moqiguzhu
 * @date 2016-04-04
 * @version 1.0
 */
import java.util.Scanner;

public class SnakeShape {
  // 右 下 左 上
  public static int[] pos_x = new int[] {0, 1, 0, -1};
  public static int[] pos_y = new int[] {1, 0, -1, 0};

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int width = sc.nextInt();
    int[] table = new int[width * width];

    int count = width - 1;
    int directCount = 0;
    int num = 1;
    int cur_x = 0, cur_y = 0;

    table[cur_x*width+cur_y] = num++;
    for (int i = 0; i < width-1; i++) {
      cur_x += pos_x[directCount];
      cur_y += pos_y[directCount];
      table[cur_x*width+cur_y] = num++;
    }
    directCount = (directCount + 1) % 4;
    
    while(count >= 1) {
      for(int i = 0; i < 2; i++) {
        for(int j = 0; j < count; j++) {
          cur_x += pos_x[directCount];
          cur_y += pos_y[directCount];
          table[cur_x*width+cur_y] = num++;
        }
        directCount = (directCount + 1) % 4;
      }
      count--;
    }

    for (int i = 0; i < table.length; i++) {
      System.out.print(table[i] + " ");
    }
    System.out.println();
    sc.close();
  }
}
