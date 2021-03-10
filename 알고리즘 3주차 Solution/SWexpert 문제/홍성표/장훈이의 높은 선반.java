import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, B, minHeight;
    static int[] height;
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
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        B = Integer.parseInt(input[1]);
        height = new int[N];
        String[] line = br.readLine().split(" ");
        for (int i = 0; i <N ; i++) {
            height[i] = Integer.parseInt(line[i]);
        }
 
        minHeight = Integer.MAX_VALUE;
        subset(0, 0);
        return minHeight - B;
    }
 
    private static void subset(int idx, int total) {
        if (total >= B) {
            if (minHeight > total) minHeight = total;
            return;
        }
        if (idx == N || minHeight <= total)
            return;
 
        subset(idx + 1, total);
        subset(idx + 1, total + height[idx]);
    }
}