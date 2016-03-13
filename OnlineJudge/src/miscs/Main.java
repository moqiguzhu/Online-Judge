package Main;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Andy on 3/13/16.
 */

// 纸上代码的能力 from hihocoder.com
// !!! need to debug

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numTests = sc.nextInt();
        int numSnacks;
        double ans = 0;
        double[][] table = new double[5][3];
        double sati;
        int price;

        for(int i = 0; i < numTests; i++) {
            numSnacks = sc.nextInt();
            for(int j = 0; j < numSnacks; j++) {
                sati = sc.nextDouble();
                price = sc.nextInt();

                int mod = price % 5;
                if(table[mod][0] < sati) table[mod][0] = sati;

                for(int p = 0; p < table.length; p++) {
                    int cur = (mod + p) % 5;

                    for(int q = 0; q < table[0].length-1; q++) {
                        if(table[p][q] != 0) {

                            if(table[p][q] + sati > table[cur][q+1]) table[cur][q+1] = table[p][q] + sati;
                        }
                    }
                }
            }
            ans = Math.max(table[0][0], table[0][1]);
            ans = Math.max(table[0][2], ans);

            System.out.println(Arrays.toString(table[0]));

            // print answer
            System.out.println(ans);
            ans = 0;
        }

        sc.close();
    }
}
