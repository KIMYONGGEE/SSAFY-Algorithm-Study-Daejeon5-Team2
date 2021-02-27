package com.baekjoon;

import java.util.Scanner;

public class 로또_6603 {
	static int N;
	static int[] data;
	static boolean[] select;
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		while(true) {
			N = sc.nextInt();
			if(N==0) break;
			
			data = new int[N];
			select = new boolean[N];
			for(int i =0; i< N ; i++) {
				data[i] = sc.nextInt();
			}
			comb(0,0);
			System.out.println();
		}
		
	}
	static void comb(int cnt, int target) {
		if(cnt==6) {
			for(int i =0;i<N;i++) {
				if(select[i]) {
					if(i==N-1)
						System.out.print(data[i]);
					else
						System.out.print(data[i] +" ");
				}
			}
			System.out.println();
			return;
		}
		if(target==N) { 
			return;
		}
		select[target] = true;
		comb(cnt+1, target+1);
		select[target] = false;
		comb(cnt, target+1);
	}
}
