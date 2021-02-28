package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static boolean[][] visited;
    static int N, K, max;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
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

    private static int getResult() throws IOException {
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        map = new int[N][N];
        int highest = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if (highest < map[i][j]) highest = map[i][j];
            }
        }

        max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N ; j++) {
                if (map[i][j] != highest) continue;
                visited = new boolean[N][N];
                visited[i][j] = true;
                dfs(i,j,1,false);
            }
        }
        return max;
    }

    private static void dfs(int x, int y, int dist, boolean used) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            // 유효하지 않음 범위이거나 이미 방문한 곳이면 continue
            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || visited[nextX][nextY]) continue;
            // 다음 장소의 높이가 현재 높이보다 크거나 같은 경우
            if (map[x][y] <= map[nextX][nextY]) {
                // 공사를 이미 했거나, 최대 공사 높이(K)보다 두 높이의 차이가 크면 공사 불가능
                if (used || map[nextX][nextY] - map[x][y] + 1 > K) continue;
                // 다음 높이를 현재 높이보다 1 작은 높이로 변경
                int temp = map[nextX][nextY];
                map[nextX][nextY] = map[x][y] - 1;
                visited[nextX][nextY] = true;

                dfs(nextX, nextY, dist + 1, true);

                map[nextX][nextY] = temp;
                visited[nextX][nextY] = false;
            } else {    // 다음 장소의 높이가 현재 높이보다 작은 경우
                visited[nextX][nextY] = true;
                dfs(nextX, nextY, dist + 1,used);
                visited[nextX][nextY] = false;
            }
        }

        max = Math.max(max, dist);
    }
}