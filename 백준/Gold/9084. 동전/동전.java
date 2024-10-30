import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 9084번 동전
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// T 테스트 케이서
		// N 코인 종류
		// M 만들어야 할 금액

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] coins = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			for (int i = 0; i < N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}

			int M = Integer.parseInt(br.readLine());

			int[] dp = new int[M+1];

			dp[0] = 1;

			for(int coin : coins){
				for(int i=0;i<=M;i++){
					if(i-coin >= 0){
						dp[i] += dp[i-coin];
					}
				}
			}

			System.out.println(dp[M]);
		}

	}
}