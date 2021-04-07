import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution1937 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int[][] cache;
    static int N;
    static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        cache = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -1);
        }

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (cache[i][j] != -1) continue;
                cache[i][j] = dfs(i, j);
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, cache[i][j]);
            }
        }

        System.out.println(max);
    }

    private static int dfs(int x, int y) {
        if (cache[x][y] != -1) {
            return cache[x][y];
        }

        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];
            if (nX < 0 || nY < 0 || nX >= N || nY >= N || map[x][y] >= map[nX][nY]) continue;
            cache[x][y] = Math.max(cache[x][y], dfs(nX, nY));
            cnt++;
        }

        if (cnt == 0) {
            cache[x][y] = 1;
            return 1;
        }

        cache[x][y] += 1;
        return cache[x][y];
    }
}