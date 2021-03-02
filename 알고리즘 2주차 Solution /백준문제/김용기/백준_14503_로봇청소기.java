package day0228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14503_로봇청소기 {
	static int[][] map;
	static int[] di = { -1, 0, 1, 0 }; // 북 동 남 서
	static int[] dj = { 0, 1, 0, -1 };
	static int starti, startj, dir;
	static int ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		map = new int[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];

		st = new StringTokenizer(br.readLine(), " ");
		starti = Integer.parseInt(st.nextToken());
		startj = Integer.parseInt(st.nextToken());
		dir = Integer.parseInt(st.nextToken());

		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		DFS(starti, startj, dir);
		System.out.println(ans);
	}

	static void DFS(int I, int J, int D) {
		
		if(map[I][J] == 0) {
			map[I][J] = 5;
			ans++;
		}

		int dircnt = 4;
		int diridx = D;
		while (dircnt >= 0) {
			if(diridx == 0) diridx =3 ;
			else diridx--;
			
			int ni = I + di[diridx];
			int nj = J + dj[diridx];
			
			if(ni != 0 && nj != 0 && ni < map.length-1 && nj < map[0].length-1 ) {
				if(map[ni][nj] == 0) {
					DFS(ni,nj,diridx);
					return;
				}
			}
			dircnt--;
		}
		
		
		
		int backi = di[D]*(-1);
		int backj = dj[D]*(-1);
		
		if(map[I + backi][J + backj] == 1) return;
		else DFS(I + backi,J + backj,D);

	}

}
