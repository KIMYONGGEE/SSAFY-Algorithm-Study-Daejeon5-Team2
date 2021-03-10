package 스터디3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class SWEA_8382_방향전환 {
	static int si, sj, ei, ej;
	static boolean state;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			si = Integer.parseInt(st.nextToken());
			sj = Integer.parseInt(st.nextToken());
			ei = Integer.parseInt(st.nextToken());
			ej = Integer.parseInt(st.nextToken());

			ans = Integer.MAX_VALUE;

			state = true;
			search();
			state = false;
			search();

			System.out.println("#" + tc + " " + ans);
		}

	}

	static void search() { // state =false(세로) true(가로)
		int ni = si, nj = sj; 
		int distance=0;
		while(ni != ei || nj !=ej) {
			if(state) {// 가
				if(nj > ej) nj--;
				else nj++;
				distance++;
				state = false;
			}
			else {// 
				if(ni > ei) ni--;
				else ni++;
				distance++;
				state = true;
			}
		}
		
		ans = Math.min(distance,ans);
	}
}
