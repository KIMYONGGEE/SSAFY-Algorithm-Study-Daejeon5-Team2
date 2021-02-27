package com.SWEXPERT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 보호필름 {
	static int D, W, K;
	static int[][] data;
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			data = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					data[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			min = Integer.MAX_VALUE;
			dfs(0, 0);

			System.out.println("#" + tc + " " + min);
		}
	}

	static void dfs(int inject, int target) {

		if(inject >= min) return;
		
		if (target == D) {
			int tempmin = 0;
			if (availcheck()) {
				tempmin = inject;
				min = Math.min(min, tempmin);
			}
			return;
		}

		int[] temp = new int[W];
		for (int i = 0; i < W; i++)
			temp[i] = data[target][i];

		//////////////////////////////
		dfs(inject, target + 1);// 주사 안함

		//////////////////////////////
		for (int i = 0; i < W; i++) {
			data[target][i] = 0;
		}
		dfs(inject + 1, target + 1);// A주사

		//////////////////////////////
		for (int i = 0; i < W; i++) {
			data[target][i] = 1;
		}
		dfs(inject + 1, target + 1);// B주사

		for (int i = 0; i < W; i++)
			data[target][i] = temp[i];
	}

	static boolean availcheck() {
	
		int availtemp = 0;
		for (int j = 0; j < W; j++) {
			int Acount = 0, Bcount = 0;
			for (int i = 0; i < D; i++) {
				if (i == 0) {
					if (data[i][j] == 0)
						Acount++;
					else
						Bcount++;
				} else {
					if (data[i][j] == 0 && data[i - 1][j] == 0)
						Acount++;
					else if (data[i][j] == 1 && data[i - 1][j] == 1)
						Bcount++;
					else {
						Acount = 0;
						Bcount = 0;
						if(data[i-1][j] == 1) Acount++;
						else Bcount++;
					}
				}
				if (Acount == K || Bcount == K) {
					availtemp++;
					break;
				}
				
			}

		}

		if (availtemp == W)
			return true;
		else
			return false;
	}

}
