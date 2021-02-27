package com.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기_1759 {
	static int M, N;
	static char[] data;
	static boolean[] select;

	public static void main(String[] args) throws IOException {
		BufferedReader bre = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bre.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		data = new char[N];
		select = new boolean[N];
		st = new StringTokenizer(bre.readLine() ," ");
		for (int i = 0; i < N; i++) {
			data[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(data);
		comb(0, 0);
		
	}

	static void comb(int cnt, int target) {
		if (cnt == M) {
			char[] Printarr = new char[M];
			int Printarrcnt = 0;
			int Mcount = 0, Zcount = 0;
			for (int i = 0; i < N; i++) {
				if (select[i]) {
					Printarr[Printarrcnt] = data[i];
					Printarrcnt++;
				}
			}
			for (int i = 0; i < M; i++) {
				if (Printarr[i] == 'a' || Printarr[i] == 'e' || Printarr[i] == 'i' || Printarr[i] == 'o'
						|| Printarr[i] == 'u') {
					Mcount++;
				} else
					Zcount++;
			}

			if (Mcount >= 1 && Zcount >= 2) {
				for (int i = 0; i < M; i++) {
					System.out.print(Printarr[i]);
				}
				System.out.println();
			}

			return;
		}
		if (target == N) {
			return;
		}
		select[target] = true;
		comb(cnt + 1, target + 1);
		select[target] = false;
		comb(cnt, target + 1);
	}
}
