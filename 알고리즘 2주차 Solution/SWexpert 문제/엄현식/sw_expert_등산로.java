import java.util.Scanner;

public class sw_expert_µî»ê·Î {
	static int[][] maps;
	static int maxheight;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int max;

	static void recur(int x, int y, int k, int count, int value) {
		if (count > max) {
			max = count;
		}
		System.out.println(x+" "+y+" "+count);

		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length && maps[nx][ny] < value) {
				recur(nx, ny, k, count + 1, maps[nx][ny]);

			}
			else if (nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length && k > 0 && maps[nx][ny] - k < value) {
				System.out.println(nx+" "+ny +"ÈùÆ®");
				recur(nx, ny, 0, count + 1, maps[nx][ny] - 1);
			}
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for (int t = 1; t <= tc; t++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			max=0;
			maxheight=0;
			maps = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					maps[i][j] = sc.nextInt();
					if (maps[i][j] > maxheight) {
						maxheight = maps[i][j];
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (maps[i][j] == maxheight) {
						recur(i, j, K, 1, maps[i][j]);
					}
				}
			}
			System.out.printf("#%d %d",t,max);
			System.out.println();
		}
	}
}
