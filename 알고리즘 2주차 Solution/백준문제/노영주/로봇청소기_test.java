package com.ssafy.study;

import java.util.Arrays;
import java.util.Scanner;

public class 로봇청소기_test {

	static int[] di = { -1, 0, 1, 0 }; // 북,동,남,서
	static int[] dj = { 0, 1, 0, -1 };
	static int[][] map;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		map = new int[N][M];
		int ans = 0;

		int ni = sc.nextInt(); 
		int nj = sc.nextInt(); 
		int nd = sc.nextInt(); 

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		} // end input


		while (true) {

			int ok = 4; //네 방향 모두 벽이거나 청소한 칸으로 처음에 줌.
			
			if (map[ni][nj] == 0) { // 현재 칸이 청소가 안된 칸이라면
				map[ni][nj] = 3; //칸을 청소한다. 
				ans++; 
			}
			
			int count=0; //사방탐색을 위한 카운트
			while(count<4) { //사방탐색이 끝나면 나옴
			if(nd==0) { //북쪽
				nd=3; //왼쪽(서쪽)으로 회전
				count++; //회전했으니 count 1증가
				if(map[ni+di[nd]][nj+dj[nd]]==0) { //해당방향 이동칸이 청소가능하다면
					ok = check(ni,nj); //ok변수는 4보다 작은 값으로 변경된다.
					//현재의 위치에서 탐색이 가능했기 때문에 이동 전의 값을 넣어줘야함. 
					ni  +=di[nd]; //이동
					nj +=dj[nd]; //이동
					break; //탐색 성공했으니 break
				}
			}else if(nd==1) {
				nd=0;
				count++;
				if(map[ni+di[nd]][nj+dj[nd]]==0) {
					ok = check(ni,nj);
					ni  +=di[nd];
					nj +=dj[nd];
					break;
				}
			}else if(nd==2) {
				nd=1;
				count++;
				if(map[ni+di[nd]][nj+dj[nd]]==0) {
					ok = check(ni,nj);
					ni  +=di[nd];
					nj +=dj[nd];
					break;
				}
			}else if(nd==3) {
				nd=2;
				count++;
				if(map[ni+di[nd]][nj+dj[nd]]==0) {
					ok = check(ni,nj);
					ni  +=di[nd];
					nj +=dj[nd];
					break;
				}
			}}


			if (ok == 4) { // 청소 가능한 칸을 발견하지 못함.
				ni -=di[nd]; 
				nj -=dj[nd];
				if (map[ni][nj] == 1) { //후진도 불가능하다면.
					break;
				} 
			}
		}

		System.out.println(ans);
	}

	static int check(int r, int c) { // 사방을 탐색 해 청소 가능한 칸이 없다면 4.
		int sum = 0;
		for (int d = 0; d < 4; d++) {
			if (map[r + di[d]][c + dj[d]] == 3 || map[r + di[d]][c + dj[d]] == 1) {
				sum += 1;
			}
		}
		return sum;
	}
}
