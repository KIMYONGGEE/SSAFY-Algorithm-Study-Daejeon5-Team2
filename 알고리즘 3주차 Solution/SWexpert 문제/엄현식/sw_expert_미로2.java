package firstProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


public class sw_expert_미로2 {
	
	
	static int [][]maps;
	static int start_x,start_y,end_x,end_y;
	static int answercount;
	static boolean [][]check;
	static int[] dx = { -1, 0, 1, 0 };// 위,오,아,왼
	static int[] dy = { 0, 1, 0, -1 };
	static class Pair {
		int x;
		int y;
		int count;


		public Pair(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
			
		}


		@Override
		public String toString() {
			return "Pair [x=" + x + ", y=" + y + ", count=" + count + "]";
		}
	}
	private static void bfs() {
		Queue<Pair> q = new LinkedList<>();

		q.add(new Pair(start_x, start_y, 0));

		
		while (!q.isEmpty()) {

			Pair temp = q.poll();
		
			if (temp.x == end_x && temp.y ==end_y) {
				//System.out.println("enter");
				answercount=1;
				break;
			}

			if (check[temp.x][temp.y]) {
				continue;
			}
			
			check[temp.x][temp.y] = true;

			for (int i = 0; i < dx.length; i++) {

				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];

				if (nx <0 || nx > 100 || ny < 0 || ny > 100)
					continue;
				
				if(!check[nx][ny]&&(maps[nx][ny]==0||maps[nx][ny]==3))
					q.add(new Pair(nx,ny,temp.count+1));

			}

		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
	BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
	for (int t = 1; t <= 10; t++) {
		String n=br.readLine();
		maps=new int [101][101];
		check=new boolean[101][101];
		answercount=0;
		start_x=-1;
		start_y=-1;
		end_x=-1;
		end_y=-1;
		for(int i=0;i<100;i++) {
			String temp =br.readLine();
			for(int j=0;j<temp.length();j++) {
				maps[i][j]=Integer.parseInt(Character.toString(temp.charAt(j)));
				if(maps[i][j]==2) {
					start_x=i;
					start_y=j;
				}
				if(maps[i][j]==3) {
					end_x=i;
					end_y=j;
				}
			}
		}
		
		bfs();
		
		System.out.printf("#%d %d",t,answercount);
		System.out.println();
	}
}
}
