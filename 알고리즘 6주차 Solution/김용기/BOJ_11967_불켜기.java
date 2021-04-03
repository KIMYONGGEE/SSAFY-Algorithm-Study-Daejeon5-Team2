package 스터디6주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ_11967_불켜기
 */
public class BOJ_11967_불켜기 {
    static int N,M,ans;

    static ArrayList<Pair>[][] Switchdata;

    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,1,-1};
    

    static class Pair{
        int i,j;
        Pair(int i,int j){
            this.i = i;
            this.j = j;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Switchdata = new ArrayList[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Switchdata[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            
            Switchdata[x][y].add(new Pair(a, b));
        }        
        BFS();
        
        System.out.println(ans);
        
    }
    static void BFS(){
        boolean[][] visit = new boolean[N][N];// 방문체크를 통해서 시간터지는걸 방지
        boolean[][] light= new boolean[N][N];//불이 켜져있는 곳 : 갈수있는곳
        boolean[][] checkSwitchon= new boolean[N][N];//다시 켜질걸 방지한 배열 
        
        light[0][0] =true;

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0));
        visit = new boolean[N+1][N+1];

        while(!q.isEmpty()){
            Pair now = q.poll();
            int I = now.i;
            int J = now.j;
            boolean switchOn = false;
            
            if(Switchdata[I][J].size() != 0 && !checkSwitchon[I][J]){
                checkSwitchon[I][J] = true;
                for(int s=0; s<Switchdata[I][J].size() ; s++){
                    light[Switchdata[I][J].get(s).i][Switchdata[I][J].get(s).j] = true;
                    ans++;
                }
                switchOn = true;
            }

            visit[I][J] = true;
            for (int d = 0; d < 4; d++) {
                int ni = I +di[d];
                int nj = J +dj[d];

                if(ni >=0 && nj >=0 && ni < N && nj < N && !visit[ni][nj] && light[ni][nj]){
                    q.add(new Pair(ni, nj));
                }       
            }

            //불을 켰다면 다시 돌아갈수도 있기 때문에 visit을 초기화 해준다
            if(switchOn){
                for (int k = 0; k < N ; k++) {
                    Arrays.fill(visit[k], false);
                }
                q.add(new Pair(I, J));
            }

        }

    }
    
}