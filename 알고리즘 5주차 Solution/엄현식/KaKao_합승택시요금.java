package firstProject;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class KaKao_합승택시요금 {
	static int[][] maps;

	public static class Pair{
		int vertex;
		int distance;
		public Pair(int vertex,int distance) {
			this.vertex=vertex;
			this.distance=distance;
		}
		
		
	}
	public static void dikstra(int s,int[] dist){
		dist[s]=0;
		Queue<Pair> q=new LinkedList<>();
		q.add(new Pair(s,0));
		
		while(!q.isEmpty()) {
			
			Pair now=q.poll();
			
			for(int i=0;i<maps[now.vertex].length;i++) {
				if(maps[now.vertex][i]!=0) {
					if(dist[i]>now.distance+maps[now.vertex][i]) {
						dist[i]=now.distance+maps[now.vertex][i];
						q.add(new Pair(i,now.distance+maps[now.vertex][i]));
						
					}
				}
			}
			
			
		}
		

	     }

	public int solution(int n, int s, int a, int b, int[][] fares) {
		int answer = 0;
		maps = new int[n][n];
		
		int[] dist= new int[n];
		int[] dist2= new int[n];
		int[] dist3= new int[n];
	
		Arrays.fill(dist, Integer.MAX_VALUE);
		Arrays.fill(dist2, Integer.MAX_VALUE);
		Arrays.fill(dist3, Integer.MAX_VALUE);
		for (int i = 0; i < fares.length; i++) {
			int st = fares[i][0];
			int e = fares[i][1];
			int fare = fares[i][2];
			st -= 1;
			e -= 1;
			maps[st][e] = fare;
			maps[e][st] = fare;

		}
		dikstra(s-1,dist);
		dikstra(a-1,dist2);
		dikstra(b-1,dist3);
        int min =Integer.MAX_VALUE;
        for(int i=0;i<dist.length;i++){
            if(min>dist[i]+dist2[i]+dist3[i]){
                min=dist[i]+dist2[i]+dist3[i];
            }
        }
        return min;

	
	}
}
