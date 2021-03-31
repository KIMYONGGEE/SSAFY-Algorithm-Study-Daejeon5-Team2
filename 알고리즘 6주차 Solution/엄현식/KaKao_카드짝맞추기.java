package firstProject;

import java.util.LinkedList;
import java.util.Queue;

public class KaKao_카드짝맞추기 {
	static boolean[] icon;
	static int[] save;
	static int[] element;
	static boolean[] check;
	static Pair[] icon_first;
	static Pair[] icon_sec;
	static int now_x,now_y;
	static int[] dx= {-1,0,1,0};
	static int[]  dy= {0,1,0,-1};
	static int min=Integer.MAX_VALUE;
	static int maps[][];
	static class Pair implements Comparable<Pair> {
		int x;
		int y;
		int count;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public Pair(int x, int y,int count) {
			this.x = x;
			this.y = y;
			this.count=count;
		}
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return this.count-o.count;
		}

	}

	static void permu(int target, int k,int r ,int c) {

		if (target == k) {
		//	System.out.println(Arrays.toString(save));
			move(r,c);
			//System.out.println(tmp);
			return;
		}
		
		
		for(int i=0;i<element.length;i++) {
			if(!check[i]) {
				check[i]=true;
				save[target] = element[i];
				permu(target+1,k,r,c);
				check[i]=false;
			}
		}
		
		


	}
	public static Pair shiftmove(int r, int c ,int rotate,int[][] copymaps) {
		int x=r;
		int y=c;

		while (true) {
			int nx=x+dx[rotate];
			int ny=y+dy[rotate];
			if(nx<0||nx>=4||ny<0||ny>=4)return new Pair(x,y);

			if(copymaps[nx][ny]!=0) {
				return new Pair(nx,ny);
			}
			
			x=nx;
			y=ny;
			
		}
		
		
	}
	
	public static int findshortest(int r, int c,int target_x,int target_y,int[][] copymaps) {
		
		boolean[][] visited=new boolean[4][4];
		visited[r][c]=true;
		Queue<Pair> q= new LinkedList<>();
		q.add(new Pair(r,c,0));
		int count=0;
		while(!q.isEmpty()) {
			
			Pair temp =q.poll();
			System.out.println(temp.x+" "+temp.y+" 들");
			System.out.println();
			visited[temp.x][temp.y]=true;
			if(temp.x==target_x&&temp.y==target_y) {
				count=temp.count;
				break;
			}
		
			for(int i=0;i<dx.length;i++) {
				
				Pair shift=shiftmove(temp.x,temp.y,i,copymaps);
				System.out.println(shift.x+" "+shift.y+ ""+i );
				if(!visited[shift.x][shift.y]) {
					visited[shift.x][shift.y]=true;
					shift.count=temp.count+1;
					q.add(shift);
				}
				
			}
			
			for(int i=0;i<dx.length;i++) {
				int nx=temp.x+dx[i];
				int ny=temp.y+dy[i];
				if(nx<0||nx>=4||ny<0||ny>=4) {
					continue;
				}
				
				if(!visited[nx][ny]) {
					visited[nx][ny]=true;
					q.add(new Pair(nx,ny,temp.count+1));
				}
				
				
			}
			
			
			
			
			
		}
		System.out.println(count+" 움직임 ");
		return count;
		
		
		
		
		
		
	}
	
	public static int [][] deepcopy(){
		int [][] copymaps=new int[4][4];
		for(int i=0;i<maps.length;i++) {
			for(int j=0;j<maps.length;j++) {
				copymaps[i][j]=maps[i][j];
			}
		}
		return copymaps;
		
	}
	
	public static Pair enter(int r,int c,int pick,int[][] copymaps) {
		
		int count1=0;
		int count2=0;
		
		int x =icon_first[pick].x;
		int y=icon_first[pick].y;
		
		int x1=icon_sec[pick].x;
		int y1=icon_sec[pick].y;
		
		int target_x=-1;
		int target_y=-1;
		int next_target_x=-1;
		int next_target_y=-1;

		
		int dist1=findshortest(r,c,x,y,copymaps); //어떤 거를 잡는게 유리한지 비교 
		int dist2=findshortest(r,c,x1,y1,copymaps);
		
		if(dist1<dist2) {
	
			target_x=x;
			target_y=y;
			next_target_x=x1;
			next_target_y=y1;
		}
		else {
	
			target_x=x1;
			target_y=y1;
			next_target_x=x;
			next_target_y=y;
		}
		
		//System.out.println("첫 타깃 "+ target_x +" "+target_y);
		count1=findshortest(r,c,target_x,target_y,copymaps)+1;//grab
		copymaps[target_x][target_y]=0;// 캐릭터 지우기 
		//System.out.println(" 첫 캐릭터 잡음 "+ " 이동횟수 "+count1);
		
		//System.out.println("두번째  타깃 "+ next_target_x +" "+next_target_y);
		count2=findshortest(target_x,target_y,next_target_x,next_target_y,copymaps)+1;
		copymaps[next_target_x][next_target_y]=0;
		//System.out.println(" 두번 캐릭터 잡음 "+ " 이동횟수 "+count2);
		
		
		//System.out.println(" 이동 풀 카운트 "+(count1+count2));
		return new Pair(next_target_x,next_target_y,count1+count2);
	}
	
	public static void move(int r ,int c) {
		int counts=0;
		int x=r;
		int y=c;
		
		int [][] copymaps=deepcopy();//배열 지우면 변환 되니까 deep copy  
		
		for(int i=0;i<save.length;i++) {
			int pick=save[i];
			Pair temp=enter(x,y,pick,copymaps);
			
			x=temp.x;
			y=temp.y;
			counts+=temp.count;

			
		}
		
		//System.out.println(counts);
		if(counts<min)min=counts;
		
	}
	
	
	
	
	public static int solution(int[][] board, int r, int c) {
		int answer = 0;
		int k = 0;
		icon = new boolean[7];
		icon_first=new Pair[7];
		icon_sec=new Pair[7];
		maps=new int[4][4];
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				maps[i][j]=board[i][j];
			}
		}
		
		for (int i = 0; i < board.length; i++) { // icon 에 저장되어있으면 두번재 좌표에 저장 
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != 0) {
					// if문처
					if (!icon[board[i][j]]) {
						icon_first[board[i][j]]=new Pair(i,j);
						icon[board[i][j]] = true;
						k += 1;
					}
					else {
						icon_sec[board[i][j]]=new Pair(i,j);
					}

				}
			}
		}
		element = new int[k]; // 캐릭터 저장 요소 
		check = new boolean[k]; // permu check 배열 
		
		int idx = 0;
		for (int i = 1; i < icon.length; i++) {
			if (icon[i])
				element[idx++] = i;
		}
		//System.out.println(Arrays.toString(element));
		save = new int[k];
		permu(0, k,r,c);//순열 돌리기 
		
				
		return answer;
	}
	public static void main(String[] args) {
		int [][] temp= {{1,0,0,3},{2,0,0,0},{0,0,0,2},{3,0,1,0}};
		solution(temp,1,0);
	}
}
