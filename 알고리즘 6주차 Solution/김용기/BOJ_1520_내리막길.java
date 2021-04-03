package 스터디6주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_1520_내리막길
 * dp 와 dfs 합치자 
 * 
 * 그 뭐지 dp동적 테이블을통해 현재까지의 연결된 지점을 저장한다
 * 그 이후 디피 테이블에 도착했던 데이터가 있으면 탐색을 안하고 테이블 데이터를 넘긴다. 
 */
public class BOJ_1520_내리막길 {
    static int N, M,ans;
    static int[][] data;
    static int[][] dp;
    static boolean[][] visit;

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        data = new int[N + 1][M + 1];
        visit = new boolean[N + 1][M + 1];
        dp = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 1; j <= M; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // for (int i = 0; i < N; i++) {
        //     Arrays.fill(dp[i], 0);
            
        // }

        DFS(1, 1);

        System.out.println(dp[1][1]);

    }

    static int DFS(int I, int J) {
        
        if(I == N && J == M){
            return 1;
        }
        visit[I][J] = true;

        if(dp[I][J] == 0){
            for (int d = 0; d < 4; d++) {
                int ni = I + di[d];
                int nj = J + dj[d];
                if(ni >=1 && nj >= 1 && ni <= N && nj <=M && data[I][J] > data[ni][nj] && !visit[ni][nj]){
                    dp[I][J] = dp[I][J] + DFS(ni,nj);
                }

            }
        }
        
        return dp[I][J];
    }

}