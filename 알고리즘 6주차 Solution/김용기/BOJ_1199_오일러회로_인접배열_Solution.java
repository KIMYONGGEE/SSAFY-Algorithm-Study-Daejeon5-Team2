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
public class BOJ_1199_오일러회로_인접배열_Solution {
    static int N;
    static int[][] data;
    static StringBuilder ans = new StringBuilder();
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        data = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if (checkAvail()) {
            DFS(0);
            System.out.println(ans);

        } else {
            System.out.println(-1);
        }


    }

    static void DFS(int node) {
        for (int i = 0; i < N; i++) {
            while (data[node][i] != 0) {
                data[node][i]--;
                data[i][node]--;
                DFS(i);
            }
        }
        ans.append(node + 1);
        ans.append(" ");
    }
    static boolean checkAvail(){
        for (int i = 0; i < N; i++) {
            int tempLenght =0 ;
            for (int j = 0; j < N; j++) {
                tempLenght += data[i][j];
            }
            if(tempLenght%2 != 0)
                return false;
        }

        return true;

    }
        
}