import java.util.*;

class Solution {
    int[][] map;
    int N, M, totalHole;
    public boolean solution(int[][] key, int[][] lock) {
        N = lock.length;
        M = key.length;
        map = new int[N * 3][N * 3];
        initMap(lock);
        for(int i = N - M + 1; i < 2 * N; i++) {
            for(int j = N - M + 1; j < 2 * N; j++) {
                int cnt = 0;
                while(++cnt <= 4) {
                    key = rotateMap(key);
                    if (!isSolved(i, j, key)) continue;
                    return true;                    
                }
            }
        }
        return false;
    }
    
    public void initMap(int[][] lock) {
        for(int i = 0; i < N * 3; i++) {
            Arrays.fill(map[i], -1);
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[N + i][N + j] = lock[i][j];
                if (lock[i][j] == 0) totalHole++;
            }
        }
    }
    
    
    public boolean isSolved(int x, int y, int[][] key) {
        int fitCnt = 0;
         for(int i = 0; i < M; i++) {
            for(int j = 0; j < M; j++) {
                if (key[i][j] == 1 && map[x + i][y + j] == 1) return false;
                if (key[i][j] == 1 && map[x + i][ y + j] == 0) fitCnt++;
            }   
         }
        
        return totalHole == fitCnt;
    } 
    
    public int[][] rotateMap(int[][] origin) {
        int[][] rotated = new int[M][M];
        for(int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                rotated[j][M - i - 1] = origin[i][j];
            }
        }
        return rotated;
    }
}