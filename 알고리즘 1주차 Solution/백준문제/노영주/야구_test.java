package com.ssafy.study;

import java.util.Arrays;
import java.util.Scanner;

public class 야구_test {
	static int N;
	static int[][] score;
	static int outCount = 3;
	static int[] order;
	static int ans;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		score = new int[N][9];

		for (int i = 0; i < N; i++) { // i는 이닝접근
			for (int j = 0; j < 9; j++) { // j는 선수스코어
				score[i][j] = sc.nextInt();
			}
		}
		order = new int[9];

		ans = 0;

		for (int i = 0; i < order.length; i++) {
			order[i] = i + 1;
		}

		do {
			if (order[3] == 1) {
				int sum = 0;
				int idx = 0;
				for (int i = 0; i < N; i++) {
					int outSum = 0;
					int oneru = 0;
					int tworu = 0;
					int threeru = 0;
					while (outSum < outCount) {
						if(idx==9) idx=0;
						switch (score[i][order[idx] - 1]) {
						case 0:
							outSum++;
							idx++;
							break;
						case 1:
							if (threeru == 1) {
								sum++;
								threeru = 0;
							}
							if (tworu == 1) {
								threeru = 1;
								tworu = 0;
							}
							if (oneru == 1) {
								tworu = 1;
								oneru = 0;
							}
							oneru = 1;
							idx++;
							break;
						case 2:
							if (threeru == 1) {
								sum++;
								threeru = 0;
							}
							if (tworu == 1) {
								sum++;
								tworu = 0;
							}
							if (oneru == 1) {
								threeru = 1;
								oneru = 0;
							}
							tworu = 1;
							idx++;
							break;
						case 3:
							sum += oneru + tworu + threeru;
							oneru = 0;
							tworu = 0;
							threeru = 1;
							idx++;
							break;
						case 4:
							sum += oneru + tworu + threeru + 1;
							oneru = 0;
							tworu = 0;
							threeru = 0;
							idx++;
							break;
						}
					}
				}
				ans = Math.max(sum, ans);
			}
		} while (np());

		System.out.println(ans);
	}

	static boolean np() {
		int i = 9 - 1;

		while (i > 0 && order[i - 1] >= order[i]) {
			--i;
		}

		if (i == 0)
			return false;

		int j = 9 - 1;
		while (order[i - 1] >= order[j]) {
			--j;
		}

		swap(i - 1, j);

		int k = 9 - 1;

		while (i < k) {
			swap(i++, k--);
		}

		return true;
	}

	private static void swap(int i, int j) {
		int tmp = order[i];
		order[i] = order[j];
		order[j] = tmp;
	}
}
