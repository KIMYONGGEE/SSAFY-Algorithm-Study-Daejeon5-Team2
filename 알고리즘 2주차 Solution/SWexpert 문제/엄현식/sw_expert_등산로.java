import java.util.Scanner;

public class sw_expert_등산로 {
   static int[][] maps;
   static int maxheight;
   static int[] dx = { -1, 0, 1, 0 };
   static int[] dy = { 0, 1, 0, -1 };
   static int max;
   static boolean[][] check;
   static int k;

//   static void recur2(int x, int y, boolean flag, int count) {
//      if (count > max) {
//         max = count;
//      }
//      check[x][y] = true;
//
//      for (int i = 0; i < dx.length; i++) {
//         int nx = x + dx[i];
//         int ny = y + dy[i];
//
//         if (nx < 0 || nx >= maps.length || ny < 0 || ny >= maps[0].length)
//            continue;
//         if (check[nx][ny])
//            continue;
//
//         if (maps[nx][ny] < maps[x][y]) {
//            recur2(nx, ny, flag, count + 1);
//         }
//
//         else if (maps[nx][ny] >= maps[x][y] && flag == false) {
//            for (int j = 1; j <= k; j++) {
//               if (maps[nx][ny] - j < maps[x][y]) {
//                  int tempsave = maps[nx][ny];
//                  maps[nx][ny] = maps[nx][ny] - j;
//                  recur2(nx, ny, true, count + 1);
//                  maps[nx][ny] = tempsave;
//               }
//            }
//
//         }
//
//      }
//      check[x][y] = false;
//
//   }

   static void recur(int x, int y, int k, int count, int value) {
      if (count > max) {
         max = count;
      }
      check[x][y] = true;

      for (int i = 0; i < dx.length; i++) {
         int nx = x + dx[i];
         int ny = y + dy[i];
         if (nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length && maps[nx][ny] < maps[x][y]
               && check[nx][ny] == false) {

            recur(nx, ny, k, count + 1, maps[nx][ny]);

         }
         if (nx >= 0 && nx < maps.length && ny >= 0 && ny < maps[0].length && k > 0 && maps[nx][ny] - k < maps[x][y]
               && check[nx][ny] == false) {
            // System.out.println(nx+" "+ny +"힌트");
            for (int j = 1; j <= k; j++) {
               if (maps[nx][ny] - j < maps[x][y]) {
                  int save = maps[nx][ny];
                  maps[nx][ny] = maps[nx][ny] - j;
                  recur(nx, ny, 0, count + 1, maps[nx][ny] - k);
                  maps[nx][ny] = save;
               }
            }
         }
      }
      check[x][y] = false;

   }

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int tc = sc.nextInt();
      for (int t = 1; t <= tc; t++) {
         int N = sc.nextInt();
         int K = sc.nextInt();
         max = 0;
         maxheight = 0;
         check = new boolean[N][N];
         maps = new int[N][N];
         k = K;
         for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
               maps[i][j] = sc.nextInt();
               if (maps[i][j] > maxheight) {
                  maxheight = maps[i][j];
               }
            }
         }
         for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
               if (maps[i][j] == maxheight) {
                  check = new boolean[N][N];
                  //recur2(i, j, false, 1);
                  recur(i,j,K,1,maps[i][j]);
               }
            }
         }
         System.out.printf("#%d %d", t, max);
         System.out.println();
      }
   }
}