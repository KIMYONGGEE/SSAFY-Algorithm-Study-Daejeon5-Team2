package 스터디4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_탈주범검거 {
	static int N,M;
	static int L;
	static int[][] data;
	static int[] di = {0,1,0,-1}; // right, down, left, up
	static int[] dj = {1,0,-1,0};
	static int[][] Blockdata = {{0,1,2,3},{1,3},{0,2},{0,3},{0,1},{1,2},{2,3}}; //블록별 방향 데이터
	static int[][] Directdata = {{0,2,5,6},{0,1,3,6},{0,2,3,4},{0,1,4,5}}; //오른쪽 아래 왼쪽 위 방향에 따른 가능한 블록인덱스
	static boolean[][] availLocation;
	static boolean[][] isSelect;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int starti = Integer.parseInt(st.nextToken());
			int startj = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			data = new int[N][M];
			availLocation = new boolean[N][M];
			isSelect = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					data[i][j] = Integer.parseInt(st.nextToken()) -1;
				}
			}
			
			
			DFS(starti, startj, data[starti][startj], L-1);
			
			
			int ans=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(availLocation[i][j]) ans++; 
				}
			}
			
			System.out.println("#" + tc + " "+ ans);
		}
	}
	
	static void DFS(int I, int J, int block, int l) {
		
		if(l<0) return;
		
		isSelect[I][J] = true;
		
		availLocation[I][J] = true; 
		
		
		for (int i = 0; i < Blockdata[block].length; i++) {
			int ni = I + di[Blockdata[block][i]];
			int nj = J + dj[Blockdata[block][i]];
			
			if(ni <0 || nj <0 || ni >= N || nj >= M) continue;
			if(isSelect[ni][nj]) continue;
			if(!isAvailBock(data[ni][nj], Blockdata[block][i])) continue;
			
			DFS(ni, nj, data[ni][nj], l-1);
		}
		
		isSelect[I][J] = false;
	}
	
	static boolean isAvailBock(int nextdata, int d) {
		for (int i = 0; i < 4; i++) {
			if(Directdata[d][i] == nextdata)
				return true;
		}
		return false;
	}
}

