package com.ssafy.study;

import java.util.Scanner;

public class 방향전환_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		int TC = sc.nextInt();

		for (int t = 1; t <= TC; t++) {

			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();

			int ans =0;

			if ((Math.abs(x2 - x1) + Math.abs(y2 - y1)) % 2 == 0) { //(x1,y1)과 (x2,y2)사이의 거리가 짝수라면
				int temp = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
				ans = temp * 2; //x와 y의 차이중 큰 값의 2배와 같다.
			} else {
				int temp = Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
				ans = temp * 2 - 1; // 홀수 일 경우는 1을 빼줘야 한다.
			}
			System.out.println("#" + t + " " + ans);
		}
	}
}
