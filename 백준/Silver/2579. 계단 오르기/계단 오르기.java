import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine()); //계단의 갯수
		int[] score = new int[N+1];

		for(int i=1;i<=N;i++){
			score[i] = Integer.parseInt(br.readLine());
		}
		if(N == 1){
			bw.write(String.valueOf(score[1]));
		}
		else{
			int[][] dp = new int[N+1][3];

			dp[1][1] = score[1];
			dp[2][1] = score[2];
			dp[2][2] = dp[1][1] + score[2];

			for(int i=3;i<=N;i++){
				dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + score[i];
				dp[i][2] = dp[i-1][1] + score[i];
			}
			bw.write(String.valueOf(Math.max(dp[N][1],dp[N][2])));
		}

		bw.flush();
    }


}