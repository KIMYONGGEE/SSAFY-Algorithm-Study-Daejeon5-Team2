package com.ssafy.study;

import java.util.Arrays;
import java.util.Scanner;

public class 로또_test {
	
	static int K;
	static int[] S;
	static boolean[] selected;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			K = sc.nextInt();
			if(K==0) {
				break;
			}
			S = new int[K];
			selected=new boolean[K];
			
			for(int i=0;i<K;i++) {
				S[i]=sc.nextInt();
			}
			//sort필요.
			Arrays.sort(S);
			
			rotto(0,0);
			
			System.out.println();
			
		}
	}
	
	static void rotto(int idx,int cnt) {
		if(cnt==6) {
			for(int i=0;i<K;i++) {
				if(selected[i]) {
					System.out.print(S[i]+" ");
				}
			}
			System.out.println();
			return;
		}
		if(idx==K) {
			return;
		}
		
		selected[idx]=true;
		rotto(idx+1,cnt+1);
		selected[idx]=false;
		rotto(idx+1,cnt);
	}

}
