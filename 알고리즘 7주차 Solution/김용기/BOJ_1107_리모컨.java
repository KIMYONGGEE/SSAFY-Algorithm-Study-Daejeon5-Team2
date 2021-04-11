package 스터디7주차;

import java.util.Scanner;

/**
 * BOJ_1107_리모컨
 */
public class BOJ_1107_리모컨 {
    static int data;
    static int N;
    static boolean[] deny = new boolean[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        data = sc.nextInt();
        N = sc.nextInt();
        int result1 = Math.abs(100 - data);
        int result2 = 0;

        for (int i = 0; i < N; i++) {
            int idx = sc.nextInt();
            deny[idx] = true;
        }

        result2 = calc(result1);

        System.out.println(Math.min(result1, result2));
    }

    static int calc(int result1) {
        int ans =result1;

        int lenght = 0;
        for (int i = 0; i <= 500000 * 2; i++) {

            lenght = isBroken(i);
            if (lenght == 0)
                continue;
            ans = Math.min(ans, Math.abs(data - i) + lenght);
        }
        return ans;
    }

    static int isBroken(int num) {
        if (num == 0)
            return (deny[num] ? 0 : 1);

        int len = 0;
        while (num > 0) {
            if (deny[num % 10])
                return 0;
            num /= 10;
            len++;
        }
        return len;
    }

}