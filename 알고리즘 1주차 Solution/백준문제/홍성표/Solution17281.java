import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution17281 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int innings, maxScore = Integer.MIN_VALUE, totalScore, nextPlayer;
	static int[][] player;
	static int[] selection = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		innings = Integer.parseInt(br.readLine());
		player = new int[innings][9];
		
		for (int i = 0; i < innings; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < line.length; j++) {
				player[i][j] = Integer.parseInt(line[j]);
			}
		}
		
        //0번째 선수를 제외하고 순열 생성
		do {
			totalScore = 0;
			nextPlayer = 0;
			playGame();
		} while (nextPermutation());

		System.out.println(maxScore);
	}

    // 점수 계산
	private static void playGame() {
        // 선수 배치
		int[] sortedPlayer = new int[9];
		sortedPlayer[3] = 0;
		for (int i = 0; i < selection.length; i++) {
			if (i < 3)
				sortedPlayer[i] = selection[i];
			else if (i >= 3)
				sortedPlayer[i + 1] = selection[i];
		}
		
        // 이닝 진행
		for (int i = 0; i < innings; i++) {
			int out = 3;
			int runner = 0;
            // 3 아웃까지 진행
			while (out > 0) {
                // 선수의 행동에 따라 진루할 베이스 결정
				int curPlayer = sortedPlayer[nextPlayer];
				int base = 0;
				if (player[i][curPlayer] == 1) {
					base = 1;
				} else if (player[i][curPlayer] == 2) {
					base = 2;
				} else if (player[i][curPlayer] == 3) {
					base = 3;
				} else if (player[i][curPlayer] == 4) {
					base = 4;
				} else
					out--;

                // 주자 및 점수 계산
				runner = processBase(runner, base);
				nextPlayer = (nextPlayer + 1) % 9;
			}
		}
		
		maxScore = Math.max(maxScore, totalScore);
	}

	private static int processBase(int runner, int base) {
        // 안타인 경우 타자 진루
		if (base > 0)
			runner = runner | 1 << 0;
        // shift 연산을 통해 모든 주자 진루
		while (base-- > 0) {
            // 3루에 주자가 있으면 점수 + 1
			if ((runner & 1 << 3) > 0) {
				runner = runner | 0 << 3;
				totalScore += 1;
			}
			runner = runner << 1;
		}
		return runner;
	}

	static boolean nextPermutation() {
		int srcIdx = 8;
		while (--srcIdx > 0) {
			if (selection[srcIdx] > selection[srcIdx - 1])
				break;
		}
		if (--srcIdx < 0)
			return false;

		int destIdx = 8;
		while (--destIdx >= 0) {
			if (selection[srcIdx] >= selection[destIdx])
				continue;
			swap(srcIdx, destIdx);
			break;
		}

		destIdx = 8;
		while (++srcIdx <= --destIdx) {
			swap(srcIdx, destIdx);
		}
		return true;
	}

	static void swap(int src, int dest) {
		int temp = selection[src];
		selection[src] = selection[dest];
		selection[dest] = temp;
	}
}