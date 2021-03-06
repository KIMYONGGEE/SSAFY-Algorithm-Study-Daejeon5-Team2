package com.ssafy.study;

import java.util.Arrays;
import java.util.Scanner;

public class 연산자끼워넣기_test {
	static int N;
	static int[] num, operator;
	static int min, max;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		N = sc.nextInt();
		num = new int[N];
		operator = new int[N - 1]; //연산자 개수를 저장하기 위한 배열. 
		
		for(int i=0;i<N;i++)
			num[i]=sc.nextInt();
		
		int plus = sc.nextInt();
		int minus=sc.nextInt();
		int mul= sc.nextInt();
		int div = sc.nextInt();
		
		for(int i=0;i<N-1;i++) {
			if(plus>0) {
			operator[i] = 0; 
			plus--; 
			continue;
			}
			if(minus>0) {
				operator[i]=1;
				minus--;
				continue;
			}
			if(mul>0) {
				operator[i]=2;
				mul--;
				continue;
			}
			if(div>0) {
				operator[i]=3;
				div--;
				continue;
			}
		}
		Arrays.sort(operator); //np 전에 정렬.
		
		
		do{
			int sum=num[0];
			int idx=0;
			
			for(int i=1;i<N;i++) {
				if(operator[idx]==0) {
					sum+=num[i];
					idx++;
					continue;
				}
				if(operator[idx]==1) {
					sum-=num[i];
					idx++;
					continue;
				}
				if(operator[idx]==2) {
					sum*=num[i];
					idx++;
					continue;
				}
				if(operator[idx]==3) {
					if(sum>=0) {
						sum/=num[i];
						idx++;
					}else {
						sum = Math.abs(sum);
						sum/=num[i];
						sum*=(-1);
						idx++;
					}
					continue;
				}
			}
			
			if(sum>max)
				max=sum;
			
			if(sum<min)
				min=sum;
		}
		while(np());
		
		System.out.println(max);
		System.out.println(min);
	}
	
	static boolean np() {
		int i = operator.length - 1;

		while (i > 0 && operator[i - 1] >= operator[i]) {
			--i;
		}

		if (i == 0)
			return false;

		int j = operator.length - 1;
		while (operator[i - 1] >= operator[j]) {
			--j;
		}

		swap(i - 1, j);

		int k = operator.length - 1;

		while (i < k) {
			swap(i++, k--);
		}

		return true;
	}

	private static void swap(int i, int j) {
		int tmp = operator[i];
		operator[i] = operator[j];
		operator[j] = tmp;
	}
}
