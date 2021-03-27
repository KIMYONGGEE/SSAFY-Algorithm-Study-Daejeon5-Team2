class Solution {
    static int[][] adj, paths;
    static final int INF = 40000001, NIL = -1;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        adj = new int[n+1][n+1];

        //initialize
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if(i != j) {
                    adj[i][j] = INF;                    
                }
            }
        }

        for (int i = 0; i < fares.length; i++) {
            int src = fares[i][0];
            int dest = fares[i][1];
            int weight = fares[i][2];
            adj[src][dest] = weight;
            adj[dest][src] = weight;
        }

        floydWashall(n);
        return getMinPayment(n, s, a, b);
    }

    public int getMinPayment(int n, int s, int a, int b) {
        int minPayment = adj[s][a] + adj[s][b];
        for (int i = 1; i <= n; i++) {
            int curPayment = adj[s][i] + adj[i][a] + adj[i][b];
            minPayment = Math.min(minPayment, curPayment);
        }
        return minPayment;
    }

    public  void floydWashall(int n) {
        for (int k = 1; k <= n ; k++) {
            for (int i = 1; i <= n ; i++) {
                for (int j = 1; j <= n ; j++) {
                    if (adj[i][j] <= adj[i][k] + adj[k][j]) continue;
                    adj[i][j] = adj[i][k] + adj[k][j];
                }
            }
        }
    }
}