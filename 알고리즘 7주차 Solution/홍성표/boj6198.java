import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution6198 {
    static class Pair {
        int num;
        int cnt;

        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] floor = new int[N];
        for (int i = 0; i < N; i++) {
            floor[i] = Integer.parseInt(br.readLine());
        }

        long result = 0;
        Stack<Pair> prefix = new Stack<>();
        prefix.add(new Pair(floor[N - 1], 1));
        for (int i = N - 2; i >= 0; i--) {
            if (floor[i] <= prefix.peek().num) {
                prefix.add(new Pair(floor[i], 1));
            } else {
                int totalCnt = 0;
                while(!prefix.isEmpty()) {
                    Pair cur = prefix.pop();
                    if (floor[i] <= cur.num) {
                        prefix.add(cur);
                        break;
                    }
                    totalCnt += cur.cnt;
                }
                result += totalCnt;
                prefix.add(new Pair(floor[i], totalCnt + 1));
            }
        }
        System.out.println(result);
    }
}