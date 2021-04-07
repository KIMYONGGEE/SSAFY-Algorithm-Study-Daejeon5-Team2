import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution1107 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, minDiff;
    static boolean[] visited = new boolean[1000001];
    static boolean[] buttons = new boolean[10];
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        Arrays.fill(buttons, true);
        if ( M != 0) {
            String[] line = br.readLine().split(" ");
            for (int i = 0; i < M; i++) {
                int num = Integer.parseInt(line[i]);
                buttons[num] = false;
            }
        }


        minDiff = Math.abs(N - 100);
        for (int i = 0; i < 10; i++) {
            if (!buttons[i]) continue;
            moveChannel(i, 1);
        }

        System.out.println(minDiff);
    }
    private static void moveChannel(int num, int len) {
        if (minDiff > Math.abs(N - num) + len) {
            minDiff = Math.abs(N - num) + len;
        }

        if (len == 7) return;
        int next = num * 10;
        for (int i = 0; i < 10; i++) {
            if (!buttons[i] || Math.abs(N - num) + len < Math.abs(N - (next + i)) + len + 1) continue;
            moveChannel(next + i, len + 1);
        }
    }
}