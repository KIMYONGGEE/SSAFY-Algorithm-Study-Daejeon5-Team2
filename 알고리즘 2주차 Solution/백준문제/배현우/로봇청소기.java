package day0301;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기 {
	static int[][] map;
	static int N, M;
	static int r, c, d;
	static int[] di = { 0, -1, 0, 1 };
	static int[] dj = { -1, 0, 1, 0 };
	static int cnt;

	static void dfs(int r, int c, int d, int dCnt) {

		if (dCnt == 4) {
			int ci = r-di[d];
			int cj = c-dj[d];
			if ((ci < 1 || ci >= N - 1 || cj < 1 || cj >= M - 1 || map[ci][cj]==1))
				return;
			else {
				r -= di[d];
				c -= dj[d];
				if(d==0) {
					d=3;
				}
				else {
					d-=1;
				}
				dCnt = 0;
			}
		}

		map[r][c] = 2;
		int ni = r + di[d];
		int nj = c + dj[d];

		if (ni >= 1 && ni < N - 1 && nj >= 1 && nj < M - 1 && map[ni][nj]==0) {
			cnt++;
			if (d == 0)
				d = 4;
			dfs(ni, nj, d-1, 0);
		} else {
			if(dCnt==3) {
				dfs(r,c,d,dCnt +1);
			}
			else {
			if (d == 0)
				d = 4;
			dfs(r, c, d - 1, dCnt + 1);

		}	}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cnt = 1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(r, c, d, 0);

		System.out.println(cnt);

	}
}

