package 스터디3주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1219_길찾기 {
	static int[] data1,data2;
	static boolean[] select;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 0; tc < 10; tc++) {
			StringTokenizer st =new StringTokenizer(br.readLine()," ");
			int T =Integer.parseInt(st.nextToken());
			int way =Integer.parseInt(st.nextToken());
			
			data1 = new int[100];
			data2 = new int[100];
			select = new boolean[100];
			int indextemp = 0;
			ans = 0;
			st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < way*2; i++) {
				if(i%2 ==0 ) {
					indextemp = Integer.parseInt(st.nextToken());
				}
				else {
					int tempdata = Integer.parseInt(st.nextToken());
					if(data1[indextemp] != 0) data2[indextemp] = tempdata;
					else data1[indextemp] = tempdata;
				}
			}
			
			DFS(0);
			System.out.println("#" + T + " " + ans);
			
		}
		
	}
	
	static void DFS(int idx) {
		
		if(data1[idx] == 99 || data2[idx] == 99) ans=1;
		
		select[idx] = true;
		
		if(!select[data1[idx]] && data1[idx]!=0)
			DFS(data1[idx]);
		
		if(!select[data2[idx]] && data2[idx]!=0)
			DFS(data2[idx]);
		
		select[idx] = false;
		
	}

}
