package 스터디7주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_1937_욕심쟁이판다
 */
public class BOJ_1937_욕심쟁이판다Solution {
    static int N, ans;
    static int[][] data;
    static int[][] dp;
    static int[] di={-1,1,0,0};
    static int[] dj={0,0,-1,1};
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N =Integer.parseInt(br.readLine());
        data = new int[N][N];
        dp = new int[N][N];
        ans = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int tempans = dfs(i,j);
                ans = Math.max(ans, tempans);
            }
        }

        // for (int i = 0; i < N; i++) {
        //     System.out.println(Arrays.toString(dp[i]));
        // }

        System.out.println(ans);
        
    }
    static int dfs(int I, int J){

        if(dp[I][J] != 0){
            return dp[I][J];
        }

        dp[I][J] = 1;
        int resultLen = 0;
        for (int d = 0; d < 4; d++) {
            int ni = I + di[d];
            int nj = J + dj[d];
            if(ni >=0 && nj>= 0 && ni < N && nj < N){
                if(data[ni][nj]>data[I][J]){
                    resultLen = Math.max(resultLen, dfs(ni,nj));
                }
            }
        }

        dp[I][J] += resultLen;
        return dp[I][J];

    }
}