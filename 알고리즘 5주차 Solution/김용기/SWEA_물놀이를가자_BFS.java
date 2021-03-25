package 스터디5주차;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class SWEA_물놀이를가자_BFS{
    static int[] di = { 1, -1, 0, 0 };
    static int[] dj = { 0, 0, -1, 1 };

    static int N, M;
 
    static class locPair{
        int i,j,l;
        locPair(int i, int j, int l){
            this.i = i;
            this.j = j;
            this.l = l;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine());
        int answer;
 
        for (int tc = 1; tc <= T; tc++) {
 
            answer = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            boolean[][] visit = new boolean[N][M];
 
            Queue<locPair> queue = new LinkedList<>();
 
            for (int n = 0; n < N; n++) {
                String str = br.readLine();
                for (int m = 0; m < M; m++) {
                    if (str.charAt(m) == 'W') {
                        visit[n][m] = true;
                        queue.add(new locPair(n, m, 0));
                    }
                }
            }
 
            while (!queue.isEmpty()) {
                locPair now = queue.poll();

                int x = now.i;
                int y = now.j;
                int l = now.l;

                answer += l;
 
                for (int i = 0; i < 4; i++) {
                    int nX = x + di[i];
                    int nY = y + dj[i];
                     
                    if(nX >=0 && nY >=0 &&  nX < N && nY < M && !visit[nX][nY]) {
                        visit[nX][nY] = true;
                        queue.add(new locPair(nX,nY,l+1));
                    }
                }

            }
 
            System.out.println("#" + tc + " " + answer);
        }
    }
}