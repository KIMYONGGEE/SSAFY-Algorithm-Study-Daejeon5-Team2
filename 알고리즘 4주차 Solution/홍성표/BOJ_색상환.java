import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        if (K == 1) {
            System.out.println(N);
            return;
        }
        if (K  > N/2) {
            System.out.println(0);
            return;
        }

        long ret = (getColorCnt(2, N-1, K) + getColorCnt(3, N-1, K -1) + getColorCnt(2,N-2, K-1));
        System.out.println(ret % 1000000003);
    }

    private static long getColorCnt(int start, int end, int k) {
        long[][][] cache = new long[1001][1001][2];
        for (int i = start-1; i <= end ; i++) {
            cache[0][i][0] = 0;
            cache[0][i][1] = 1;
        }

        for (int i = 1; i <= k; i++) {
            for (int j = start; j <= end; j++) {
                cache[i][j][0] = cache[i-1][j-1][1] % 1000000003;
                cache[i][j][1] = (cache[i][j-1][0] + cache[i][j-1][1]) % 1000000003;
            }
        }

        return (cache[k][end][0] + cache[k][end][1]) % 1000000003;
    }
}