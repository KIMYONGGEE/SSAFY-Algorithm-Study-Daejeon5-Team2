package day0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 방범 {
	static int N, M;
	static int[][] map;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			max = Integer.MIN_VALUE;
			ArrayList<Point> pt = new ArrayList<Point>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					pt.add(new Point(i, j));
				}
			}
			int k = 2;
			while (true) {
				int val = k*k+(k-1)*(k-1);
				if(val>pt.size()*M)
					break;

				for (int i = 0; i < pt.size(); i++) {
					max = Math.max(max, check(k, pt.get(i).x, pt.get(i).y));
				}
				k++;
			}
			System.out.println("#"+tc+" "+max);
		}

	}

	static int check(int k, int x, int y) {
		int cnt = 0;
		int s = 0;
		int check = 1;
	
		for (int i = (k - 1) * (-1); i <= k - 1; i++) {
			for (int j = s * (-1); j <= s; j++) {
				if (x + i >= 0 && x + i < N && y + j >= 0 && y + j < N && map[x + i][y + j] == 1) {
					cnt++;
				}
			}
			if (i == 0) {
				check *= -1;
			}
			s += check;
		}
		int ck = k * k + (k - 1) * (k - 1);
		if (ck <=(cnt * M))
			return cnt;
		else
			return 1;
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
