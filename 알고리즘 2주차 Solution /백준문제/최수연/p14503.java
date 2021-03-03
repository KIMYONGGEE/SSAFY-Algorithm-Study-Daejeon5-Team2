import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Node {
    int x, y, d;

    public Node(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }
}

public class p14503 {
    static int N, M;
    static int[][] arr;
    static boolean[][] vis;
    static int ans = 0; //로봇 청소기가 청소하는 칸의 개수

    public static void main(String[] args) throws IOException {
        //입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        vis = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cleaning(r, c, d);
        System.out.println(ans);
    }

    static int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //인덱스 번호가 방향으로 전진하는 번호
    static int[][] goBack = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}}; // 후진

    private static void cleaning(int r, int c, int direction) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(r, c, direction));
        vis[r][c] = true;
        ans++;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int x = cur.x, y = cur.y, d = cur.d;
            int dirIdx = d;
            boolean allClean = true;
            for (int i = 0; i < 4; i++) {
                dirIdx--;
                if(dirIdx < 0) dirIdx = 3;
                int nx = x + dir[dirIdx][0];
                int ny = y + dir[dirIdx][1];
                if (nx <= 0 || nx >= N - 1 || ny <= 0 || ny >= M - 1 || vis[nx][ny] || arr[nx][ny] == 1) {
                    continue;
                }
                vis[nx][ny] = true;
                ans++;
                queue.offer(new Node(nx, ny, dirIdx));
                allClean = false;
                break;
            }
            if (allClean) {
                int bx = x + goBack[d][0], by = y + goBack[d][1];
                if (posAvailable(bx, by)) {
                    queue.offer(new Node(bx, by, d));
                } else {
                    return;
                }
            }
        }
    }

    private static boolean posAvailable(int r, int c) {
        if (r <= 0 || r >= N - 1 || c <= 0 || c >= M - 1 || arr[r][c] == 1) return false;
        return true;
    }
}
