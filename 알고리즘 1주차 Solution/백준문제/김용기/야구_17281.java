package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer; 

public class 야구_17281 {
	static int Inning;
	static int[][] data;
	static boolean[] select;
	static int[] PlayerIdx;
	static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Inning = Integer.parseInt(br.readLine());

		data = new int[Inning][9];
		select = new boolean[9];
		PlayerIdx = new int[9];
		select[3] = true;
		max = Integer.MIN_VALUE;
		for (int i = 0; i < Inning; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 9; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		perm(0);
		System.out.println(max);

	}

	static void perm(int idx) {
		if (idx == 3) {
			PlayerIdx[idx] = 0;
			perm(idx + 1);
			return;
		}
		if (idx == 9) {
			int tempmax = 0;
			tempmax = Score(PlayerIdx);
			max = Math.max(max, tempmax);
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (!select[i]) {
				if (i == 0)
					PlayerIdx[idx] = 3;
				else
					PlayerIdx[idx] = i;
				select[i] = true;
				perm(idx + 1);
				select[i] = false;
			}
		}

	}

	static int Score(int[] playeridx) {
		int tempscore = 0;
		int tempidx = 0;

		for (int k = 0; k < Inning; k++) {
			int[] map = new int[3];
			int out = 0;
			while (out != 3) {
				if (tempidx == 9)
					tempidx = 0;
				if (data[k][playeridx[tempidx]] == 1) {
					if (map[2] == 1) {
						map[2] = 0;
						tempscore++;
					}
					if (map[1] == 1) {
						map[1] = 0;
						map[2] = 1;
					}
					if (map[0] == 1) {
						map[0] = 0;
						map[1] = 1;
					}
					map[0] = 1;
					tempidx++;
				} else if (data[k][playeridx[tempidx]] == 2) {

					if (map[2] == 1) {
						map[2] = 0;
						tempscore++;
					}
					if (map[1] == 1) {
						map[1] = 0;
						tempscore++;
					}
					if (map[0] == 1) {
						map[0] = 0;
						map[2] = 1;
					}
					map[1] = 1;

					tempidx++;
				} else if (data[k][playeridx[tempidx]] == 3) {

					if (map[2] == 1) {
						map[2] = 0;
						tempscore++;
					}
					if (map[1] == 1) {
						map[1] = 0;
						tempscore++;
					}
					if (map[0] == 1) {
						map[0] = 0;
						tempscore++;
					}
					map[2] = 1;

					tempidx++;
				} else if (data[k][playeridx[tempidx]] == 4) {

					if (map[2] == 1) {
						map[2] = 0;
						tempscore++;
					}
					if (map[1] == 1) {
						map[1] = 0;
						tempscore++;
					}
					if (map[0] == 1) {
						map[0] = 0;
						tempscore++;
					}
					tempscore++;

					tempidx++;
				} else if (data[k][playeridx[tempidx]] == 0) {
					out++;
					tempidx++;

				}
			}

		}
		return tempscore;

	}
}
