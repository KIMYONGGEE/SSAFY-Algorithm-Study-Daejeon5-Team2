import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution3075 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, p, q;
    static long INF = Long.MAX_VALUE /2;
    static long[][] map;
    static int[] people;
    public static void main(String[] args) throws IOException {
        ArrayList<long[]> result = new ArrayList<>();
        int T = Integer.parseInt(br.readLine());
        while(--T >= 0) {
            result.add(getResult());
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i)[0] + " " + result.get(i)[1]);
        }
    }

    private static long[] getResult() throws IOException {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        p = Integer.parseInt(input[1]);
        q = Integer.parseInt(input[2]);
        people = new int[n];
        map = new long[p + 1][p + 1];

        init();
        for (int i = 0; i < n; i++) {
            people[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < q; i++) {
            String[] line = br.readLine().split(" ");
            int src = Integer.parseInt(line[0]);
            int dest = Integer.parseInt(line[1]);
            int weight = Integer.parseInt(line[2]);
            map[src][dest] = Math.min(weight, map[src][dest]);
            map[dest][src] = Math.min(weight, map[dest][src]);
        }

        floydWashall();
        return findMeetAstro();
    }

    private static void printMap() {
        for (int i = 1; i < p + 1; i++) {
            for (int j = 1; j < p + 1; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static long[] findMeetAstro() {
        long[] result = new long[2];
        long minDistance = Long.MAX_VALUE;
        long astroId = -1;

        for (int i = 1; i < p + 1; i++) {
            long distance = 0;
            boolean isConnected = true;
            for (int j = 0; j < n; j++) {
                if (map[i][people[j]] == INF) {
                    isConnected = false;
                    break;
                }
                distance += Math.pow(map[i][people[j]], 2);
            }


            if (!isConnected) continue;
            if (distance < minDistance) {
                minDistance = distance;
                astroId = i;
            }
        }

        result[0] = astroId;
        result[1] = minDistance;
        return result;
    }

    private static void floydWashall() {
        for (int k = 1; k < p + 1; k++) {
            for (int i = 1; i < p + 1; i++) {
                for (int j = 1; j < p + 1; j++) {
                    if (map[i][j] <= map[i][k] + map[k][j]) continue;
                    map[i][j] = map[i][k] + map[k][j];
                }
            }
        }
    }

    private static void init() {
        for (int i = 1; i < p + 1; i++) {
            for (int j = 1; j < p + 1; j++) {
                if (i == j)
                    map[i][j] = 0;
                else
                    map[i][j] = INF;
            }
        }
    }
}