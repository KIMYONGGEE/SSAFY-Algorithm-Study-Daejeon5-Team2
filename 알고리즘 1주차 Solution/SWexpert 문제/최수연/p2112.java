import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class p2112 {
    static int D, W, K;
    static int[][] film;
    static int minInput;
    static ArrayList<Integer> selected;
    static boolean[] selectedA;
    static int[] idx;
    public static void main(String[] args) throws IOException {
        //1. 현재 모든 셀에 대해 성능 테스트를 통과를 할 수 있는지 확인한다. => 조합 
        //2. 못하는 경우 순열을 부분집합을 통해 모든 가능성을 확인한다(공집합 제외하기) => powerSet

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <=T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken()); //두께
            W = Integer.parseInt(st.nextToken()); //가로크기
            K = Integer.parseInt(st.nextToken()); //합격기준
            film = new int[D][W];
//            isSelected = new boolean[D];
            for(int i = 0; i < D; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < W; j++){
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //1.
            boolean isPassed = inspection(film);
            if(isPassed){ //(1).바로 통과되는 경우
                System.out.println("#"+t+" "+ 0);
                continue;
            }

            //2.
            minInput = K+1; //약물투입횟수
            for(int i = 1; i <= K; i++){
                idx = new int[i]; //선별된 층
                combi(0, 0, i);
                if(minInput < K+1) break;
            }
            System.out.println("#"+t+" "+minInput);
        }

    }

    private static void combi(int cnt, int st, int pick) {
        if(cnt == pick){ //부분집합 완성. 변경할 막이 선별됨.
            selected = new ArrayList<>(); //선별된 층
            for(int i = 0; i < pick; i++){
                selected.add(idx[i]);
            }
//            if(idx.size() == 0) return; // 아무것도 선별 안된 경우
            //변경할 특성 선택 => 부분집합(공집합 포함 가능)
            selectedA = new boolean[idx.length];
            selectChemical(0);
        }else{
            for(int i = st; i < D; i++){
                idx[cnt] = i;
                combi(cnt + 1, i + 1, pick);
            }
        }
    }

    //변경할 층을 어떤 타입으로 변경할지 결정
    private static void selectChemical(int cnt){
        if(cnt == idx.length){
            //배열 복사
            int[][] tempFilm = new int[D][W];
            for(int i = 0; i < D; i++){
                if(selected.contains(i)){ // 선별된 층에서
                    if(selectedA[selected.indexOf(i)]){ //A 변형에 해당
                        for(int j = 0; j < W; j++){
                            tempFilm[i][j] = 0;
                        }
                    }else{
                        for(int j = 0; j < W; j++){ //B 변형에 해당
                            tempFilm[i][j] = 1;
                        }
                    }
                }else{
                    for(int j = 0; j < W; j++){
                        tempFilm[i][j] = film[i][j];
                    }
                }
            }
            if(inspection(tempFilm)){
                minInput = Math.min(minInput, idx.length);
            }
        }else{
            selectedA[cnt] = true;
            selectChemical(cnt + 1);
            selectedA[cnt] = false;
            selectChemical(cnt + 1);
        }
    }

    //성능검사에 통과하는지 확인
    private static boolean inspection(int[][] arr) {
        for(int i = 0; i < W; i++){
            int maxCnt = 1, cnt = 1; //최대 연속 카운
            for(int j = 0; j < D - 1; j++){
                if(arr[j][i] != arr[j+1][i]){
                    maxCnt = Math.max(maxCnt, cnt);
                    cnt = 1;
                }else{
                    cnt++;
                    if(cnt >= K) break;
                }
            }
            maxCnt = Math.max(maxCnt, cnt); //마지막 원소
            if(maxCnt < K) return false;
        }
        return true;
    }
}
