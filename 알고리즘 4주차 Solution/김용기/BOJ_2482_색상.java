package 스터디4주차;
import java.util.Scanner;

public class BOJ_2482_색상 {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		//dp[고를수 있는 수][고를 갯수]
		
		int N = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] dp = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			dp[i][0] = 1;// 0개롤 고르는 경우는 1로 배제
			dp[i][1] = i;// i개중에 1하나 뽑을 경우의수 
		}
		
		
		for (int i = 1; i <= N; i++) {
//			if(i<3) continue; //원형이니깐 최소 양옆을 봐줘야 하므로 3보다 작을 경우 없음
			for (int j = 2; j <= (i+1)/2; j++) { // i개중에 i의 절반 이상을 고를순 없자나..
				//점화식으로 누적해준다 
				//i번째를 선택할 경우 인접한 색을 빼야하므로 i-2중에 j-1개,
				//i번째를 선택 안할경우 i-1중에 j개를 선택 할수 잇음
				dp[i][j] = (dp[i-2][j-1] + dp[i-1][j])%1000000003;
			}
		}
		
		System.out.println((dp[N-3][K-1] + dp[N-1][K])%1000000003);
	}

}

