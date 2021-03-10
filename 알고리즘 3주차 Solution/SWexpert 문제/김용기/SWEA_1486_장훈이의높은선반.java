package 스터디3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1486_장훈이의높은선반 {
	static int N, B;
	static int[] data;
	static boolean[] select;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			data = new int[N];
			select = new boolean[N];
			
			ans = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				data[i] = Integer.parseInt(st.nextToken());
			}

			PowerSet(0);
			
			System.out.println("#"+tc+" "+ans);
		}

	}

	static void PowerSet(int target) {

		if (target == N) {
			int temp = 0;
			for (int i = 0; i < N; i++) {
				if (select[i])
					temp += data[i];
			}
			if(temp >= B) {
				temp = temp-B;
				ans = Math.min(temp, ans);
			}
			
			return;
		}

		select[target] = true;
		PowerSet(target + 1);
		select[target] = false;
		PowerSet(target + 1);

	}
}
