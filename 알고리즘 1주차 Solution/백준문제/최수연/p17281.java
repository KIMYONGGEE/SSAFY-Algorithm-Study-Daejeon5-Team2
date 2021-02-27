import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p17281 {
    static boolean[] isSelected = new boolean[9];
    static int[] num = new int[8];
    static int[] serial = new int[9];

    static int innings;
    static int[][] inningResult;
    static int maxScore = 0;
    static int score = 0;

    public static void main(String[] args) throws IOException {
        //1.1번선수를 제외한 8명의 선수의 순열생성
        //2.모든 경우에 대한 득점 계산

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        innings = Integer.parseInt(br.readLine());
        inningResult = new int[innings][9];
        for(int i = 0; i < innings; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                inningResult[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //1.
        perm(0);

        System.out.println(maxScore);
    }

    private static void perm(int cnt) {
        if(cnt == 8){
            int idx = 0;
            for(int i = 0; i < 9; i++){
                if(i == 3){
                    serial[i] = 0;
                }else{
                    serial[i] = num[idx++];
                }
            }
            //2. 점수 계산
            calcScore();
        }else{
            for(int i = 1; i < 9; i++){
                if(isSelected[i]) continue;
                isSelected[i] = true;
                num[cnt] = i;
                perm(cnt+1);
                isSelected[i] = false;
            }
        }
    }

    //해당 경우에 대한 득점 계산
    private static void calcScore() {
        score = 0; //이번 경우의 점수
        int turn = 0; //현재 타자(serial)의 인덱스
        for(int i = 0; i < innings; i++){
            int out = 0; //현재 이닝의 아웃 수
            int game = 0; //이닝에서 경기 회차
            int[] bases = new int[3]; // 1,2,3루
            while(out < 3){ //각 이닝에서 9번의 경기를 진행함
                int forward = inningResult[i][serial[turn]]; //타의 결과에 따른 진루
                if(forward == 0) {
                    out++;
                }else{
                    bases = startRunning(bases, forward);
                }
                game++;
                turn++;
                if(turn >= 9) turn = 0;
            }
        }
        maxScore = Math.max(score, maxScore);
    }

    //
    private static int[] startRunning(int[] bases, int forward) {
        if(forward == 1){ //안타인 경우
            if(bases[2] == 1) score++;
            bases[2] = bases[1];
            bases[1] = bases[0];
            bases[0] = 1;
        }else if(forward == 2){ //2루타인 경우
            if(bases[2] == 1) score++;
            if(bases[1] == 1) score++;
            bases[2] = bases[0];
            bases[1] = 1;
            bases[0] = 0;
        }else if(forward == 3){ // 3루타인 경우
            for(int i = 0; i < 3; i++){
                if(bases[i] == 1) score++;
                bases[i] = 0;
            }
            bases[2] = 1;
        }else if(forward == 4){ //홈런
            for(int i = 0; i < 3; i++){
                if(bases[i] == 1) score++;
                bases[i] = 0;
            }
            score++;
        }
        return bases;
    }
}
