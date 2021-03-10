package com.ssafy.study;

import java.util.Scanner;

public class 장훈이의높은선반_test {
	static int N, B;
	static int[] height;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int t = 1; t <= TC; t++) {

			N = sc.nextInt();
			B = sc.nextInt();

			height = new int[N];
			visited = new boolean[N];

			for (int i = 0; i < N; i++) {
				height[i] = sc.nextInt();
			}
			ans = Integer.MAX_VALUE;

			go(0); 

			System.out.println("#" + t + " " + ans);
		}
	}

	static void go(int idx) {
		if (idx == N) { //점원의 수가 넘어갔을 때
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					sum += height[i]; //현재 선택된 것들을 더한다.
				}
			}

			if (sum < B) { //B미만일 경우는 생각하지 않아도 됨.
				return;
			}

			if (Math.abs(sum - B) >= ans) { //만약 B이상이지만 현재의 ans값보다 크거나 같으면 생각하지 않아도 됨.
				return;
			}

			ans = Math.abs(sum - B); //ans 갱신
			return;
		}

		visited[idx] = true;
		go(idx + 1);
		visited[idx] = false;
		go(idx + 1);
	}
}
