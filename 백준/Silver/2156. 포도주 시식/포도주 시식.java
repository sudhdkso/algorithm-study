import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());

		int[] juice = new int[n];

		for (int i = 0; i < n; i++) {
			juice[i] = Integer.parseInt(br.readLine());
		}

		int[] dp = new int[n];
		dp[0] = juice[0];

		if (n > 1) {
			dp[1] = juice[0] + juice[1];
		}
		for (int i = 2; i < n; i++) {
			if(i == 2){
				dp[2] = Math.max(dp[1], Math.max(juice[0], juice[1]) + juice[2]);
				continue;
			}
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2], dp[i - 3] + juice[i - 1]) + juice[i]);
		}
		bw.write(String.valueOf(dp[n - 1]));
		bw.flush();
		bw.close();

	}
}