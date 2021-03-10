import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, macCnt;
    static int[] cache;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        // 대각선 배열 - 해당 대각선에 비숍이 놓인 x 좌표 값을 저장
        cache = new int[2 * N - 1];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }

        putBishop(0, 0);
        System.out.println(macCnt);
    }

    private static void putBishop(int idx, int cnt) {
        // 마지막 대각선에 도착했을 경우 최대값 갱신
        if (idx == (2 * N) - 1) {
            if (macCnt < cnt) macCnt = cnt;
            return;
        }

        // 현재 대각선에 비숍을 하나라도 놓을 수 있는지 체크
        boolean isExist = false;
        for (int i = 0; i <= idx; i++) {
            // 대각선에 해당하는 좌표에 비숍을 넣을 수 있는지 판단
            if (i >= N || idx - i >= N || map[i][idx - i] == 0 || !isAvailable(i, idx - i, idx)) continue;
            
            //넣을 수 있으면 해당 좌표의 x좌표로 값을 갱신하고 다음 대각선으로 진행
            cache[idx] = i;
            putBishop(idx + 1, cnt + 1);
            isExist = true;
        }

        // 비숍을 하나도 못 놓은 경우
        if (!isExist) {
            // 현재 대각선은 놓아진 비숍이 없음을 저장
            cache[idx] = -1;
            putBishop(idx + 1, cnt);
        }
    }

    // 반대 방향 대각선에 비숍이 존재하는지 확인
    private static boolean isAvailable(int x, int y, int idx) {
        //x좌표가 1 감소할 때마다 대각선의 index는 2씩 감소
        while(idx >= 2 && x >= 1 && y >= 1) {
            idx -= 2;
            x -= 1;
            y -= 1;

            // 비숍이 존재하면 놓을 수 없음.
            if (cache[idx] == x) return false;
        }

        return true;
    }
}