package firstProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;


public class sw_expert_길찾기 {
	static int N;
	static Map<Integer, ArrayList<Integer>> maps;
	static int answercount;
	static boolean[] check;
	static class Pair {
		int x;
		int count;


		public Pair(int x, int count) {
			this.x = x;
			this.count = count;
			
		}
	}
	
	public static void bfs() {
		
		Queue<Pair> q = new LinkedList<>();

		q.add(new Pair(0, 0));// flag가 true면 가로

		
		while (!q.isEmpty()) {

			Pair temp = q.poll();
		
			if (temp.x==99) {
				//System.out.println("enter");
				answercount=1;
				break;
			}

			if (check[temp.x]) {
				continue;
			}
			
			check[temp.x] = true;
			
			if(maps.containsKey(temp.x)){
				for(int i=0;i<maps.get(temp.x).size();i++) {
					int next=maps.get(temp.x).get(i);
					if(check[next]==false) {
						q.add(new Pair(next,temp.count+1));
					}
				}
				
			}
			
	

		}
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		for (int t = 1; t <= 10; t++) {

			int tc = sc.nextInt();
			N = sc.nextInt();
			maps = new HashMap<Integer, ArrayList<Integer>>();
			check=new boolean[100];
			answercount=0;
			for (int i = 0; i < N; i++) {
				int start = sc.nextInt();
				int end = sc.nextInt();
				if (maps.containsKey(start)) {
					maps.get(start).add(end);
				} else {
					ArrayList temp = new ArrayList<Integer>();
					temp.add(end);
					maps.put(start, temp);
				}

			}
			bfs();

			System.out.printf("#%d %d",t,answercount);
			System.out.println();

		}

	}
}
