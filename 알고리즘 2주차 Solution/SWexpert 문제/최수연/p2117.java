import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2117 {
    static int N, M;
    static int[][] city;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            city = new int[N][N];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    city[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = 1;
            for(int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    servicePrice(i, j);
                }
            }

            System.out.println("#"+t+" "+ans);
        }
    }

    private static void servicePrice(int x, int y) {
        for(int k = 0; k <= N+1; k++){
            int homeCnt = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    int distX = Math.abs(i - x);
                    int distY = Math.abs(j - y);
                    if(distX + distY < k && city[i][j] == 1){
                        homeCnt++;
                    }
                }
            }
            if(((M*homeCnt) - (k*k + (k-1)*(k-1))) >= 0){ //이익이 남을때
                ans = Math.max(homeCnt, ans); //더 많은 집을 갖는 값으로 업데이트
            }
        }

    }
}
