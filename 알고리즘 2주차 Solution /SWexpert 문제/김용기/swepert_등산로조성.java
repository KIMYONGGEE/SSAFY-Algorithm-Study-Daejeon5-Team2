package day0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swepert_등산로조성 {
	static int N, K;
	static int[][] map;
	static boolean[][] isSelect;
	static int[] di = { 1, -1, 0, 0 };// 아래/ 위 / 왼/ 오
	static int[] dj = { 0, 0, -1, 1 };
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			ans = Integer.MIN_VALUE;
			int startpoint = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					startpoint = Math.max(startpoint, map[i][j]);
				}
			}

			isSelect = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == startpoint) {
						DFS(i, j, 0, false);
					}
				}
			}

			System.out.println("#" + tc + " " + ans);

		}

	}

	static void DFS(int I, int J, int dir, boolean isDig) {
		
		if(dir == 0) dir =1;
		
		isSelect[I][J]  = true;
		
		
		for (int d = 0; d < 4; d++) {
			int ni = I, nj = J;
			
			ni += di[d];
			nj += dj[d];
			
			if (ni < 0 || ni >= N || nj < 0 || nj >= N)
				continue;

			if (isSelect[ni][nj])
				continue;

			if (map[ni][nj] < map[I][J]) {
				DFS(ni, nj, dir + 1, isDig);
			}

			else if (map[ni][nj] >= map[I][J]) {
				if (!isDig) {
					for (int k = 1; k <= K; k++) {
						if (map[ni][nj]-k < map[I][J]) {
							int savemap = map[ni][nj];
							map[ni][nj] = map[ni][nj] - k;
							DFS(ni, nj, dir + 1, true);
							map[ni][nj] = savemap;
						}
					}

				}
			}
		}

		ans = Math.max(ans, dir);
		
		isSelect[I][J]  = false;
		
		
		

	}

}
