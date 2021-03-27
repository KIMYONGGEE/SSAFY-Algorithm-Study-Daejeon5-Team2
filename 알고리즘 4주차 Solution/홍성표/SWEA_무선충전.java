import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int M, A;
    static int[] a, b;
    static BC[] bc;
    static int[] dx = {0, 0, 1, 0, -1}, dy = {0, -1, 0, 1, 0};

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
        M = Integer.parseInt(input[0]);
        A = Integer.parseInt(input[1]);
        a = new int[M];
        b = new int[M];
        bc = new BC[A];
        String[] aline = br.readLine().split(" ");
        String[] bline = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            a[i] = Integer.parseInt(aline[i]);
            b[i] = Integer.parseInt(bline[i]);
        }

        for (int i = 0; i < A; i++) {
            String[] bcLine = br.readLine().split(" ");
            bc[i] = new BC(bcLine);
        }
        return move();
    }

    private static int move() {
        int total = 0;
        Pair curA = new Pair(1, 1);
        Pair curB = new Pair(10, 10);
        for (int i = 0; i < M; i++) {
            total += getCharge(curA, curB);
            curA.x += dx[a[i]];
            curA.y += dy[a[i]];
            curB.x += dx[b[i]];
            curB.y += dy[b[i]];
        }
        return total + getCharge(curA, curB);
    }

    private static int getCharge(Pair curA, Pair curB) {
        int[][] visited = new int[A][2];
        int visitCnt = 0;
        for (int j = 0; j < A; j++) {
            if (Math.abs(bc[j].x - curA.x) + Math.abs(bc[j].y - curA.y) <= bc[j].C)
                visited[j][0] = 1;

            if (Math.abs(bc[j].x - curB.x) + Math.abs(bc[j].y - curB.y) <= bc[j].C)
                visited[j][1] = 1;

            if (visited[j][0] + visited[j][1] > 0)
                visitCnt++;
        }

        int ret = 0;
        if (visitCnt == 1) {
            for (int i = 0; i < A; i++) {
                if (visited[i][0] + visited[i][1] == 0) continue;
                ret = (bc[i].P * visited[i][0] + bc[i].P * visited[i][1]) / (visited[i][0] + visited[i][1]);
            }
        } else {
            for (int i = 0; i < A; i++) {
                for (int j = 0; j < A; j++) {
                    if (i == j || visited[i][0] + visited[j][1] == 0) continue;
                    ret = Math.max(ret, (bc[i].P * visited[i][0] + bc[j].P * visited[j][1]));
                }
            }
        }
        return ret;
    }

    static class BC {
        int x;
        int y;
        int C;
        int P;

        public BC(String[] line) {
            this.x = Integer.parseInt(line[0]);
            this.y = Integer.parseInt(line[1]);
            C = Integer.parseInt(line[2]);
            P = Integer.parseInt(line[3]);
        }
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}