import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p6603 {
    static int N;
    static int[] S;
    static int[] lotto;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
            if(N == 0) {
                break;
            }
            S = new int[N];
            lotto = new int[6];
            for (int i = 0; i < N; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }
            combi(0, 0);
            System.out.println();
        }
    }

    private static void combi(int cnt, int st) {
        if (cnt == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(lotto[i] + " ");
            }
            System.out.println();
        }else{
            for (int i = st; i < N; i++) {
                lotto[cnt] = S[i];
                combi(cnt + 1, i + 1);
            }
        }
    }
}
