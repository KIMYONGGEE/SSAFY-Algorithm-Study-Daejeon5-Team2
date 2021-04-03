package 스터디6주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ_1199_오일러회로
 */
public class BOJ_1199_오일러회로_인접리스트 {
    static int N;
    static int pathNum;
    // static int[][] data;
    static ArrayList<ArrayList<Integer>> data = new <ArrayList<Integer>>ArrayList();
    static StringBuilder ans = new StringBuilder();
    static boolean check;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // data = new int[N][N];
        // for (int i = 0; i < N; i++) {
        // StringTokenizer st = new StringTokenizer(br.readLine().trim());
        // for (int j = 0; j < N; j++) {
        // data[i][j] = Integer.parseInt(st.nextToken());
        // if(data[i][j]!=0)
        // pathNum += data[i][j];
        // }
        // }

        for (int i = 0; i < N; i++) {
            data.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp != 0) {
                    pathNum += temp;
                    for (int k = 0; k < temp; k++) {
                        data.get(i).add(j);
                    }
                }
            }
        }

        // for (int i = 0; i < data.size(); i++) {
        //     for (int j = 0; j < data.get(i).size(); j++) {
        //         System.out.print(data.get(i).get(j) + " ");
        //     }
        //     System.out.println();
        // }


        for (int i = 0; i < 1; i++) {
            StringBuilder tempans = new StringBuilder();
            tempans.append(i + 1);
            tempans.append(" ");

            DFS(i, 0, i, tempans);

            if (check) {
                System.out.println(ans);
                break;
            }

        }
        if (ans.length() == 0)
            System.out.println(-1);

    }

    static void DFS(int node, int cnt, int startnode, StringBuilder tempans) {
        System.out.println("------node in--------");
        System.out.println(node+", "+ cnt+", "+ startnode+", "+ tempans);

        for (int i = 0; i < data.size(); i++) {
             for (int j = 0; j < data.get(i).size(); j++) {
                System.out.print(data.get(i).get(j) + " ");
            }
            System.out.println();
        }
        if (cnt == pathNum / 2) {
            if (node == startnode) {
                ans = tempans;
                check = true;
            }
            return;
        }
        for (int i = 0; i < data.get(node).size(); i++) {
            int endNode = data.get(node).get(i);

            data.get(node).remove(i);
            data.get(endNode).remove((Integer) node);

            // tempans += Integer.toString(endNode + 1) + " ";
            tempans.append(endNode + 1);
            tempans.append(" ");

            DFS(endNode, cnt + 1, startnode, tempans);

            data.get(node).add(i);
            data.get(endNode).add((Integer) node);

        }
    }
}