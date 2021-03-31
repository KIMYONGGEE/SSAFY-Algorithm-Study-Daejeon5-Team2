package study_5_weeks;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bak_11967 {
	static int N, M;
	static Node[][] edge;
	static boolean[][] light;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int count;

	static class Node {

		int x;
		int y;
		Node nextnode;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, Node nextnode) {
			super();
			this.x = x;
			this.y = y;
			this.nextnode = nextnode;
		}

	}

	public static void bfs() {

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0));
		count = 1;
		light[0][0] = true;
		visited[0][0] = true;
		while (!q.isEmpty()) {

			Node now = q.poll();
			//System.out.println((now.x+1)+" "+(now.y+1));
			for (Node temp = edge[now.x][now.y]; temp != null; temp = temp.nextnode) {
				if (!light[temp.x][temp.y]) {
					light[temp.x][temp.y] = true;
					q=new LinkedList<>();
					q.add(new Node(0,0));
					visited=new boolean[N][N];
					visited[0][0]=true;
					count += 1;
				}
			}
			for (int i = 0; i < dx.length; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				if (!visited[nx][ny] && light[nx][ny]) {
					visited[nx][ny]=true;
					q.add(new Node(nx, ny));

				}

			}

		}

	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		light = new boolean[N][N];
		visited = new boolean[N][N];
		edge = new Node[N][N];
		for (int i = 0; i < M; i++) {
			int x1 = sc.nextInt();
			int y1 = sc.nextInt();
			int x2 = sc.nextInt();
			int y2 = sc.nextInt();
			x1 -= 1;
			y1 -= 1;
			x2 -= 1;
			y2 -= 1;
			//System.out.println();
			edge[x1][y1] = new Node(x2, y2, edge[x1][y1]);
		}
		bfs();
	//	System.out.println();
		int lightCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (light[i][j]) {
					lightCount += 1;
				//	System.out.println((i+1)+" "+(j+1));
				}
			}
		}
		System.out.println(lightCount);

	}
}
