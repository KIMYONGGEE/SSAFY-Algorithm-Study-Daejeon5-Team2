package com.ssafy.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 길찾기_test {
	
	static int[] arr1,arr2;
	static int INIT = 100;
	static int ans;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1;t<=10;t++) {
			
			String s = br.readLine();
			StringTokenizer st = new StringTokenizer(s);
			
			int TC = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			
			arr1 = new int[100];
			arr2 = new int[100];
			
			
			Arrays.fill(arr1, INIT); //사용될 리 없는 값(100)으로 초기화한다.
			Arrays.fill(arr2, INIT);
			
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreElements()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				if(arr1[from]==100) { //두배열을 번갈아가며 값을 넣어준다.
					arr1[from]=to; 
				}else {
					arr2[from]=to;
				}
			}
			ans=0; //가는길을 발견한다면 1로 갱신됨.
			road(0,1); 
			
			
			System.out.println("#"+TC+" "+ans);
		}
	}
	static void road(int idx,int flag) { //flag는 두개를 번갈아 가기 위한 변수
		
		if(idx==100) { //범위를 넘어가면 return
			return ; 
		}
		
		
		if(flag==1 && arr1[idx]==100) { //arr1이며 해당배열의 idx가 현재 100이다.(연결된 도시가 없다.)
			return ;
		}
		
		if(flag==2 && arr2[idx]==100) { //arr2이며 해당배열의 ...
			return ;
		}
		
		if(arr1[idx]==99 || arr2[idx]==99) { //flag가 1이든 2든 가는길을 발견했다.
			ans=1;
			return ;
		}
		
			road(arr1[idx],1); //arr1로 따라가본다. 
			road(arr2[idx],2); //arr1에 마땅한 길이 없었으니 2로 따라가본다.
	}
}
