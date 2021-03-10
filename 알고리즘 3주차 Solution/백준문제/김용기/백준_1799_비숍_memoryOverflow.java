package 스터디3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 백준_1799_비숍_memoryOverflow{
	static int N;
	static boolean[][] map;
	static int[][] Bishop;
	static boolean[] Select;
	
	static int[] di = { 1, 1, -1, -1 };
	static int[] dj = { -1, 1, 1, -1 };

	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		map = new boolean[N][N];
		int Bishopcnt = 0;
		int temp=0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				temp = Integer.parseInt(st.nextToken());
				if (temp == 1) {
					map[i][j] = true;
					Bishopcnt++;
				}
				else map[i][j] = false;
			}
		}

		Bishop = new int[Bishopcnt][2];
		Select = new boolean[Bishopcnt];

		ans = Integer.MIN_VALUE;
		Bishopcnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j]) {
					Bishop[Bishopcnt][0] = i;
					Bishop[Bishopcnt][1] = j;
					Bishopcnt++;
				}
			}
		}
		PowerSet(0);
		System.out.println(ans);
	}

	static void PowerSet(int target) {
		
		if (target == Bishop.length) {
			map = new boolean[N][N];
			int cnt = 0;
			for (int i = 0; i < Bishop.length; i++) {
				if (Select[i]) {
					map[Bishop[i][0]][Bishop[i][1]] = true;
				}
			}

			for (int i = 0; i < Bishop.length; i++) {
				if (Select[i]) {
					if (isAvail(i))
						cnt++;
					else
						continue;
				}
			}
			ans = Math.max(cnt, ans);
			return;
		}

		Select[target] = true;
		PowerSet(target + 1);
		Select[target] = false;
		PowerSet(target + 1);

	}

	static boolean isAvail(int idx) {

		for (int i = 0; i < 4; i++) {
			int ni = Bishop[idx][0];
			int nj = Bishop[idx][1];
			while (true) {
				ni += di[i];
				nj += dj[i];
				if (ni < 0 || nj < 0 || ni >= N || nj >= N)
					break;
				if (map[ni][nj])
					return false;
			}
		}
		return true;
	}

}
