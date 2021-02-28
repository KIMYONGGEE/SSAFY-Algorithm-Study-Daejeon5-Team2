import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int min, D, W, K;
	static int[][] map;
	static int[] type;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int T = Integer.parseInt(br.readLine());
		int[] result = new int[T];
		for (int i = 0; i < result.length; i++) {
			result[i] = getResult();
		}

		for (int i = 0; i < result.length; i++) {
			System.out.printf("#%d %d\n", i + 1, result[i]);
		}
	}

	private static int getResult() throws IOException {
		String[] input = br.readLine().split(" ");
		D = Integer.parseInt(input[0]);
		W = Integer.parseInt(input[1]);
		K = Integer.parseInt(input[2]);
		min = Integer.MAX_VALUE;
		map = new int[D][W];
		type = new int[D];

		for (int i = 0; i < D; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}

		traversal(0, 0);
		return min;
	}

	static void traversal(int row, int cnt) {
		if (cnt >= min)
			return;
		if (row == D) {
			if (doTest()) {
				min = Math.min(min, cnt);
			}
			return;
		}

        // 해당 행에 약을 주입하지 않는 경우
		type[row] = 2;
		traversal(row + 1, cnt);
        // 해당 행에 A타입 약을 주입하는 경우
		type[row] = 0;
		traversal(row + 1, cnt + 1);
        // 해당 행에 B 타입 약을 주입하는 경우
		type[row] = 1;
		traversal(row + 1, cnt + 1);
	}

	private static boolean doTest() {
        // 조건을 만족하는지 검사 진행
		for (int i = 0; i < W; i++) {
			int cnt = 1;
			int previous = (type[0] == 2) ? map[0][i] : type[0];
			for (int j = 1; j < D; j++) {
                // 해당 행의 종류를 파악
				int current = (type[j] == 2) ? map[j][i] : type[j];

				if (previous == current)
					cnt++;
				else {
					cnt = 1;
					previous = current;
				}
				
				if (cnt == K) break;
			}
			
			if (cnt < K) return false;
		}
		return true;
	}
}