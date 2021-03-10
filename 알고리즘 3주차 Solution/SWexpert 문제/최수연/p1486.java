import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p1486 {
    static int N, B;
    static int[] staffs;
    static boolean[] isSelected;
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            ans = Integer.MAX_VALUE;
            staffs = new int[N];
            isSelected = new boolean[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                staffs[i] = Integer.parseInt(st.nextToken());
            }
            powerSet(0, 0);
            System.out.println("#" + t + " "+ (ans - B));
        }
    }

    private static void powerSet(int cnt, int sum) {
        if (cnt == N) {
            if (sum >= B && ans > sum) {
                ans = sum;
            }
        } else {
            if(sum > ans) return;
            isSelected[cnt] = true;
            powerSet(cnt + 1, sum + staffs[cnt]);
            isSelected[cnt] = false;
            powerSet(cnt + 1, sum);

        }
    }
}
