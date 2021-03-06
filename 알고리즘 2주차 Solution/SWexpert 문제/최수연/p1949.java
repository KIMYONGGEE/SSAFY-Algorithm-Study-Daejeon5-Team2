import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int x, y, height;
    boolean isCut;
    int[][] vis;

    public Node(int x, int y, int height, boolean isCut, int[][] vis) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.isCut = isCut; //깎음 여부
        this.vis = vis;
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class p1949 {
    static int N, K, H;
    static int[][] map;
    static List<Point> highestPeaks = new ArrayList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        //1. 가장 높은 봉우리 찾기
        //2. 각각에 대해 bfs <- 깎을수 있음을 고려
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            H = map[0][0];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > H) {
                        H = map[i][j]; // 가장 높은 봉우리의 높이
                        highestPeaks.clear();
                        highestPeaks.add(new Point(i, j));
                    } else if (map[i][j] == H) {
                        highestPeaks.add(new Point(i, j));
                    }
                }
            }
            int ans = findMaxRoad();
            System.out.println("#" + t + " " + ans);
        }
    }

    private static int findMaxRoad() {
        int max = 0; // 만들 수 있는 가장 긴 등산로의 길이
        //가장 높은 봉우리 각각 bfs
        for (int i = 0; i < highestPeaks.size(); i++) {
            Point cur = highestPeaks.get(i);
            int roadLength = bfs(cur.x, cur.y);
            max = Math.max(roadLength, max);
        }
        return max;
    }

    private static int bfs(int a, int b) {
        Queue<Node> queue = new LinkedList<>();
        int[][] vis = new int[N][N];
        vis[a][b] = 1;
        int roadLength = 1;
        queue.add(new Node(a, b, map[a][b], false, vis));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x, y = cur.y, h = cur.height;
            boolean isCut = cur.isCut;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (cur.vis[nx][ny] != 0) continue;
                int[][] visited = CopyVisitArray(cur.vis);
                if (map[nx][ny] < h) { // 이동한 위치의 높이가 현재 높이보다 낮은 경우
                    visited[nx][ny] = visited[x][y] + 1;
                    queue.add(new Node(nx, ny, map[nx][ny], isCut, visited));
                } else if (map[nx][ny] >= h && !isCut && map[nx][ny] - K < h) {
                    // 이동할 높이가 더 크다면
                    // (아직 깎는 공사를 하지 않음 + 최대 K만큼 깎을때 현재 높이보다 낮음) 시 이동 가능
                    visited[nx][ny] = visited[x][y] + 1;
                    queue.add(new Node(nx, ny, h - 1, true, visited));
                } else {
                    continue;
                }
                roadLength = Math.max(roadLength, visited[nx][ny]);
            }
        }
        return roadLength;
    }

    private static int[][] CopyVisitArray(int[][] vis) {
        int[][] arr = new int[N][N];
        for(int j = 0; j < N; j++){
            arr[j] = vis[j].clone();
        }
        return arr;
    }
}
