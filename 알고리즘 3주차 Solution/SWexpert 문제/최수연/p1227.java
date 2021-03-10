import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class point {
    int x;
    int y;

    public point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class p1227 {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t = 1; t < 11; t++) {
            int idx = Integer.parseInt(br.readLine());
            int[][] maze = new int[100][100];
            int[][] visited = new int[100][100];
            Queue<point> queue = new LinkedList<point>();
            for (int i = 0; i < 100; i++) {
                String[] str = br.readLine().split("");
                for (int j = 0; j < 100; j++) {
                    maze[i][j] = Integer.parseInt(str[j]);
                    if (maze[i][j] == 2) {
                        queue.offer(new point(i, j));
                        visited[i][j] = 1;
                    }
                }
            }
            if (bfs(queue, maze, visited) == 1) {
                System.out.println("#" + t + " " + 1);
            } else {
                System.out.println("#" + t + " " + 0);
            }


        }
    }

    private static int bfs(Queue<point> queue, int[][] maze, int[][] vis) {
        while(! queue.isEmpty()){
            point tmp = queue.remove();
            int x = tmp.x;
            int y = tmp.y;
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || nx >= 100 || ny < 0 || ny >= 100) continue;
                if(maze[nx][ny] == 3) return 1;
                if(maze[nx][ny] == 0 && vis[nx][ny] == 0){
                    vis[nx][ny] = 1;
                    queue.add(new point(nx, ny));
                }
            }

        }
        return 0;
    }
}