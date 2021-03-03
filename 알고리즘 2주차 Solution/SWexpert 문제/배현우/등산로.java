package day0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 등산로 {
	static int[][] map;
	static boolean[][] check;
	static int N, K;
	static ArrayList<Point> list;
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };
	static int ans;

	static void dfs(int x, int y, int k, int cnt) {

		for (int i = 0; i < 4; i++) {
			int ni = x + di[i];
			int nj = y + dj[i];
			check[x][y] = true;
			if (ni >= 0 && ni < N && nj >= 0 && nj < N && map[x][y] > map[ni][nj] && !check[ni][nj]) {
				dfs(ni, nj, k, cnt + 1);
			} else {
				if (ni >= 0 && ni < N && nj >= 0 && nj < N && !check[ni][nj] && (map[x][y] > (map[ni][nj] - k)) ) {
					int val = map[ni][nj]-map[x][y];
					map[ni][nj] -= val+1;
					dfs(ni, nj, 0, cnt + 1);
					map[ni][nj] += val+1;
				}
			}
			check[x][y] = false;
		}
		ans = Math.max(ans, cnt);
		return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			int max = 0;
			list = new ArrayList<Point>();
			map = new int[N][N];
			ans = 0;
			check = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					max = Math.max(max, map[i][j]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						list.add(new Point(i, j));
					}
				}
			}

			for (int i = 0; i < list.size(); i++) {
				dfs(list.get(i).x, list.get(i).y, K, 1);
			}
			System.out.println("#"+tc+" "+ans);
		}

	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
