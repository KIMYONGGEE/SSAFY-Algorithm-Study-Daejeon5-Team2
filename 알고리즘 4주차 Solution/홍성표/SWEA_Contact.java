import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Set<Integer>[] adjList = new Set[101];
    static int N, start, maxNum;
 
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
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        start = Integer.parseInt(input[1]);
        maxNum = 0;
 
        for (int i = 1; i <= 100; i++) {
            adjList[i] = new HashSet<>();
        }
 
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < N; i += 2) {
            adjList[Integer.parseInt(line[i])].add(Integer.parseInt(line[i + 1]));
        }
 
        bfs(start);
        return maxNum;
    }
 
    private static void bfs(int start) {
 
        int[] visited = new int[101];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int time = 1;
        visited[start] = time;
        while (!q.isEmpty()) {
            time++;
 
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int cur = q.poll();
                for (Integer next : adjList[cur]) {
                    if (visited[next] != 0) continue;
                    visited[next] = time;
                    q.add(next);
                }
            }
        }
 
        for (int i = 100; i >= 0; i--) {
            if (visited[i] == time-1) {
                maxNum = i;
                break;
            }
        }
    }
}