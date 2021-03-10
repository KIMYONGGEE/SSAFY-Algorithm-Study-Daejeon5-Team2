package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 미로2_test {
	static boolean[][] visited;
	static int[][] map;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	static int ans = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		for (int t = 1; t <= 10; t++) {

			int TC = Integer.parseInt(br.readLine());
			
			map = new int[100][100];
			visited = new boolean[100][100];

			int si = 0, sj = 0;

			for (int i = 0; i < 100; i++) {
				String s = br.readLine();
				for (int j = 0; j < 100; j++) {
					map[i][j] = s.charAt(j) - '0';
					if (map[i][j] == 2) {
						si = i; //출발점 기록
						sj = j;
					}
				}
			}
			ans = 0; //3에 도달했다면 1로 변경되고 나온다.
			move(si, sj); //출발점 부터 시작한다.
 

			System.out.println("#" + TC + " " + ans);
		}
	}

	static void move(int nx, int ny) {
		if(check(nx,ny)==4) { //모두 벽이거나 방문했던 곳이라면 갈 곳이 없으므로 return.
			return ;
		}
		

		visited[nx][ny] = true; //현재좌표를 방문처리

		for (int d = 0; d < 4; d++) { //다음번에 갈 좌표를 정한다.
			int mx = nx + di[d];
			int my = ny + dj[d];

			if (map[mx][my] == 3) { //다음번에 갈 곳이 도착점이라면
				ans = 1; 
				return; //종료한다.
			} else if (map[mx][my] == 0 && !visited[mx][my]) { //방문하지 않은 길이라면 
				move(mx, my); //해당좌표로 이동한다.
			}
		}
	}
	static int check(int x,int y) { //벽이거나 방문한 곳을 더한다. 4가 리턴되면 종료조건.
		int sum=0;
		for(int d=0;d<4;d++) {
			int mx = x+di[d];
			int my = y+dj[d];
			if(visited[mx][my] || map[mx][my]==1) {
				sum+=1;
			}
		}
		return sum;
	}
}
