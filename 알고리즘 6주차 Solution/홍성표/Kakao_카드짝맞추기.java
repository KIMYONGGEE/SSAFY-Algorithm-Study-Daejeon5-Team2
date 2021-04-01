import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class SolutionKakao {
    static int[][] map = new int[4][4];
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int[] flag;
    static int startX, startY;

    public static void main(String[] args) {
        int[][] board = {{3, 0, 0, 2}, {0, 0, 1, 0}, {0, 1, 0, 0}, {2, 0, 0, 3}};
        int r = 0;
        int c = 1;
        System.out.println(solution(board, r, c));
    }

    private static int solution(int[][] board, int r, int c) {
        initFlag(board);
        int minTime = Integer.MAX_VALUE;
        do {
            minTime = Math.min(minTime, getTime(r, c, board));
        } while (nextPermutation());
        return minTime;
    }

    private static int getTime(int r, int c, int[][] board) {
        int time = 0;
        startX = r;
        startY = c;
        deepCopy(board);
        for (int i = 0; i < flag.length; i++) {
            int target = flag[i];
            time += bfs(target);
            time += bfs(target);
        }
        return time;
    }

    private static int bfs(int target) {
        // 현재 위치가 target과 같은 경우
        if (map[startX][startY] == target) {
            map[startX][startY] = 0;
            return 1;
        }

        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        q.add(new Pair(startX, startY));
        int time = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                Pair cur = q.poll();

                // 1칸
                for (int i = 0; i < 4; i++) {
                    int nX = cur.x + dx[i];
                    int nY = cur.y + dy[i];
                    if (nX < 0 || nY < 0 || nX >= 4 || nY >= 4 || visited[nX][nY]) continue;
                    if (map[nX][nY] == target) {
                        startX = nX;
                        startY = nY;
                        map[nX][nY] = 0;
                        return time + 1;
                    }

                    visited[nX][nY] = true;
                    q.add(new Pair(nX, nY));
                }

                //ctrl
                for (int i = 0; i < 4; i++) {
                    int nX = cur.x;
                    int nY = cur.y;
                    while (true) {
                        nX += dx[i];
                        nY += dy[i];
                        if (nX < 0 || nY < 0 || nX >= 4 || nY >= 4) {
                            nX -= dx[i];
                            nY -= dy[i];
                            break;
                        }
                        if (map[nX][nY] != 0) break;
                    }

                    if (visited[nX][nY]) continue;
                    if (map[nX][nY] == target) {
                        startX = nX;
                        startY = nY;
                        map[nX][nY] = 0;
                        return time + 1;
                    }
                    visited[nX][nY] = true;
                    q.add(new Pair(nX, nY));
                }
            }
            time++;
        }
        return 0;
    }

    private static void deepCopy(int[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = board[i][j];
            }
        }
    }

    private static void initFlag(int[][] board) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0 || set.contains(board[i][j])) continue;
                set.add(board[i][j]);
            }
        }

        flag = new int[set.size()];
        int idx = 0;
        for (Integer i : set) {
            flag[idx++] = i;
        }

        Arrays.sort(flag);
    }

    private static boolean nextPermutation() {
        int srcIdx = flag.length;
        while (--srcIdx > 0) {
            if (flag[srcIdx] > flag[srcIdx - 1]) break;
        }
        if (--srcIdx == -1) return false;

        int destIdx = flag.length;
        while (--destIdx >= 0) {
            if (flag[srcIdx] >= flag[destIdx]) continue;
            swap(srcIdx, destIdx);
            break;
        }

        destIdx = flag.length;
        while (++srcIdx <= --destIdx) swap(srcIdx, destIdx);
        return true;
    }

    private static void swap(int srcIdx, int destIdx) {
        int temp = flag[srcIdx];
        flag[srcIdx] = flag[destIdx];
        flag[destIdx] = temp;
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