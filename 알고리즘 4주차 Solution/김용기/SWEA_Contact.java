package 스터디4주차;

import java.io.*;
import java.util.*;


public class SWEA_Contact {
	static int N;
	static boolean[][] data;
	static boolean[] Select;
	static Queue<Node> queue;
	static ArrayList<Node> anslist;
	static int DEPTH;
	static int ans; 
	
	
	static class Node{
		int Nnum; //노드번호 
		int d;// 깊이 
		
		Node(int Nnum, int d) {
			this.Nnum = Nnum;
			this.d = d;
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st =new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			data= new boolean[101][101];
			Select = new boolean[101];
			queue = new LinkedList<Node>();//BFS에 사용될 큐 
			anslist = new ArrayList<Node>();//결과 노드들을 저장할 어레이 리스트 
			
			
			DEPTH = Integer.MIN_VALUE;
			ans = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N/2; i++) {
				data[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
			}
			
			BFS(start);
			
			System.out.println("#" + tc + " " + ans);
		}
	}
	
	static void BFS(int start) {
		queue.add(new Node(start,0));
		anslist.add(new Node(start,0));
		Select[start] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int Nnum = node.Nnum;
			int depth = node.d;
			
			for (int i = 0; i < 101; i++) {
				if(Select[i]) continue;
				if(!data[Nnum][i]) continue;
				Select[i] = true;
				queue.add(new Node(i,depth+1));
				anslist.add(new Node(i,depth+1));
			}
			DEPTH = Math.max(DEPTH, depth);
			
		}
		
		for (int i = 0; i < anslist.size(); i++) {
			if(anslist.get(i).d == DEPTH)
				ans = Math.max(ans, anslist.get(i).Nnum);
		}
	}
}
