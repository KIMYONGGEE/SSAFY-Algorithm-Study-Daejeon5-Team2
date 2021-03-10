package 스터디3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA1227_미로2 {

	static int[][] data;
	static boolean[][] select;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int tc = 1; tc <= 10; tc++) {
			int T = Integer.parseInt(br.readLine());
			data = new int[100][100];
			select = new boolean[100][100];

			ans =0 ;
			int starti = 0, startj = 0;
			

			for (int i = 0; i < 100; i++) {
				String st = br.readLine();
				for (int j = 0; j < 100; j++) {
					data[i][j] = (int) st.charAt(j) - '0';
					if (data[i][j] == 2) {
						starti = i;
						startj = j;
					}
				}
			}
			
			DFS(starti,startj);
			
			System.out.println("#" + T + " "+ ans);

		}
	}
	
	static void DFS(int I, int J) {
		
		if(data[I][J] == 3) ans=1;
		
		select[I][J] = true;
		
		for (int d = 0; d < 4; d++) {
			int ni = I + di[d];
			int nj = J + dj[d];
			
			if(ni<0 || nj <0 || ni >=100 || nj>=100) continue;
			if(data[ni][nj] == 1) continue;
			if(select[ni][nj]) continue;
			
			//System.out.println(ni+"/"+nj+","+data[ni][nj]);
			DFS(ni,nj);
			
		}
		
		select[I][J] = false;
		
	}

}
