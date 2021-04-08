package study6weeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bak_1107 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int ans = 0;
		int now = 100;
		int min = Math.abs(100 - N);
		if (M == 0) {
			

			for (int i = 0; i <= 1000000; i++) {
				String k = Integer.toString(i);
				
				if (min > Math.abs(N - i) + k.length()) {

					min = Math.abs(N - i) + k.length();// 조작횟수
					// 그 값 기억하

				}
			}
			System.out.println(min);
			

		} else {
			String broken[] = new String[M];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int idx = 0;
			while (st.hasMoreTokens()) {
				broken[idx++] = st.nextToken();
			}
			
			for (int i = 0; i <= 1000000; i++) {
				String k = Integer.toString(i);
				boolean flag = false;
				for (int j = 0; j < M; j++) {
					if (k.contains(broken[j])) {
						flag = true;
						break;
					}
				}

				if (!flag) {

					if (min > Math.abs(N - i) + k.length()) {

						min = Math.abs(N - i) + k.length();// 조작횟수
						// 그 값 기억하

					}
				}

			}

			System.out.println(min);
		}

	}
}
