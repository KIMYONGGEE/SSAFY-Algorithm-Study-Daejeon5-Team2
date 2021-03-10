import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class p1219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t < 11; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int pair = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            //인접리스트(그래프)생성
            ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
            //그래프 초기화
            for (int i = 0; i < 100; i++) {
                adj.add(new ArrayList<Integer>());
            }
            //인접 정점 정보 입력
            for (int i = 0; i < pair; i++) {
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                adj.get(u).add(v);
            }
            int ans = 0;
            //BFS를 사용하여 탐색
            Queue<Integer> queue = new LinkedList<Integer>();
            boolean[] vis = new boolean[100];
            queue.add(0);
            vis[0] = true;
            while (!queue.isEmpty() && ans == 0){
                int v = queue.poll();
                for(int i = 0; i < adj.get(v).size(); i++){
                    int nxt = adj.get(v).get(i);
                    if(nxt == 99){
                        ans = 1;
                        break;
                    }
                    if(!vis[nxt]){
                        queue.add(nxt);
                        vis[nxt] = true;
                    }
                }
            }

            System.out.println("#" + idx + " " + ans);
        }
    }
}
