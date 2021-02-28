package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, maxBenefitCnt;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
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
        M = Integer.parseInt(input[1]);
        map = new int[N][N];
        int houseCnt = 0;

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
                if (map[i][j] == 1) houseCnt++;
            }
        }

        // k=1일 때 손해를 보지 않는 집의 개수는 1
        maxBenefitCnt = 1;

        // k = 2부터 탐색 시작
        int k = 2;
        while(true) {
            // 지도 상의 모든 집을 포함해도 손해가 발생하면 종료
            if ((houseCnt * M)- ((k*k) + ((k-1) * (k-1))) < 0) break;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited = new boolean[N][N];
                    // 집의 개수 및 손해 여부 탐색
                    int curCnt = getBenefitCnt(i, j, k);
                    maxBenefitCnt = Math.max(maxBenefitCnt, curCnt);
                }
            }
            k++;
        }
        return maxBenefitCnt;
    }

    //bfs
    private static int getBenefitCnt(int x, int y, int k) {
        int benefit = -((k*k) + ((k-1) * (k-1)));
        int cnt = 0;
        Queue<Pair> area = new LinkedList<>();
        area.add(new Pair(x,y));
        visited[x][y] = true;
        int depth = 0;
        // depth가 k일 떄까지 영역 확장
        while(++depth <= k && !area.isEmpty()) {
            int size = area.size();
            while(--size >= 0) {
                Pair cur = area.poll();
                // 현재 영역에 집이 있으면 이익 추가
                if (map[cur.x][cur.y] == 1) {
                    benefit += M;
                    cnt++;
                }
                // 다음 확장할 영역을 Queue에 삽입
                for (int i = 0; i < 4; i++) {
                    int nextX = cur.x + dx[i];
                    int nextY = cur.y + dy[i];
                    if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || visited[nextX][nextY]) continue;
                    visited[nextX][nextY] = true;
                    area.add(new Pair(nextX, nextY));
                }
            }
        }
        // 손해를 보는 경우에는 0, 손해를 보지 않으면 영역에 포함된 집의 개수 반환
        return (benefit >= 0) ? cnt : 0;
    }
}