package firstProject;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sw_expert_가로세로 {

	static int[] dx = { -1, 0, 1, 0 };// 위,오,아,왼
	static int[] dy = { 0, 1, 0, -1 };
	static int dest_x, dest_y;
	static int answercount = Integer.MAX_VALUE;
	static boolean[][] check;

	static class Pair {
		int x;
		int y;
		int count;
		boolean flag;

		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + ", count=" + count + ", flag=" + flag + "]";
		}

		public Pair(int x, int y, int count, boolean flag) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.flag = flag;
		}
	}

	private static void bfs(int x, int y, boolean flag) {
		Queue<Pair> q = new LinkedList<>();

		q.add(new Pair(x, y, 0, flag));// flag가 true면 가로

		
		while (!q.isEmpty()) {

			Pair temp = q.poll();

			if (temp.x == dest_x && temp.y == dest_y) {
			
				if (answercount > temp.count) {
					answercount = temp.count;
				}
				break;
			}

			if (check[temp.x + 100][temp.y + 100]) {
				continue;
			}
			
			check[temp.x + 100][temp.y + 100] = true;

			for (int i = 0; i < dx.length; i++) {

				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];

				if (nx < -100 || nx > 100 || ny < -100 || ny > 100)
					continue;
				if (temp.flag) {// 전에 가로였음
					if (check[nx + 100][ny + 100] == false && (i == 0 || i == 2))
						q.add(new Pair(nx, ny, temp.count + 1, false));
				}

				else {
					if (check[nx + 100][ny + 100] == false && (i == 1 || i == 3))
						q.add(new Pair(nx, ny, temp.count + 1, true));
				}

			}

		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = sc.nextInt();
		for (int t = 1; t <= tc; t++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			dest_x = sc.nextInt();
			dest_y = sc.nextInt();
			answercount=Integer.MAX_VALUE;
			check = new boolean[202][202];
			bfs(x1, y1, true);// 전에 가로
			check = new boolean[202][202];
			bfs(x1, y1, false);// 전에 세로
			System.out.printf("#%d %d",t,answercount);
			System.out.println();
		}

	}
}
