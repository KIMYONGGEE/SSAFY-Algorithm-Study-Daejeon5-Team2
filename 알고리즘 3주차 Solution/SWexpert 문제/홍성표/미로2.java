import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static int startX, startY;
    static boolean isPossible;
 
    public static void main(String[] args) throws IOException {
        int T = 10;
        int[] result = new int[T];
        for (int i = 0; i < T; i++) {
            result[i] = getResult();
        }
 
        for (int i = 0; i < T; i++) {
            System.out.printf("#%d %d\n", i + 1, result[i]);
        }
    }
 
    private static int getResult() throws IOException {
        br.readLine();
        map = new int[100][100];
        isPossible = false;
        for (int i = 0; i < 100; i++) {
            String line = br.readLine();
            for (int j = 0; j < 100; j++) {
                map[i][j] = Character.getNumericValue(line.charAt(j));
                if (map[i][j] == 2) {
                    startX = i;
                    startY = j;
                }
            }
        }
 
        dfs(startX, startY);
        return (isPossible) ? 1 : 0;
    }
 
    private static void dfs(int x, int y) {
        if (map[x][y] == 3) {
            isPossible = true;
            return;
        }
 
        map[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int nX = x + dx[i];
            int nY = y + dy[i];
            if (nX < 0 || nY < 0 || nX >= 100 || nY >= 100 || map[nX][nY] == 1)  continue;
            dfs(nX, nY);
        }
    }
}