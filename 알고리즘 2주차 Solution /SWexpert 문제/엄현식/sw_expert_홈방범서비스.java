import java.util.Scanner;

public class sw_expert_홈방범서비스 {

	static int N, M;
	static int[][] maps;
	static int home;
	static int aven;

	public static void makesmall(int k, int x, int y) {

		int count = 0;
		int temp_x = x - (k - 1);
		int temp_y = y;
		int otherside = (k - 1) * 2;
		for (int i = 1; i <= k; i++) {
			temp_y = y - (i - 1);
			for (int j = 0; j <= 2 * (i - 1); j++) {
				if (i == k) {
					if (temp_x >= 0 && temp_x < N && temp_y >= 0 && temp_y < N && maps[temp_x][temp_y] == 1) {
						count++;
					}
				} else {
					if (temp_y >= 0 && temp_y < N && temp_x + otherside >= 0 && temp_x + otherside < N
							&& maps[temp_x + otherside][temp_y] == 1) {
						count++;

					}
					if (temp_x >= 0 && temp_x < N && temp_y >= 0 && temp_y < N && maps[temp_x][temp_y] == 1)
						count++;

				}

				temp_y += 1;
			}
			otherside -= 2;
			temp_x += 1;
		}
		if (home < count && (count * M) - ((k * k) + (k - 1) * (k - 1)) >= 0) {

			home = count;
		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for (int t = 1; t <= tc; t++) {
			home = 0;
			N = sc.nextInt();
			M = sc.nextInt();
			maps = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					maps[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					for (int k = 1; k <= (N+1); k++) {
						makesmall(k, i, j);
					}

				}
			}
			System.out.printf("#%d %d", t, home);
			System.out.println();
		}

	}
}
