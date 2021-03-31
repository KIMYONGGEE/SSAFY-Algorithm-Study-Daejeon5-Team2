package Algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1520 {
    static int[][] map;
    static boolean[][] vis;
    static int[][] dp;
    static int N;
    static int M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        vis = new boolean[N][M];
        dp = new int[N][M];

        for(int i = 0; i <N; i++){
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for(int j = 0;j < M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0, 0));
    }

    private static int dfs(int x, int y) {
        if(x == N-1 && y == M-1){
            return 1;
        }
        if(!vis[x][y]){
            // 해당 위치로 온적이 없음
            vis[x][y] = true;
            dp[x][y] = 0;
            for(int idx = 0; idx< 4; idx++){
                int nx = x + dx[idx];
                int ny = y + dy[idx];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(map[x][y] <= map[nx][ny]) continue;
                dp[x][y] += dfs(nx, ny);
            }
        }
        //온적 있으면 그 자리의 경우의 수 반환
        return dp[x][y];
    }
}
