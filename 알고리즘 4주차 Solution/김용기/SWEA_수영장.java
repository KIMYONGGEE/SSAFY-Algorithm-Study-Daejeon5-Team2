package 스터디4주차;

import java.io.*;
import java.util.StringTokenizer;

public class SWEA_수영장 {
	static int[] MemPrice= new int[4];
	static int[] Month = new int[12];
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer  st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < 4; i++) {
				MemPrice[i] = Integer.parseInt(st.nextToken());
			}
			
			ans = MemPrice[3];
			st= new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 12; i++) {
				Month[i]= Integer.parseInt(st.nextToken());
			}
			
			DFS(0,0);
			
			System.out.println("#" + tc + " " + ans);
			
		}
	}
	
	static void DFS(int idx, int price) {
		
		if(idx>11) {
			ans = Math.min(ans, price);
			return;
		}
		
		if(Month[idx] == 0)
			DFS(idx+1 , price);
		else {
			DFS(idx+1, price+(MemPrice[0]*Month[idx]));
			DFS(idx+1, price + MemPrice[1]);
			DFS(idx+3, price + MemPrice[2]);
		}

	}
}
