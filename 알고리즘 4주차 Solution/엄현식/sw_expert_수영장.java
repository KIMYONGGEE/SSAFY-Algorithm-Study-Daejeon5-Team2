package firstProject;

import java.util.Arrays;
import java.util.Scanner;
 
public class sw_expert_수영장 {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int Tc = sc.nextInt();
        for (int tc = 1; tc <= Tc; tc++) {
            int oneday = sc.nextInt();
            int month = sc.nextInt();
            int three = sc.nextInt();
            int year = sc.nextInt();
            int cal[] = new int[13];
            int dp[] = new int[13];
            for (int i = 1; i <= 12; i++) {
                cal[i] = sc.nextInt();
            }
 
            for (int i = 1; i <= 12; i++) {
                int minval = Math.min(dp[i - 1] + cal[i] * oneday, dp[i - 1] + month);
                if (i >= 3) minval = Math.min(minval, dp[i - 3] + three);
                if (i >= 12) minval = Math.min(minval, dp[i - 12] + year);
                 
                dp[i] = minval;
            }
            System.out.printf("#%d %d",tc,dp[12]);
            System.out.println();
 
        }
 
    }
}