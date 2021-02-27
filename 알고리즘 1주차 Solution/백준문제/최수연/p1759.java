import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class p1759 {
    static char[] alpha;
    static int L;
    static int C;
    static char[] idx;
    static PriorityQueue<String> pq = new PriorityQueue<>();
    static ArrayList<Character> vowels = new ArrayList<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        idx = new char[L];
        alpha = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alpha); //오름차순 출력을 위해 정렬
        combi(0, 0);

    }

    private static void combi(int cnt, int st) {
        if (cnt == L) {
            StringBuilder sb = new StringBuilder();
            int vowelCnt = 0, conCnt = 0;
            for (int i = 0; i < L; i++) {
                if(vowels.contains(idx[i])) vowelCnt++;
                else conCnt++;
                sb.append(idx[i]);
            }
            //조건확인  
            if(vowelCnt < 1 || conCnt < 2) return;
            System.out.println(sb);
        } else {
            for (int i = st; i < C; i++) {
                idx[cnt] = alpha[i];
                combi(cnt + 1, i + 1);
            }
        }
    }
}
