package Algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Forest {
    int x;
    int y;

    public Forest(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class p1937 {
    static int N;
    static int[][] map;
    static int[][] vis;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        vis = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Arrays.fill(vis[i], 1);
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int maxLife = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(i, j);
                maxLife = Math.max(vis[i][j], maxLife);
            }
        }
        System.out.println(maxLife);
    }

    private static int dfs(int x, int y) {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        if (vis[x][y] == 1) {
            for (int idx = 0; idx < 4; idx++) {
                int nx = x + dx[idx];
                int ny = y + dy[idx];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (map[nx][ny] <= map[x][y]) {
                    continue;
                }
                vis[x][y] = Math.max(dfs(nx, ny) + 1, vis[x][y]);
            }
        }
        return vis[x][y];
    }
}
