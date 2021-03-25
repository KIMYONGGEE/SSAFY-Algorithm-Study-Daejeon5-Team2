package firstProject;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class sw_expert_탈주범  {
     
    static int maps[][];
    static boolean check[][];
    static int N;
    static int M;
    static int R,C,L;
    static int cnt;
    static int dx[] = {-1,0,1,0};
    static int dy[] = {0,1,0,-1};
    static class Pair{
        int x;
        int y;
        int step;
        public Pair(int x ,int y,int step) {
            this.x=x;
            this.y=y;
            this.step=step;
        }
         
    }
    public static boolean canmove(int nx,int ny ,int x ,int y,int angle) {
         
        if(angle==0) {//nx ny가 위에 있음
             
            if(maps[x][y]==1||maps[x][y]==2||maps[x][y]==4||maps[x][y]==7) {
                if(maps[nx][ny]==2||maps[nx][ny]==1||maps[nx][ny]==6||maps[nx][ny]==5)return true;
                else return false;
            }
     
            else return false;
             
             
        }
         
        if(angle==2) {//nx ny가 아래
             
            if(maps[x][y]==1||maps[x][y]==2||maps[x][y]==5||maps[x][y]==6) {
                if(maps[nx][ny]==2||maps[nx][ny]==1||maps[nx][ny]==4||maps[nx][ny]==7)return true;
                else return false;
            }
             
            else {
                return false;
            }
             
             
        }
         
        if(angle==1) {//nx ny가 오른쪽
             
            if(maps[x][y]==1||maps[x][y]==3||maps[x][y]==4||maps[x][y]==5) {
                if(maps[nx][ny]==1||maps[nx][ny]==3||maps[nx][ny]==6||maps[nx][ny]==7)return true;
                else return false;
            }
            else {
                return false;
            }
             
        }
        if(angle==3) {//nx ny가 왼쪽
            if(maps[x][y]==1||maps[x][y]==3||maps[x][y]==6||maps[x][y]==7) {
                if(maps[nx][ny]==1||maps[nx][ny]==3||maps[nx][ny]==4||maps[nx][ny]==5)return true;
                else return false;
            }
            else {
                return false;
            }
             
        }
        return false;
         
         
    }
     
    public static void bfs(int R,int C) {
         
        Queue<Pair>q = new LinkedList<>();
        q.add(new Pair(R,C,1));
         
        while(!q.isEmpty()) {
             
            Pair temp=q.poll();
            if(check[temp.x][temp.y])continue;
            check[temp.x][temp.y]=true;
            if(temp.step>L)break;
            cnt+=1; 
            for(int i=0;i<dx.length;i++) {
                int nx=temp.x+dx[i];
                int ny=temp.y+dy[i];
                if(nx<0||nx>=N||ny<0||ny>=M)continue;
                 
                if(!check[nx][ny]&&canmove(nx,ny,temp.x,temp.y,i)) {
                    q.add(new Pair(nx,ny,temp.step+1));
                }
                 
                 
            }
             
             
             
             
             
        }
         
    }
     
    public static void main(String[] args) {
         
        Scanner sc=new Scanner(System.in);
        int Tc=sc.nextInt();
        for(int tc=1;tc<=Tc;tc++) {
        N=sc.nextInt();
        M=sc.nextInt();
        R=sc.nextInt();
        C=sc.nextInt();
        L=sc.nextInt();
        maps=new int[N][M];
        check=new boolean[N][M];
        cnt=0;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                maps[i][j]=sc.nextInt();
            }
        }
        bfs(R,C);
         
        System.out.printf("#%d %d",tc,cnt);
        System.out.println();
        }
         
         
    }
}