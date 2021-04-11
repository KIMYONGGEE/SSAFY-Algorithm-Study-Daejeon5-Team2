package 스터디7주차;

import java.util.Scanner;

public class BOJ_6198_옥상정원꾸미기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] data = new int[N];
        long ans =0 ;

        for (int i = 0; i < N; i++) {
            data[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            long tempans=0;
            if(i == N-1){ break;}
            for (int j = i+1; j < N; j++) {
                if(data[i]>data[j])
                    tempans++;
                else break;
            }
            ans += tempans;
        }
        System.out.println(ans);
    }
}
