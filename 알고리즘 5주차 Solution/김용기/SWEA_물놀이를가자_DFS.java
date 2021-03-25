package 스터디5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_물놀이를가자
 */
public class SWEA_물놀이를가자_DFS {

    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static ArrayList<Land> landData;
    static int ans;

    static int[] di = { 1, -1, 0, 0 };
    static int[] dj = { 0, 0, -1, 1 };

    static class Land {
        int i, j;

        Land(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int result = 0;
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            landData = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                String temp = br.readLine();
                for (int j = 0; j < M; j++) {
                    if (temp.charAt(j) == 'W')
                        map[i][j] = 1;
                    else {
                        map[i][j] = 0;
                        landData.add(new Land(i, j));
                    }
                }
            }
            visit = new boolean[N][M];

            for (int l = 0; l < landData.size(); l++) {
                ans = Integer.MAX_VALUE;
                DFS(landData.get(l).i, landData.get(l).j, 0);
                result += ans;
            }

            System.out.println("#" + tc + " " + result);

        }
    }

    static void DFS(int I, int J, int cnt) {
        if(map[I][J]==1){
            ans = Math.min(ans , cnt);
            return;
        }

        visit[I][J] = true;
        for (int k = 0; k < 4; k++) {
            int ni = I + di[k];
            int nj = J + dj[k];
            if(ni >= 0 && nj >=0 && ni < N && nj < M && !visit[ni][nj]){
                DFS(ni,nj,cnt+1);
            }
        }
        visit[I][J] = false;

    }

}