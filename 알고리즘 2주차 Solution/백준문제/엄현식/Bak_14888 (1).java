import java.util.Arrays;
import java.util.Scanner;

public class Bak_14888 {
	static int N, M;
	static int[] arr;
	static int[] save;
	static int[] ehang;
	static int[] how;
	static int min, max;
	static boolean[] isSelected;

	static void permu(int target) {
		if (target == how.length) {
			int value = arr[0];
			for (int i = 0; i < how.length; i++) {
				switch (save[i]) {
				case 0:
					value = value + arr[i + 1];
					break;
				case 1:
					value = value - arr[i + 1];
					break;
				case 2:
					value = value  * arr[i + 1];
					break;
				case 3:
					value = (value / arr[i + 1]);
					break;

				}
			}
			if (min > value) {
				min = value;
			}
			if (max < value) {
				max = value;
			}
			return;
		}

		for (int i = 0; i < how.length; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				save[target] = how[i];
				permu(target + 1);
				isSelected[i] = false;
			}
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = N - 1;
		arr = new int[N];
		ehang = new int[4];
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;

		isSelected = new boolean[M];
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		for (int i = 0; i < 4; i++) {
			ehang[i] = sc.nextInt();
		}
		how = new int[M];
		save = new int[M];
		int idx = 0;
		for (int i = 0; i < 4; i++) {
			if (ehang[i] > 0) {
				for (int j = 0; j < ehang[i]; j++) {
					how[idx++] = i;
				}
			}
		}
		permu(0);
		System.out.println(max);
		System.out.println(min);
	}

}
