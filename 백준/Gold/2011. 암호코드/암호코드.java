import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	private static int MOD = 1000000;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String S = br.readLine();
		int N = S.length();
		long[] dp = new long[N+1];
		if(S.charAt(0) == '0' || S.indexOf("00") >= 0){
			dp[N] = 0;
		}
		else {
			dp[0] = dp[1] = 1;

			for(int i=2;i<=S.length();i++){
				int now = Integer.parseInt(String.valueOf(S.charAt(i-1)));
				int prev = Integer.parseInt(String.valueOf(S.charAt(i-2)));

				int temp = prev*10 + now;
				if(now == 0){
					if(prev == 1 || prev == 2){
						dp[i] = dp[i-2]%MOD;
					}
					else{
						dp[N] = 0;
						break;
					}
				}
				else {
					if(prev == 0){
						dp[i] = dp[i-1] % MOD;
					}
					else if (temp >= 1 && temp <= 26) {
						dp[i] = (dp[i-1] + dp[i - 2]) % MOD;
					} else {
						dp[i] = dp[i - 1] % MOD;
					}
				}
			}
		}
		bw.write(dp[N]+"\n");
		bw.flush();
	}
}