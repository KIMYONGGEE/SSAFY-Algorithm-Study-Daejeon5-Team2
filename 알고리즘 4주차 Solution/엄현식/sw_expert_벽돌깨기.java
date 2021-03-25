package firstProject;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
class sw_expert_벽돌깨기 {
     
    static int[][] maps;
    static int N, H, W;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int allBrick, min;
 
    static class Pair {
        int x;
        int y;
 
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
 
    }
 
    static int[][] deepCopy(int[][] maps) { //깊은 복사 
        int[][] copymaps = new int[H][W];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copymaps[i][j] = maps[i][j];
            }
        }
        return copymaps;
 
    }
 
    static int[][] grab(int[][] maps) { // 아래로 당기기 
        int[][] grabmaps = new int[H][W];
        for (int i = 0; i < W; i++) {
            int idx = H - 1;
            for (int j = H - 1; j >= 0; j -= 1) {
                if (maps[j][i] != 0) {
                    grabmaps[idx--][i] = maps[j][i];
                }
            }
        }
        return grabmaps;
    }
 
    static void bfs(int x, int y, int[][] maps, int step, int counts) {
    	// bfs dfs 혼합 
 
        if (step == N) {
        	//벽돌 N번 부시면 return 
            return;
        }
 
        int[][] copymaps = deepCopy(maps);  // 원래의 map 건들지 않기 
        Queue<Pair> q = new LinkedList<>(); 
        q.add(new Pair(x, y));
        int count = 0;
 
        while (!q.isEmpty()) {
            Pair temp = q.poll();
 
            int move = copymaps[temp.x][temp.y];
            if (copymaps[temp.x][temp.y] == 0)
                continue;
            copymaps[temp.x][temp.y] = 0;
            count += 1;
            if (move == 1)
                continue;
            for (int i = 0; i < dx.length; i++) {
                for (int movecount = 1; movecount < move; movecount++) {
                    int nx = temp.x + dx[i] * movecount;
                    int ny = temp.y + dy[i] * movecount;
                    if (nx < 0 || nx >= H || ny < 0 || ny >= W)
                        continue;
                    if (copymaps[nx][ny] != 0) {
                        q.add(new Pair(nx, ny));
 
                    }
                }
 
            }
 
        }
 
        if (allBrick - (counts+count) < min) {
 
            min = allBrick - (counts+count);
        }
 
        int[][] grabmaps = grab(copymaps);
 
        boolean check[] = new boolean[W];
 
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (!check[j] && grabmaps[i][j] != 0) {
                    bfs(i, j, grabmaps, step + 1, count + counts);
                    check[j] = true;
                }
            }
 
        }
 
    }
 
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            N = sc.nextInt();
            W = sc.nextInt();
            H = sc.nextInt();
            min = Integer.MAX_VALUE;
            maps = new int[H][W];
            allBrick = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    maps[i][j] = sc.nextInt();
                    if (maps[i][j] != 0)
                        allBrick += 1;
                }
            }
 
            boolean check[] = new boolean[W];
 
            // 1. 가장 위에 있는 걸 뽑는다 .
            // 2. bfs 돌린다. 
 
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (!check[j] && maps[i][j] != 0) {
                        bfs(i, j, maps, 0, 0);
                        check[j] = true;
                    }
                }
 
            }

            System.out.printf("#%d %d", tc, min);
 
            System.out.println();
        }
    }
 
}