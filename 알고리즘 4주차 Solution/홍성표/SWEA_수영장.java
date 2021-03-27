import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution {
 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int minCnt;
    static int[] amount = new int[4];
    static int[] month = new int[12];
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        int[] result = new int[T];
        for (int i = 0; i < T; i++) {
            result[i] = getResult();
        }
 
        for (int i = 0; i < T; i++) {
            System.out.printf("#%d %d\n", i + 1, result[i]);
        }
    }
 
    static int getResult() throws IOException {
        String[] line1 = br.readLine().split(" ");
        String[] line2 = br.readLine().split(" ");
 
        for (int i = 0; i < 4; i++)
            amount[i] = Integer.parseInt(line1[i]);
 
        for (int i = 0; i < 12; i++)
            month[i] = Integer.parseInt(line2[i]);
 
        minCnt = amount[3];
        getMinAmount(0, 0);
        return minCnt;
    }
 
    private static void getMinAmount(int idx, int total) {
 
        if (idx == 12) {
            minCnt = Math.min(minCnt, total);
            return;
        }
        getMinAmount(idx + 1 , total + (month[idx] * amount[0]));
        getMinAmount(idx + 1 , total + amount[1]);
        if (idx < 10)
            getMinAmount(idx + 3 , total + amount[2]);
    }
}