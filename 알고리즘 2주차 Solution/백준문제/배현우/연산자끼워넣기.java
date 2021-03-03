package day0301;
import java.util.Arrays;
import java.util.Scanner;

public class 연산자끼워넣기 {
	static int N;
	static int [] arr;
	static int [] ops;
	static char [] op;
	static boolean[] isSelected;
	static char[] numbers;
	static int max,min;
	static void per(int cnt) {
		if(cnt==N-1) {
			int sum=arr[0];
			
			for(int i=1;i<N;i++) {
				if(numbers[i-1]=='+') {
					sum+=arr[i];
				}
				else if(numbers[i-1]=='-') {
					sum-=arr[i];
				}
				else if(numbers[i-1]=='*') {
					sum*=arr[i];
				}
				else if(numbers[i-1]=='/') {
					sum/=arr[i];
				}
			}
			min = Math.min(min, sum);
			max = Math.max(max, sum);
		}
		for(int i=0;i<N-1;i++) {
			if(isSelected[i])continue;
			numbers[cnt]=op[i];
			isSelected[i]=true;
			per(cnt+1);
			isSelected[i]=false;
		}
	}
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		for(int i=0;i<N;i++) {
			arr[i]=sc.nextInt();
		}
		ops=new int[4];
		for(int i=0;i<4;i++) {
			ops[i]=sc.nextInt();
		}
		int k=0;
		op = new char[N-1];
		for(int i=0;i<4;i++) {
			for(int j=0;j<ops[i];j++) {
				if(i==0) {
					op[k]='+';
				}
				else if(i==1) {
					op[k]='-';
				}
				else if(i==2) {
					op[k]='*';
				}
				else {
					op[k]='/';
				}
				k++;
			}
		}
		numbers = new char[N-1];
		isSelected= new boolean[N-1];
		max =Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		per(0);
		
		System.out.println(max);
		System.out.println(min);
	}
}

