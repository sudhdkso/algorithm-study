import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	private static Set<Integer> woks = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] dp = new int[N+1];
		int[] m = new int[M];

		st = new StringTokenizer(br.readLine(), " ");

		for(int i=0;i<M;i++){
			m[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(m);

		Arrays.fill(dp, Integer.MAX_VALUE-1);

		dfs(m, new boolean[M], 0,0,2);
		for(int wok: woks){
			if(wok <= N){
				dp[wok] = 1;
			}
		}

		for(int wok : woks){
			for(int i=1;i<=N;i++){
				if(i-wok >= 0){
					dp[i] = Math.min(dp[i-wok]+1, dp[i]);
				}
			}
		}
		int result = dp[N] == Integer.MAX_VALUE-1 ? -1 : dp[N];
		System.out.println(result);
	}

	private static void dfs(int[] m, boolean[] visited, int start, int sum, int k){
		if(k < 0) return;
		if(k >= 0){
			woks.add(sum);
		}


		for(int i=start;i<m.length;i++){
			if(!visited[i]){
				visited[i] = true;
				dfs(m, visited, i+1, sum+m[i],k-1);
				visited[i] = false;
			}
		}
	}
}