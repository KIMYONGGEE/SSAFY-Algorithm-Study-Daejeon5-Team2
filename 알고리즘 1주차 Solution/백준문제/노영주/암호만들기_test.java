package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기_test {
	static int L, C;
	static boolean[] selected;
	static char[] password;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		password = new char[C];
		for (int i = 0; i < C; i++) {
			password[i] = st.nextToken().charAt(0);
		}
		selected = new boolean[C];

		Arrays.sort(password);

		search(0, 0);
	}

	static void search(int idx, int cnt) {
		if (cnt == L) {
			boolean flag = false;
			int count = 0;
			for (int i = 0; i < C; i++) {
				if (selected[i]) {
					if (aeiou(password[i])) {
						flag = true;
					} else {
						count++;
					}
				}
			}
			if (!flag)
				return;
			if (count > 1) {
				for (int i = 0; i < C; i++) {
					if (selected[i]) {
						System.out.print(password[i]);
					}
				}
				System.out.println();
			}
			return;
		}
		if (idx == C) {
			return;
		}

		selected[idx] = true;
		search(idx + 1, cnt + 1);
		selected[idx] = false;
		search(idx + 1, cnt);
	}

	static boolean aeiou(char c) {
		if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			return true;
		}
		return false;
	}
}
