import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int W,H,N;
    static int[][] map;
    static int[] select;
    static int[][] visited;
    static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
    static int maxCnt;
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        int[] result = new int[T];
        for (int i = 0; i < T; i++) {
            result[i] = getResult();
        }
 
        for (int i = 0; i < T; i++) {
            System.out.printf("#%d %d\n", i + 1, result[i]);
        }
    }
 
    static int getResult() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        W = Integer.parseInt(input[1]);
        H = Integer.parseInt(input[2]);
        int totalCnt = 0;
        maxCnt = 0;
        select = new int[N];
        map = new int[H][W];
        for (int i = 0; i < H; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if (map[i][j] == 0) continue;
                totalCnt++;
            }
        }
        permutation(0);
        return totalCnt - maxCnt;
    }
    private static void permutation(int cnt) {
        if (cnt == N) {
            crash();
            return;
        }
        for (int i = 0; i < W; i++) {
            select[cnt] = i;
            permutation(cnt + 1);
            select[cnt] = -1;
        }
    }
 
    private static void crash() {
        visited = new int[H][W];
        copy();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int startX = -1;
            int startY = select[i];
            while(++startX < H) {
                if (visited[startX][startY] != 0) break;
            }
            if (startX == H) continue;
            sum += dfs(startX, startY);
            compaction();
        }
        maxCnt = Math.max(maxCnt, sum);
    }
 
    static void copy() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                visited[i][j] = map[i][j];
            }
        }
    }
 
    private static void compaction() {
        for (int i = 0; i < W; i++) {
            for (int j = H-1; j > 0; j--) {
                if (visited[j][i] != 0) continue;
                int idx = j;
                while(--idx >= 0)
                    if (visited[idx][i] != 0) break;
 
                if(idx != -1) {
                    visited[j][i] = visited[idx][i];
                    visited[idx][i] = 0;
                }
            }
        }
    }
 
    private static int dfs(int startX, int startY) {
        int cnt = 0;
        Stack<Pair> s = new Stack<>();
        s.push(new Pair(startX, startY));
        while (!s.isEmpty()) {
            Pair cur = s.pop();
            if (visited[cur.x][cur.y] == 0) continue;
            for (int i = 0; i < 4; i++) {
                int nX = cur.x;
                int nY = cur.y;
                for (int size = 1; size < visited[cur.x][cur.y]; size++) {
                    nX += dx[i];
                    nY += dy[i];
                    if (nX < 0 || nY < 0 || nX >= H || nY >= W || visited[nX][nY] == 0) continue;
                    s.push(new Pair(nX, nY));
                }
            }
            visited[cur.x][cur.y] = 0;
            cnt++;
        }
 
        return cnt;
    }
 
    static class Pair {
        int x;
        int y;
 
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}