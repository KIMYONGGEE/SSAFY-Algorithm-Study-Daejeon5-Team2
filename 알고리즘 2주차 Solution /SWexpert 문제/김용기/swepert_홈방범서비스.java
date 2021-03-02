package day0301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swepert_홈방범서비스 {
	static int N, M;
	static boolean[][] map;
	static int max;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			map = new boolean[N][N];
			max = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					int inputTemp = Integer.parseInt(st.nextToken());
					if (inputTemp == 1)
						map[i][j] = true;
					else
						map[i][j] = false;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					Checking(i, j);
				}
			}
			
			
			
			System.out.println("#" + tc + " "+  max);

		}
	}

	static void Checking(int I, int J) {
		int i=0,j=0;
		for (int k = 1; k <= N + 1; k++) {
			int side = 0;
			int housecnt =0;
			for ( i = I - (k - 1); i <= I + (k - 1); i++) {
				for ( j = J-1; j >= J - side; j--) {
					if(j<0 || j>= N || i<0 || i>= N) continue;
					if(map[i][j]) housecnt++;
				}
				for ( j = J; j <= J + side; j++) {
					if(j<0 || j>= N || i<0 || i>= N) continue;
					if(map[i][j]) housecnt++;
				}
				
				if(i<I) side++;
				else side--;
			}	
			
			if(isAvail(housecnt, k)) {

				max= Math.max(max, housecnt);
			}
			
		}
	}

	static boolean isAvail(int housecnt, int k) {
		int benefit = (housecnt*M) - (k*k + (k-1)*(k-1));
		if(benefit >= 0) return true;
		
		return false;
	}

}
