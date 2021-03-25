package firstProject;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_expert_contact {

	static class Pair {
		int x;
		int count;

		public Pair(int x, int count) {
			this.x = x;
			this.count = count;
		}

	}

	static boolean[] check;
	static boolean[][] maps;
	static int start;
	static int max;
	static int maxvalue;
	public static void bfs(int tc) {

		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(start - 1, 0));
		while (!q.isEmpty()) {

			Pair t = q.poll();

			int temp = t.x;
			int count = t.count;

			if (count > max) {
				max = count;
				maxvalue=temp;
			} else if (count == max &&maxvalue<temp) {
				maxvalue=temp;
			}

			if (check[temp])
				continue;
			check[temp] = true;
			for (int i = 0; i < 100; i++) {
				if (maps[temp][i] && !check[i])
					q.add(new Pair(i, count + 1));

			}

		}

		System.out.printf("#%d %d",tc,maxvalue+1);
		System.out.println();

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			int dataLen = sc.nextInt();
			start = sc.nextInt();
			check = new boolean[100];
			maps = new boolean[100][100];
			max = Integer.MIN_VALUE;
			maxvalue=0;
			for (int i = 0; i < dataLen / 2; i++) {

				int s = sc.nextInt();
				int e = sc.nextInt();
				maps[s - 1][e - 1] = true;

			}

			bfs(tc);
			
		}

	}
}
