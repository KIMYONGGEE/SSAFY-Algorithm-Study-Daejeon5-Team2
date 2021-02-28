import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static char[] operator = { '+', '-', '*', '/' };
	static int[] operatorCnt = new int[4];
	static int[] expr;
	static int max, min;

	public static void main(String[] args) {
		int N = sc.nextInt();
		expr = new int[N];
		for (int i = 0; i < N; i++) {
			expr[i] = sc.nextInt();
		}

		for (int i = 0; i < 4; i++) {
			operatorCnt[i] = sc.nextInt();
		}

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		compute(0, expr[0]);
		
		System.out.println(max);
		System.out.println(min);
	}

	static void compute(int idx, int res) {
        // 모든 수를 사용했을 경우, 최대/최소값 갱신
		if (idx == expr.length - 1) {
			if (max < res)
				max = res;
			if (min > res)
				min = res;
			return;
		}

		for (int j = 0; j < 4; j++) {
            // operator 개수가 남아있는 경우
			if (operatorCnt[j] != 0) {
				operatorCnt[j] -= 1;
                
				compute(idx + 1, applyOperator(res, expr[idx + 1], operator[j]));
				operatorCnt[j] += 1;				
			}
		}
		return;
	}

	static int applyOperator(int x, int y, char oper) {
		int res;
		if (oper == '+') {
			res = x + y;
		} else if (oper == '-') {
			res = x - y;
		} else if (oper == '*') {
			res = x * y;
		} else {
			if (x < 0 && y > 0) {
				res = -(Math.abs(x)/y);
			} else {
				res = x / y;				
			}
		}
		return res;
	}
}