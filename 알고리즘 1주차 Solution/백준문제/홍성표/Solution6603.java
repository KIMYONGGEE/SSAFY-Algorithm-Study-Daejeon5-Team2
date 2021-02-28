import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution6603 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int K;
	static int[] flag, nums;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		while (true) {
			String[] line = br.readLine().split(" ");
			if (line[0].equals("0")) {
				System.out.println(sb.toString());
				return;
			}
			getLottoCase(line);
		}
	}

	private static void getLottoCase(String[] line) {
		K = Integer.parseInt(line[0]);
		nums = new int[K];
		flag = new int[K];
		for (int i = 0; i < K; i++) {
			nums[i] = Integer.parseInt(line[i + 1]);
		}

		Arrays.sort(nums);

		int cnt = K-6;
		int idx = K;
		while (--cnt >= 0)
			flag[--idx] = 1;
        // 0이 포함된 수를 확인 (내림차순으로 확인해야 하므로)
		do {
			getLottoNums();
		} while (nextPermutation());
		sb.append("\n");
	}

	private static void getLottoNums() {
		for (int i = 0; i < flag.length; i++) {
			if (flag[i] == 1)
				continue;
			sb.append(nums[i] + " ");
		}
		sb.replace(sb.length() - 1, sb.length(), "\n");
	}

	static boolean nextPermutation() {
		int srcIdx = flag.length;
		while (--srcIdx > 0) {
			if (flag[srcIdx] > flag[srcIdx - 1])
				break;
		}
		if (--srcIdx == -1)
			return false;

		int destIdx = flag.length;
		while (--destIdx >= 0) {
			if (flag[srcIdx] >= flag[destIdx])
				continue;
			swap(srcIdx, destIdx);
			break;
		}

		destIdx = flag.length;
		while (++srcIdx <= --destIdx) {
			swap(srcIdx, destIdx);
		}
		return true;
	}

	private static void swap(int srcIdx, int destIdx) {
		int temp = flag[srcIdx];
		flag[srcIdx] = flag[destIdx];
		flag[destIdx] = temp;
	}
}