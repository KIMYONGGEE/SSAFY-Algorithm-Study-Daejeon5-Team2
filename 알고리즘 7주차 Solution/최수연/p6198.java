package Algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class p6198 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] building = new int[N];
        long[] seeing = new long[N];
        for (int i = 0; i < N; i++) {
            building[i] = Integer.parseInt(br.readLine());
        }
        Stack<Integer> stack = new Stack<>();
        long ans = 0;
        for (int i = building.length - 1; i >= 0; i--) {
            int tmp = 0;
            while (!stack.isEmpty() && building[stack.peek()] < building[i]) {
                tmp += seeing[stack.pop()];
                tmp += 1;
            }
            stack.add(i);
            seeing[i] = tmp;
            ans += tmp;
        }
        System.out.println(ans);
    }
}
