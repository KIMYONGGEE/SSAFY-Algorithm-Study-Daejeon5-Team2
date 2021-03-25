package 스터디5주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11403_경로찾기 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] data = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st =new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//i에서 j로 갈수있는 경우 기존데이터에 1을 표시한다 . 
		//i-tempN->j로 갈 수 있는 경우도 체크한다. 3중 포문 
		for (int tempN = 0; tempN < data.length; tempN++) {
			for (int i = 0; i < data.length; i++) {
				for (int j = 0; j < data.length; j++) {
					//k를 거쳐서 갈수있는 지 검사
					if(data[i][tempN] == 1 && data[tempN][j] == 1)
						data[i][j] = 1;
				}
			}
		}
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data.length; j++) {
				System.out.print(data[i][j] + " ");		
			}
			System.out.println();
		}

	}
}
