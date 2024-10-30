import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 2293번 동전1
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine()," ");

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] coins = new int[N];
		long[] dp = new long[K+1];

		for(int i=0;i<N;i++){
			coins[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(coins);
		
		dp[0] = 1;
		for(int coin : coins){
			for(int i=coin;i<=K;i++){
				dp[i] += dp[i-coin];

			}
		}

		System.out.println(dp[K]);
	}
}