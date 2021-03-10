import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p8382 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int stX = Integer.parseInt(st.nextToken());
            int stY = Integer.parseInt(st.nextToken());
            int desX = Integer.parseInt(st.nextToken());
            int desY = Integer.parseInt(st.nextToken());

            //계산
            int distX = Math.abs(stX - desX);
            int distY = Math.abs(stY - desY);
            int ans = Math.min(distX, distY) * 2;
            if (distX != distY) {
                distX -= (ans / 2);
                distY -= (ans / 2);
                int left = Math.max(distX, distY);
                if (left % 2 == 0) {
                    ans += left * 2;
                } else {
                    ans += (left * 2 - 1);
                }
            }

            System.out.println("#" + t + " " + ans);
        }
    }
}
