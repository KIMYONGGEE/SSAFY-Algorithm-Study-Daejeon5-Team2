package firstProject;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class sw_expert_장훈이의높은선반 {
	static int N,B;
	static Integer[] emp;
	static int ans;
	static boolean[] check;
	public static void combi(int index,int count,int K) {
		int sum=0;
		if(count==K) {
			for(int i=0;i<N;i++) {
				if(check[i]) {
					sum+=emp[i];
					if(sum-B>ans)return;
				}
			}
			if(sum-B>0&&sum-B<ans)ans=sum-B;
			
			return;
		}
		if(index>=N) {
			return;
		}
		
		check[index]=true;
		combi(index+1,count+1,K);
		check[index]=false;
		combi(index+1,count,K);
		
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for(int t=1;t<=tc;t++) {
			N=sc.nextInt();
			B=sc.nextInt();
			emp=new Integer[N];
			ans=Integer.MAX_VALUE;
			for(int i=0;i<N;i++) {
				emp[i]=sc.nextInt();}
			
			for(int i=1;i<=N;i++) {
				check=new boolean[N];
				combi(0,0,i);
			}
			System.out.printf("#%d %d",t,ans);
			System.out.println();
			
			
			
		}
	}
}
