package Algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int x;
    int y;
    ArrayList<Edge> rooms;

    public Edge(int x, int y) {
        this.x = x;
        this.y = y;
        this.rooms = new ArrayList<>();
    }
}

public class p11967 {
    static int N;
    static int M;
    static int[][] farm;
    static boolean[][] light;
    static boolean[][] vis;
    static ArrayList<Edge>[][] graph;
    static Queue<Edge> queue;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        farm = new int[N][N];
        light = new boolean[N][N];
        vis = new boolean[N][N];
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            graph[x][y].add(new Edge(a, b));
        }

        light[0][0] = true;
        bfs();

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (light[i][j]) cnt++;
            }
        }
        System.out.println(cnt);

    }

    private static void bfs() {
        queue = new LinkedList<>();
        queue.offer(new Edge(0, 0));
        vis[0][0] = true;

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            int x = cur.x;
            int y = cur.y;
            lightOn(x, y);
            for (int idx = 0; idx < 4; idx++) {
                int nx = x + dx[idx];
                int ny = y + dy[idx];
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (vis[nx][ny] || !light[nx][ny]) continue;

                queue.offer(new Edge(nx, ny));
                vis[nx][ny] = true;
            }
        }
    }

    private static void lightOn(int i, int j) {
        ArrayList<Edge> nxt = graph[i][j];
        for (Edge e : nxt) {
            int x = e.x;
            int y = e.y;
//            light[x][y] = true;
            if(!vis[x][y] && !light[x][y]) {
                light[x][y] = true;
                for (int idx = 0; idx < 4; idx++) {
                    int nx = x + dx[idx];
                    int ny = y + dy[idx];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                    if (vis[nx][ny]) {
                        vis[x][y] = true;
                        queue.offer(new Edge(x, y));
                    }
                }
            }
        }
    }
}
