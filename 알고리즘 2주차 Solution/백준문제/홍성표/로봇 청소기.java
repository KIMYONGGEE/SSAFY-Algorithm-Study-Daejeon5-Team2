import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static int N, M, cleanCnt = 1;
    public static void main(String[] args) throws IOException {
        String[] input1 = br.readLine().split(" ");
        N = Integer.parseInt(input1[0]);
        M = Integer.parseInt(input1[1]);

        String[] input2 = br.readLine().split(" ");
        int startX = Integer.parseInt(input2[0]);
        int startY = Integer.parseInt(input2[1]);
        int direction = Integer.parseInt(input2[2]);

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
        // 시작 위치 청소
        map[startX][startY] = 2;
        clean(startX, startY, direction);
        System.out.println(cleanCnt);
    }

    private static void clean(int x, int y, int direction) {
        boolean isContinue = true;
        // 2-4의 경우가 나오기 전까지 탐색 진행
        while (isContinue) {
            // 현재 위치에서 좌측을 순서대로 탐색
            for (int i = 0; i < 4; i++) {
                // 좌측 방향으로 갱신
                direction = (direction + 3) % 4;
                // 청소 가능한 경우 1번 수행 후, 새로운 2번으로 진행
                if (map[x + dx[direction]][y + dy[direction]] == 0) {
                    x += dx[direction];
                    y += dy[direction];
                    map[x][y] = 2;
                    cleanCnt += 1;
                    break;
                }

                // 네 방향 모두 청소가 불가능할 때
                if (i == 3) {
                    int backDirection = (direction + 2) % 4;
                    // 뒤에가 벽이면 종료 플래그 설정
                    if (map[x + dx[backDirection]][y + dy[backDirection]] == 1) {
                        isContinue = false;
                    } else {    // 뒤에가 벽이 아니면 후진
                        x += dx[backDirection];
                        y += dy[backDirection];
                    }
                }
            }
        }
    }
}