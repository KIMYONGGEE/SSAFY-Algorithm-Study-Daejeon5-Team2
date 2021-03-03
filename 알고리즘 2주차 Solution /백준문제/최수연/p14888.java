import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p14888 {
    static int N;
    static int[] nums;
    static int[] operator;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static boolean[] isSelected;
    static int[] op;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        operator = new int[N-1];
        isSelected = new boolean[N-1];
        op = new int[N-1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int idx = 0;
        for(int i = 0; i < 4; i++){
            int operatorCnt = Integer.parseInt(st.nextToken());
            for(int j = 0; j < operatorCnt; j++){
                operator[idx++] = i;
            }
        }

        //1. 연산 경우의 수 구하기
        perm(0);
        System.out.println(max+"\n"+min);
    }

    private static void perm(int cnt) {
        if(cnt == N-1){
            calc();
        }else{
            for(int i = 0; i < N-1; i++){
                if(isSelected[i]) continue;
                isSelected[i] = true;
                op[cnt] = operator[i];
                perm(cnt+1);
                isSelected[i] = false;
            }
        }
    }

    private static void calc() {
        int tmp = nums[0];
        for(int i = 0; i < N-1; i++){
            if(op[i] == 0){ //더하기
                tmp += nums[i+1];
            }else if(op[i] == 1){
                tmp -= nums[i+1];
            }else if(op[i] == 2){
                tmp *= nums[i+1];
            }else if(op[i] == 3){
                if(tmp < 0){
                    tmp = -(Math.abs(tmp)/nums[i+1]);
                }else{
                    tmp /= nums[i+1];
                }
            }
        }
        max = Math.max(tmp, max);
        min = Math.min(tmp, min);
    }
}
