import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Qubeue;

public class Solution1938 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
    static int N;
    static int[][] map;
    static boolean[][][] visited;
    static Pair start, end;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        ArrayList<int[]> B = new ArrayList<>();
        ArrayList<int[]> E = new ArrayList<>();
        map = new int[N][N];
        visited = new boolean[N][N][2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                char cur = line.charAt(j);
                if (cur == 'B') {
                    int[] s = {i, j};
                    B.add(s);
                } else if (cur == 'E') {
                    int[] s = {i, j};
                    E.add(s);
                } else
                    map[i][j] = Character.getNumericValue(cur);
            }
        }

        // initialize
        if (B.get(0)[0] == B.get(1)[0]) {
            start = new Pair(B.get(1)[0], B.get(1)[1], 1, 0);
            visited[start.x][start.y][1] = true;
        } else {
            start = new Pair(B.get(1)[0], B.get(1)[1], 0, 0);
            visited[start.x][start.y][0] = true;
        }

        if (E.get(0)[0] == E.get(1)[0])
            end = new Pair(E.get(1)[0], E.get(1)[1], 1, 0);
        else
            end = new Pair(E.get(1)[0], E.get(1)[1], 0, 0);

        System.out.println(bfs(start));
    }

    private static int bfs(Pair start) {
        Queue<Pair> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            Pair cur = q.poll();
            if (cur.x == end.x && cur.y == end.y && cur.direction == end.direction) {
                return cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                if (!isValid(cur.x - dx[cur.direction], cur.y - dy[cur.direction], cur.direction, i) || visited[cur.x + dx[i]][cur.y + dy[i]][cur.direction]) continue;
                visited[cur.x + dx[i]][cur.y + dy[i]][cur.direction] = true;
                q.add(new Pair(cur.x + dx[i], cur.y + dy[i], cur.direction, cur.dist + 1));
            }

            int rotateDirection = (cur.direction == 1) ? 0 : 1;
            if (!canRotate(cur.x, cur.y) || visited[cur.x][cur.y][rotateDirection]) continue;
            visited[cur.x][cur.y][rotateDirection] = true;
            q.add(new Pair(cur.x, cur.y, rotateDirection, cur.dist + 1));
        }
        return 0;
    }

    private static boolean canRotate(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i < 0 || i >= N || j < 0 || j >= N || map[i][j] == 1) return false;
            }
        }
        return true;
    }

    private static boolean isValid(int x, int y, int curD, int d) {
        for (int i = 0; i < 3; i++) {
            int nX = x + dx[d];
            int nY = y + dy[d];
            if (nX < 0 || nY < 0 || nX >= N || nY >= N || map[nX][nY] == 1) return false;
            x += dx[curD];
            y += dy[curD];
        }
        return true;
    }

    static class Pair {
        int x;
        int y;
        int direction;
        int dist;

        public Pair(int x, int y, int direction, int dist) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.dist = dist;
        }
    }
}