import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1520 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int M, N, total = 0;
    static int[][] map;
    static int[][] cache;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);
        map = new int[M][N];
        cache = new int[M][N];
        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int x, int y) {
        if (x == M - 1 && y == N - 1) {
            return 1;
        }

        if (visited[x][y])
            return cache[x][y];

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];
            if (nX < 0 || nY < 0 || nX >= M || nY >= N || map[x][y] <= map[nX][nY]) continue;
            cache[x][y] += dfs(nX, nY);
        }

        return cache[x][y];
    }
}