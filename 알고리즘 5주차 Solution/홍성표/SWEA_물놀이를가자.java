import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SolutionSWEA {
    static class Pair{
        int x;
        int y;
        int dist;

        public Pair(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, totalDist;
    static char[][] map;
    static int[][] visited;
    static Queue<Pair> q;
    static int[] dx = {1,-1,0,0}, dy = {0,0,1,-1};
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
        M = Integer.parseInt(input[1]);
        map = new char[N][M];
        visited = new int[N][M];
        q = new LinkedList<>();
        totalDist = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') continue;
                q.add(new Pair(i,j,0));
                visited[i][j] = 0;
            }
        }

        bfs();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                totalDist += visited[i][j];
            }
        }
        return totalDist;
    }

    private static void bfs() {
        while(!q.isEmpty()) {
            Pair cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nX = cur.x + dx[i];
                int nY = cur.y + dy[i];
                if (nX < 0 || nY < 0 || nX >= N || nY >= M  || visited[nX][nY] <= cur.dist + 1 || map[nX][nY] == 'W') continue;
                visited[nX][nY] = cur.dist + 1;
                q.add(new Pair(nX, nY, cur.dist + 1));
            }
        }
    }
}