import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution11967 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Pair>[][] switches;
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N + 1][N + 1];
        switches = new ArrayList[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        //init
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                switches[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            String[] line = br.readLine().split(" ");
            Pair src = new Pair(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
            Pair dest = new Pair(Integer.parseInt(line[2]), Integer.parseInt(line[3]));
            switches[src.x][src.y].add(dest);
        }

        System.out.println(switchOn(1, 1));
    }

    private static int switchOn(int startX, int startY) {
        int totalCnt = 1;
        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(1, 1));
        visited[1][1] = true;
        map[1][1] = 1;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            ArrayList<Pair> switchList = switches[cur.x][cur.y];

            // 사방에 1인데 visited 안한게 있으면 q에 삽입 => 이전에는 방문하지 못했던 경우 커
            for (int i = 0; i < 4; i++) {
                int nX = cur.x + dx[i];
                int nY = cur.y + dy[i];
                if (nX < 1 || nY < 1 || nX > N || nY > N || visited[nX][nY] || map[nX][nY] == 0) continue;
                visited[nX][nY] = true;
                q.add(new Pair(nX, nY));
            }

            // 스위치로 불이 켜지는 방 탐색
            for (Pair p : switchList) {
                // 이미 켜진 방이면 Skip
                if (map[p.x][p.y] == 1) continue;
                totalCnt++;
                map[p.x][p.y] = 1;
                // 불을 키려는 방이 방문할 수 없는 방인 경우
                if (!isContinous(p.x, p.y)) continue;
                // 방문할 수 있는 경우
                visited[p.x][p.y] = true;
                q.add(p);
            }
        }
        return totalCnt;
    }

    private static boolean isContinous(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];
            if (nX >= 1 && nY >= 1 && nX <= N && nY <= N && visited[nX][nY]) return true;
        }
        return false;
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