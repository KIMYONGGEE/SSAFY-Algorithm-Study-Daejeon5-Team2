import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] a, b;
    static int maxCnt;
 
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
        String[] line = br.readLine().split(" ");
        a = new int[2];
        a[0] = Integer.parseInt(line[0]);
        a[1] = Integer.parseInt(line[1]);
 
        b = new int[2];
        b[0] = Integer.parseInt(line[2]);
        b[1] = Integer.parseInt(line[3]);
 
        maxCnt = Integer.MAX_VALUE;
        move(a[0], a[1], 0, true);
        move(a[0], a[1], 0, false);
 
        return maxCnt;
    }
 
    static void move(int x, int y, int cnt, boolean isVertical) {
        if (x == b[0] && y == b[1]) {
            maxCnt = Math.min(cnt, maxCnt);
            return;
        }
 
        int dx = 0, dy = 0;
        if (isVertical) {
            if (x <= b[0]) dx = 1;
            else dx = -1;
        } else {
            if (y <= b[1]) dy = 1;
            else dy = -1;
        }
 
        move(x + dx, y + dy, cnt + 1, !isVertical);
    }
}