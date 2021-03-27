import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, R, C, L, total;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, +1}, dy = {1, -1, 0, 0};
 
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
        M = Integer.parseInt(input[1]);
        R = Integer.parseInt(input[2]);
        C = Integer.parseInt(input[3]);
        L = Integer.parseInt(input[4]);
 
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
 
        total = 1;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(R, C));
        int time = 0;
        visited = new boolean[N][M];
        while (++time < L) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Pair cur = q.poll();
                visited[R][C] = true;
                for (int i = 0; i < 4; i++) {
                    int nX = cur.x + dx[i];
                    int nY = cur.y + dy[i];
                    if (nX < 0 || nY < 0 || nX >= N || nY >= M || !canMove(map[cur.x][cur.y], map[nX][nY], i) || visited[nX][nY]) continue;
                    visited[nX][nY] = true;
                    q.add(new Pair(nX, nY));
                }
            }
            total += q.size();
        }
 
        return total;
    }
 
    private static boolean canMove(int cur, int next, int dIdx) {
        if (dIdx == 0) {
            return (cur == 1 || cur == 3 || cur == 4 || cur == 5) && (next == 1 || next == 3 || next == 6 || next == 7);
        } else if (dIdx == 1) {
            return (cur == 1 || cur == 3 || cur == 6 || cur == 7) && (next == 1 || next == 3 || next == 4 || next == 5);
        } else if (dIdx == 2) {
            return (cur == 1 || cur == 2 || cur == 4 || cur == 7) && (next == 1 || next == 2 || next == 5 || next == 6);
        } else {
            return (cur == 1 || cur == 2 || cur == 5 || cur == 6) && (next == 1 || next == 2 || next == 4 || next == 7);
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