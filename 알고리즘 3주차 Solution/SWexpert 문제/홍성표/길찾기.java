import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] adj1 = new int[100];
    static int[] adj2 = new int[100];
    static boolean isReached;
    static boolean[] visited;
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
 
    static int getResult() throws IOException {
        setup();
 
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[1]);
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < line.length; i += 2) {
            int src = Integer.parseInt(line[i]);
            int dest = Integer.parseInt(line[i+1]);
            if (adj1[src] == -1)
                adj1[src] = dest;
            else
                adj2[src] = dest;
        }
        dfs(0);
        return (isReached) ? 1 : 0;
    }
 
    private static void dfs(int i) {
        if (i == 99) {
            isReached = true;
            return;
        }
 
        visited[i] = true;
        if (adj1[i] != -1 && !visited[adj1[i]])
            dfs(adj1[i]);
        if (adj2[i] != -1 && !visited[adj2[i]])
            dfs(adj2[i]);
    }
 
    private static void setup() {
        isReached = false;
        visited = new boolean[100];
        for (int i = 0; i < 100; i++) {
            adj1[i] = -1;
            adj2[i] = -1;
        }
    }
}