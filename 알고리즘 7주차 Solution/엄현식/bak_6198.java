package study6weeks;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class bak_6198 {

	public static void main(String[] args) {
		
		
		Scanner sc =new Scanner(System.in);
		
		int N=sc.nextInt();
		int [] arr=new int [N];

		for(int i=0;i<N;i++) {
			arr[i]=sc.nextInt();
		}
		
		
		long [] dp= new long[N];
		Stack<Integer> stack= new Stack<>();
		
		for(int i=N-1;i>=0;i--) {        
			while(!stack.isEmpty()) {
				if(arr[i]>arr[stack.peek()]) {
					
					int tempidx=stack.pop();
					dp[i]+=dp[tempidx]+1;
				}
				else if(i==stack.peek()) {
					break;
				}
				else {
					break;
				}

			}
			stack.add(i);
			
		}
		long sum=0;
		for(int i=0;i<N;i++) {
			sum+=dp[i];
		}
		
		
		System.out.println(sum);
		
	}
}
